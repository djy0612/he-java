package com.he;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.he.mapper")
public class HeApplication {

    public static void main(String[] args) {
        //System.load("/opt/he_project/backend/he/he/build/libsealshared.so");

        String a_path = "/tmp/a.dat";
        String b_path = "/tmp/b.dat";
        String pk_path = "/tmp/pk.dat";
        String sk_path = "/tmp/sk.dat";
        String ab_path = "/tmp/ab.dat";

        // CppSupport.helloWord();

//        long start_time = new Date().getTime();
//        for (int i = 0; i < 1; i++) {
//            String[] ss = BfvDist.BFV_HE_Operation('+', a_path, b_path, ab_path);
//            for (String s : ss) {
//                System.out.println(s);
//            }
//        }
//        long end_time = new Date().getTime();
//        System.out.println(end_time - start_time);


        SpringApplication.run(HeApplication.class, args);
    }
}
