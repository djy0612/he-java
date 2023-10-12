package com.he.utils.smp;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.he.utils.smp.MPDZConstant.SPDZ_PATH;

@Slf4j
public class SmpUtil {

    public static void WriteFile(String filepath, String content) throws IOException {
        File file = new File(filepath);
        if(file.exists()) file.delete();
        Files.write(Paths.get(filepath), content.getBytes(), StandardOpenOption.CREATE);
    }

    public static String submit(String[] cmd){
        InputStream in = null;
        String res = null;

        try {
            Process process = Runtime.getRuntime().exec(cmd, null, new File(SPDZ_PATH));
            in = process.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String line = null;

            while ((line = read.readLine()) != null) {
                if (line.contains("Results")){
                    res = line.substring(line.indexOf('=') + 1, line.length());
                }
                System.out.println(line);
            }

            int exitValue = process.waitFor();
            // System.out.println("返回值：" + exitValue);
            if (exitValue !=0 ){
                log.warn("获取安全多方计算程序结果出错！");
            }

            process.getOutputStream().close();       // 不要忘记了一定要关
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void execute(String[] cmd) {
        InputStream in = null;

        try {
            Process process = Runtime.getRuntime().exec(cmd, null, new File(SPDZ_PATH));
            in = process.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String line = null;

            while ((line = read.readLine()) != null) {
                System.out.println(line);
            }

            int exitValue = process.waitFor();
            System.out.println("返回值：" + exitValue);
            if (exitValue != 0 ){
                log.warn("获取程序结果出错！");
            }
            process.getOutputStream().close();       // 不要忘记了一定要关
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
