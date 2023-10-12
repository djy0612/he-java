package com.he.service.HeService;

import com.he.pojo.Result;

public interface FloatService {
    Result FloatHe(double a, double b, String opcode, String flag);
    int Float_Keygen();
    Result Float_Encrypt(double a, double b);
    String[] Float_He_Operation(String opcode);
    String[] Float_Decrypt();
}
