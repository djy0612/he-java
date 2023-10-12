package com.he.utils;

public class UtilTest {

    public static void main(String[] args) {
        System.load("/home/ty/Documents/java_libs/libsealshared.so");

        String a_path = "/tmp/ckks_a.dat";
        String b_path = "/tmp/ckks_b.dat";
        String pk_path = "/tmp/ckks_pk.dat";
        String sk_path = "/tmp/ckks_sk.dat";
        String ab_path = "/tmp/ckks_ab.dat";

        // String[] ss = CkksDist.CKKS_Encrypt(1.2, 1.3, a_path, b_path, pk_path, sk_path);
        // String[] ss = CkksDist.CKKS_HE_Operation('^', a_path, ab_path);
        String[] ss = CkksDist.CKKS_Decrypt(ab_path, sk_path);

        // String[] ss = BfvDist.BFV_Encrypt(3, 3, a_path, b_path, pk_path, sk_path);
        // String[] ss = BfvDist.BFV_HE_Operation('+', a_path, b_path, ab_path);
        // String[] ss = BfvDist.BFV_HE_Power(a_path, 25, ab_path);
//
        // String[] ss = BfvDist.BFV_Decrypt(ab_path, sk_path);

        // String[] ss = CppSupport.HE_Int_Power(3, 6);

        for (String s : ss) {
            System.out.println(s);
        }

    }
}
