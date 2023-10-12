package com.he.service.HeService.Impl;

import com.he.pojo.Result;
import com.he.service.HeService.FloatService;
import com.he.utils.CkksDist;
import com.he.utils.CppSupport;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FloatServiceImpl implements FloatService {
    String a_path = "/tmp/a.dat";
    String b_path = "/tmp/b.dat";
    String pk_path = "/tmp/pk.dat";
    String sk_path = "/tmp/sk.dat";
    String ab_path = "/tmp/ab.dat";
    @Override
    public Result FloatHe(double a, double b, String opcode, String flag) {
        char op = '*';
        switch (opcode) {
            case "add":
                op = '+';
                break;
            case "mul":
                op = '*';
                break;
            case "sub":
                op = '-';
                break;
            case "sq":
                op = '^';
                break;
            case "R":
                op = 'R';
        }

        String[] strings = new String[4];
        int step = 1;
        if(flag.equals("true"))
            step = 100;
        long start_time = new Date().getTime();
        if(op == '^' || op == 'R') {
            for (int i = 0; i < step; i++) {
                strings = CppSupport.HE_Float(a, op);
            }
        }
        else{
            for (int i = 0; i < step; i++) {
                strings = CppSupport.HE_Float(a, b, op);
            }
        }
        long end_time = new Date().getTime();
//        long start_time = new Date().getTime();
//        for (int i = 0; i < step; i++) {
//            strings = CppSupport.HE_Float(a, b, op);
//        }
//        long end_time = new Date().getTime();


        Result result = new Result();
        if(op == '^' || op == 'R'){
            result.setEa(strings[0]);
            result.setEb("");
            result.setEab(strings[1]);
            result.setAb(strings[2]);
        }else{
            result.setEa(strings[0]);
            result.setEb(strings[1]);
            result.setEab(strings[2]);
            result.setAb(strings[3]);
        }
        result.setTime(String.valueOf(end_time - start_time));

        return result;
    }

    @Override
    public int Float_Keygen(){
        int re = CkksDist.CKKS_Keygen(pk_path, sk_path);
        return re;
    }
    @Override
    public Result Float_Encrypt(double a, double b) {
        String[] strings;
        Result result = new Result();
        strings = CkksDist.CKKS_Encrypt(a, b, a_path, b_path, pk_path);
        result.setEa(strings[0]);
        result.setEb(strings[1]);
        return result;
    }

    @Override
    public String[] Float_He_Operation(String opcode) {
        char op = ' ';
        switch (opcode) {
            case "add":
                op = '+';
                break;
            case "mul":
                op = '*';
                break;
            case "sub":
                op = '-';
                break;
            case "sq":
                op = '^';
                break;
            case "R":
                op = 'R';
        }

        if(op == '^' || op == 'R'){
            return CkksDist.CKKS_HE_Operation(op, a_path, ab_path);
        }

        return CkksDist.CKKS_HE_Operation(op, a_path, b_path, ab_path);
    }

    @Override
    public String[] Float_Decrypt() {
        return CkksDist.CKKS_Decrypt(ab_path, sk_path);
    }
}
