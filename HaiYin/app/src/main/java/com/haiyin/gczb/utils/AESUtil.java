package com.haiyin.gczb.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESUtil {
    /*
     * 已确认 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String sKey        = "69c28104a4b549a3";
    private static String ivParameter = "c7ec1f6b910a40c9";
    private static String encoding    = "utf-8";

    // 加密
    public static String encrypt(String sSrc) throws Exception {
        if (sSrc.isEmpty()) {
            return sSrc;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encoding));
        return Base64.encodeToString(encrypted,Base64.NO_WRAP);
    }

    // 解密
    public static String decrypt(String sSrc) throws Exception {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(sSrc,Base64.NO_WRAP);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, encoding);
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        // 需要加密的字串
        String cSrc = "{\"xxx\":\"111\",\"yyy\":222}";
        System.out.println("加密前的字串是：" + cSrc);
        // 加密
        String enString = encrypt(cSrc);
        System.out.println("加密后的字串是：" + enString);

        System.out.println("FgdQIEE7wgcdEHsxje0PDQ==".equals(enString));

        // 解密
        String DeString = decrypt("3sUl56WAlznZEGB118Sktlctdz+vQJ+osiCnMzwEEmltZsD6g2iL1N19ksTBlhCI");
        System.out.println("解密后的字串是：" + DeString);

        String phone = "{\"picType\":\"BannerPic\"}";
        System.out.println("加密后的字串是：" + encrypt(phone));
        System.out.println("解密后的字串是：" + decrypt(
                "tTkUNF0uNH3QFPCPDTNQhcopn7ri4+xzpv2GPZeYI8kLLz/sw+8QCqePLugYG0M1HgCQTWRpFupJFIQkLC9d/LJGvReh7O2ff5blIbstviYxSPGlSbOa6jYmnIV6SzZA3KHBs1rCiAwH7zbR2E+1WFbypyz66OAL0m8I6TMQy4YeH6+Lwn61NuT6vvhlfjDaanvIjoMB8PluDwkQc5cC79Pj1OpDb3q7NETn7um3Q4aspBVhfOle1teOlYpOSHb6"));

    }
}
