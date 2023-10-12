package com.he.utils.smp;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.he.utils.smp.MPDZConstant.*;
import static com.he.utils.smp.SmpUtil.*;

public class MPDZ {

    public static void compile(){
        String[] cmd = new String[]{COMILER, "-B", "32", PROGRAM_PATH};
        execute(cmd);
    }

    public static void writeParam(String param, int order) throws IOException {
        WriteFile(USER_INPUTS[order], param);
    }

    public static void keyGen(){
        String[] keyGen = new String[]{KEY_GENERATION, JOINER_COUNT.toString()};

        String s = "";
        for (String s1 : keyGen) {
            s += s1;
            s += " ";
        }
        System.out.println("keyGen:" + s);

        execute(keyGen);
    }

    public static String submitExec(int order) throws ExecutionException, InterruptedException {
        String []cmd =  new String[]{EXECUTOR, "-N", JOINER_COUNT.toString(), "-p", String.valueOf(order), "testgp"};
        String s = "";
        for (String s1 : cmd) {
            s += s1;
            s += " ";
        }
        System.out.println(s);

        CompletableFuture<String> player0 = CompletableFuture.supplyAsync(() -> {
            String res = submit(cmd);
            return res;
        });

        return player0.get();
    }
}
