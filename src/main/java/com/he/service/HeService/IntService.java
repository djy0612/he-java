package com.he.service.HeService;

import com.he.pojo.Operand;
import com.he.pojo.Result;

public interface IntService {
    Result IntHe(int a, int b, String opcode, String flag);
    Result Int_Encrypt(int a, int b);
    String[] Int_He_Operation(String opcode);
    String[] Int_Decrypt();
}
