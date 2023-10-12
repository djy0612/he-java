package com.he.controller;

import com.he.utils.smp.MPDZ;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class MPCController {
    // 写入要参与计算的数据，parma：数据 order：参与者的序号，0或1
    @RequestMapping("/api/write")
    public String Decrypt(String param, String order) throws IOException {
        MPDZ.writeParam(param, Integer.parseInt(order));

        return order + "OK";
    }

    // 生成密钥
    @RequestMapping("/api/keygen")
    public String key() throws IOException {
        MPDZ.keyGen();
        return "OK";
    }

    // 编译
    @RequestMapping("/api/compile")
    public String compile() throws IOException {
        MPDZ.compile();
        return "OK";
    }

    // 提交计算，order：参与者的序号，0或1。只有0和1两个参与者都提交计算后才会返回计算的结果
    @RequestMapping("/api/submit")
    public String submit(String order) throws ExecutionException, InterruptedException {
        System.out.println(Integer.parseInt(order));
        return MPDZ.submitExec(Integer.parseInt(order));
    }
}
