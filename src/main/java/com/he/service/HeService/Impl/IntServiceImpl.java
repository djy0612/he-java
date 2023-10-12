package com.he.service.HeService.Impl;

import com.he.pojo.Operand;
import com.he.pojo.Result;
import com.he.service.HeService.IntService;
import com.he.utils.BfvDist;
import com.he.utils.CppSupport;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IntServiceImpl implements IntService {

    String a_path = "/tmp/a.dat";
    String b_path = "/tmp/b.dat";
    String pk_path = "/tmp/pk.dat";
    String sk_path = "/tmp/sk.dat";
    String ab_path = "/tmp/ab.dat";
    @Override
    public Result IntHe(int a, int b, String opcode, String flag) {
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

        String[] strings = new String[4];
        int step = 1;
        if(flag.equals("true"))
            step = 100;
        long start_time = new Date().getTime();
        if(opcode.equals("exp")){
            for (int i = 0; i < step; i++) {
                strings = CppSupport.HE_Int_Power(a, b);
            }
        }
        else if(op == '^' || op == 'R'){
            for (int i = 0; i < step; i++) {
                strings = CppSupport.HE_Int(a, op);
            }
        }
        else{
            for (int i = 0; i < step; i++) {
                strings = CppSupport.HE_Int(a, b, op);
            }
        }
        long end_time = new Date().getTime();

        Result result = new Result();
        if(op == '+' || op == '-' || op == '*'){
            result.setEa(strings[0]);
            result.setEb(strings[1]);
            result.setEab(strings[2]);
            result.setAb(strings[3]);
        }else{
            result.setEa(strings[0]);
            result.setEb("");
            result.setEab(strings[1]);
            result.setAb(strings[2]);
        }
        result.setTime(String.valueOf(end_time - start_time));

        return result;
    }

    //BFV_Encrypt(int a, int b, String a_path, String b_path, String pk_path, String sk_path);
    @Override
    public Result Int_Encrypt(int a, int b) {
        String[] strings;
        Result result = new Result();
        strings = BfvDist.BFV_Encrypt(a,b,a_path,b_path,pk_path, sk_path);
        result.setEa(strings[0]);
        result.setEb(strings[1]);
        return result;
    }

    //public static native String[] BFV_HE_Operation(char opcode, String aPath, String a_path, String b_path, String ab_path);

    @Override
    public String[] Int_He_Operation(String opcode) {
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
            return BfvDist.BFV_HE_Operation(op, a_path, ab_path);
        }

        return BfvDist.BFV_HE_Operation(op, a_path, b_path,ab_path);
    }

    @Override
    public String[] Int_Decrypt() {
        return BfvDist.BFV_Decrypt(ab_path, sk_path);
    }
}
