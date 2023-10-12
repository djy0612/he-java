package com.he.controller;

import com.he.pojo.HeParameter;
import com.he.pojo.Result;
import com.he.service.HeService.FloatService;
import com.he.service.HeService.IntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeController {

    @Autowired
    private FloatService floatService;
    @Autowired
    private IntService intService;

    @RequestMapping("/api/he")
    public Result he(HeParameter heParameter){
        Result result = new Result();
        if(heParameter.type.equals("float")){
            double a = Double.parseDouble(heParameter.a);
            double b = Double.parseDouble(heParameter.b);
            result =  floatService.FloatHe(a, b, heParameter.opcode, heParameter.flag);
        }
        else{
            int a = Integer.parseInt(heParameter.a);
            int b = Integer.parseInt(heParameter.b);
            result =  intService.IntHe(a, b, heParameter.opcode, heParameter.flag);
        }
        return result;
    }
    @RequestMapping("/api/keygenerate")
    public int keygen(){
        return floatService.Float_Keygen();
    }

    @RequestMapping("/api/encrypt")
    public Result encrypt(HeParameter heParameter){
        Result result  = new Result();
        if(heParameter.type.equals("int")){
            int a = Integer.parseInt(heParameter.a);
            int b = 0;
            if(!heParameter.b.equals("")){
                b = Integer.parseInt(heParameter.b);
            }
            result = intService.Int_Encrypt(a, b);
        }
        else{
            double a = Double.parseDouble(heParameter.a);
            double b = 0;
            if(!heParameter.b.equals("")){
                b = Double.parseDouble(heParameter.b);
            }
            result = floatService.Float_Encrypt(a, b);
        }
        return result;
    }


    @RequestMapping("/api/heoperation")
    public String[] heOperation(HeParameter heParameter){
        String[] s;
        if(heParameter.type.equals("int")){
           return intService.Int_He_Operation(heParameter.opcode);
        }
        else{
            return floatService.Float_He_Operation(heParameter.opcode);
        }
    }

    @RequestMapping("/api/decrypt")
    public String[] Decrypt(HeParameter heParameter){
        if(heParameter.type.equals("int")){
            return intService.Int_Decrypt();
        }
        else{
            return floatService.Float_Decrypt();
        }
    }



}
