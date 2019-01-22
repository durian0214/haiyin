package com.haiyin.gczb.utils;

import java.math.BigDecimal;

/**
 * Created by a on 2017/7/13.
 */
public class Arith {
    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(value1));
        BigDecimal b2 = new BigDecimal(String.valueOf(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(value1));
        BigDecimal b2 = new BigDecimal(String.valueOf(value2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(value1));
        BigDecimal b2 = new BigDecimal(String.valueOf(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     *
     * @param value1
     * @param value2
     * @return
     */
    public static String muls(String value1, double value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(String.valueOf(value2));
        return b1.multiply(b2).toString();
    }
    /**
     * 提供精确的除法运算方法div
     *
     * @param value1 被除数
     * @param value2 除数
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static double div1(double value1, double value2) {
        //如果精确范围小于0，抛出异常信息
//        if(scale<0){
//            throw new IllegalAccessException("精确度不能小于0");
//        }
        BigDecimal b1 = new BigDecimal(String.valueOf(value1));
        BigDecimal b2 = new BigDecimal(String.valueOf(value2));
        BigDecimal b3 = b1.divide(b2);
        // Double b4 = b3.setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
        return b3.doubleValue();
    }


    /**
     * 提供精确的除法运算方法并保留两位(不补位)
     *
     * @param value1 被除数
     * @param value2 除数
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static String div_text2(double value1, double value2) {
        //如果精确范围小于0，抛出异常信息
//        if(scale<0){
//            throw new IllegalAccessException("精确度不能小于0");
//        }
        BigDecimal b1 = new BigDecimal(String.valueOf(value1));
        BigDecimal b2 = new BigDecimal(String.valueOf(value2));
        BigDecimal b3 = b1.divide(b2);
        Double b4 = b3.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        java.text.DecimalFormat df = new java.text.DecimalFormat("#########.###");
        return df.format(b4);
    }

    /**
     * 提供精确的除法运算方法并保留两位
     *
     * @param value1 被除数
     * @param value2 除数
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static String div_text(double value1, double value2) {
        //如果精确范围小于0，抛出异常信息
//        if(scale<0){
//            throw new IllegalAccessException("精确度不能小于0");
//        }
        BigDecimal b1 = new BigDecimal(String.valueOf(value1));
        BigDecimal b2 = new BigDecimal(String.valueOf(value2));
        BigDecimal b3 = b1.divide(b2);
        Double b4 = b3.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        if(b4.toString().contains(".00")){
            int i = b4.intValue();
            return ""+i;
        }else
         {
            return df.format(b4);
        }
    }


    /**
     * double转保留两位text
     */
    public static String two_text(double value) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        return df.format(value);
    }


}
