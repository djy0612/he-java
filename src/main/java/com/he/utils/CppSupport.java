package com.he.utils;

public class CppSupport {

    public static native void helloWord();

    /**
     * 整数型同态加密
     * @author zq
     * @param a 第一个数字
     * @param b 第二个数字
     * @param opcode 运算符：+ * -
     * @return  [a对应的密文, b对应的密文, 同态操作后的密文, 解密后的结果]
     */
    public static native String[] HE_Int(int a, int b, char opcode);

    /**
     * 整数型同态加密
     * @author zq
     * @param a 第一个数字
     * @param opcode 运算符：^ 平方 R 取反
     * @return [a对应的密文, 同态操作后的密文, 解密后的结果]
     */
    public static native String[] HE_Int(int a, char opcode);

    /**
     * 整数型同态加密 幂函数
     * @author zq
     * @param a 底数
     * @param b 幂指数
     * @return [a对应的密文, 同态操作a^b后的密文, 解密后的结果]
     */
    public static native String[] HE_Int_Power(int a, int b);

    /**
     * 浮点型同态加密
     * @author zq
     * @param a 第一个数字
     * @param b 第二个数字
     * @param opcode 运算符：+ - *
     * @return [a对应的密文, b对应的密文, 同态操作后的密文,解密后的结果]
     */
    public static native String[] HE_Float(double a, double b, char opcode);

    /**
     * 浮点型同态加密
     * @author zq
     * @param a 第一个数字
     * @param opcode 运算符：^ 平方 R 取反
     * @return [a对应的密文, 同态操作后的密文, 解密后的结果]
     */
    public static native String[] HE_Float(double a, char opcode);

    @Deprecated
    public static native String[] HE_Float_Power(double a, int b);

    public static void main(String[] args) {
        // 加载动态连接库
        System.load("/home/ty/Documents/java_libs/libsealshared.so");


    }
}
