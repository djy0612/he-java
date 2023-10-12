package com.he.utils;

public class BfvDist {

    /**
     * BFV加密
     * @author zq
     * @param a 第一个数字
     * @param b 第二个数字
     * @param a_path 第一个数字的密文文件位置
     * @param b_path 第二个数字的密文文件位置
     * @param pk_path 公钥文件位置
     * @param sk_path 私钥文件位置
     * @return [第一个值的密文，第二个值的密文]
     */
    public static native String[] BFV_Encrypt(int a, int b, String a_path, String b_path, String pk_path, String sk_path);

    /**
     * BFV同态操作
     * @author zq
     * @param opcode 同态操作运算符： + 加 - 减 * 乘
     * @param a_path 第一个数字的密文文件位置
     * @param b_path 第二个数字的密文文件位置
     * @param ab_path 同态操作后的密文文件位置
     * @return  [同态操作的密文]
     */
    public static native String[] BFV_HE_Operation(char opcode, String a_path, String b_path, String ab_path);

    /**
     * BFV同态操作
     * @author zq
     * @param opcode 同态操作运算符： ^ 平方 R 取反
     * @param a_path 密文文件位置
     * @param ab_path 同态操作后的密文文件位置
     * @return  [同态操作的密文]
     */
    public static native String[] BFV_HE_Operation(char opcode, String a_path, String ab_path);

    /**
     * BFV同态操作 幂函数
     * @param a_path 密文文件位置  底数
     * @param b 幂指数
     * @param ab_path 同态操作后的密文文件位置
     * @return [同态操作的密文]
     */
    public static native String[] BFV_HE_Power(String a_path, int b, String ab_path);

    /**
     * BFV解密
     * @author zq
     * @param ab_path 同态操作后的密文文件位置
     * @param sk_path 私钥文件位置
     * @return [解密后的值]
     */
    public static native String[] BFV_Decrypt(String ab_path, String sk_path);
}
