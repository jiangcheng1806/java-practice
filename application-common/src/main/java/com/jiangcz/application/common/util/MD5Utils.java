package com.jiangcz.application.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 类名称：MD5Utils<br>
 * 类描述：<br>
 * 创建时间：2019年01月29日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class MD5Utils {

    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    /**
     * 无盐md5加密
     * @param input
     * @return
     */
    private static String md5WithoutSalt(String input) {

        try {

            MessageDigest digest = MessageDigest.getInstance("MD5");

            return byte2Hex(digest.digest(input.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 字节转16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {

        int len = bytes.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            byte byte0 = bytes[i];
            sb.append(hex[byte0 >>> 4 & 0xf]);
            sb.append(hex[byte0 & 0xf]);
        }

        return sb.toString();
    }

    /**
     * 字节转16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex2(byte[] bytes) {

        int len = bytes.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {

            String hex = Integer.toHexString(bytes[i] & 0xFF);

            if (hex.length() == 1){
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    /**
     * 16进制 转 二进制
     * @param hex
     * @return
     */
    public static byte[] hex2byte(String hex){

        if (hex.length() < 1){
            return null;
        }

        byte[] result = new byte[hex.length() / 2];

        for (int i = 0; i < hex.length() / 2; i++) {

            int high = Integer.parseInt(hex.substring( i * 2, i * 2 + 1),16);
            int low = Integer.parseInt(hex.substring( i * 2 + 1,i * 2 + 2),16);

            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }

    public static void main(String[] args) {


        System.out.println(salt());



        String input = "123456";

        System.out.println("MD5加密" + "\n" + "明文:" + input + "\n" + "无盐密文：" + md5WithoutSalt(input));

        System.out.println("带盐密文" + md5WithSalt(input,0));


        String content = "加油，有戏？";
        String key = "123";
        System.out.println("加密之前：" + content);

        // 加密
        byte[] encrypt = MD5Utils.aesEncrypt(content, key);
        assert encrypt != null;
        System.out.println("加密后的内容：" + new String(encrypt));

        //如果想要加密内容不显示乱码，可以先将密文转换为16进制
        String hexStrResult = byte2Hex(encrypt);
        System.out.println("16进制的密文："  + hexStrResult);

        //如果的到的是16进制密文，别忘了先转为2进制再解密
        byte[] twoStrResult = hex2byte(hexStrResult);
        assert twoStrResult != null;
        System.out.println("2进制密文：" + new String(twoStrResult));


        // 解密
        byte[] decrypt = MD5Utils.aesDecrypt(twoStrResult, key);
        assert decrypt != null;
        System.out.println("解密后的内容：" + new String(decrypt));

    }

    /**
     * AES 加密
     * @param input
     * @param key
     * @return
     */
    public static byte[] aesEncrypt(String input,String key){
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");

            kgen.init(128,new SecureRandom(key.getBytes()));

            SecretKey secretKey = kgen.generateKey();
            byte[] encoded = secretKey.getEncoded();

            SecretKeySpec keySpec = new SecretKeySpec(encoded,"AES");
            Cipher cipher = Cipher.getInstance("AES");

            byte[] bytes = input.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE,keySpec);

            return cipher.doFinal(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES解密
     * @param input
     * @param key
     * @return
     */
    public static byte[] aesDecrypt(byte[] input,String key){

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128,new SecureRandom(key.getBytes()));

            SecretKey secretKey = keyGen.generateKey();
            byte[] encoded = secretKey.getEncoded();

            SecretKeySpec keySpec = new SecretKeySpec(encoded,"AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,keySpec);

            return cipher.doFinal(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 带盐加密
     * @param input
     * @param type
     * @return
     */
    private static String md5WithSalt(String input, int type) {

        try {


            String salt = null;
            if (type == 0){

                salt = salt();
            } else if (type == 1){

                String hash = null;
                // hash 从 库中 查找 并赋值
                salt = getSaltAtHash(hash);
            }

            String result = md5WithSalt(input,salt);

            return result;

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据输入和盐加密
     * @param input
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String md5WithSalt(String input,String salt) throws NoSuchAlgorithmException {


        MessageDigest digest = MessageDigest.getInstance("MD5");

        String saltInput = input + salt;//加盐

        String result = byte2Hex(digest.digest(saltInput.getBytes())); //hash计算转换

        System.out.println("加盐密文：" + result);

        // 将 salt 存储到 hash 中，用于登陆 验证密码时使用相同的盐

        char[] cs = new char[48];
        for (int i = 0; i < 48; i+= 3) {
            cs[i] = result.charAt(i / 3 * 2);
            cs[i + 1] = salt.charAt(i / 3);
            cs[i + 2] = result.charAt(i / 3 * 2 + 1);
        }

        result = new String(cs);

        return result;
    }

    /**
     * 提取salt
     * @param hash
     * @return
     */
    private static String getSaltAtHash(String hash) {

        StringBuilder sb = new StringBuilder();
        char[] h = hash.toCharArray();

        for (int i = 0;i < hash.length(); i+= 3){
            sb.append(h[i + 1]);
        }

        return sb.toString();
    }


    /**
     * 获取随机盐
     * @return
     */
    public static String salt(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);

        for (int i = 0; i< sb.capacity(); i++){
            sb.append(hex[random.nextInt(16)]);
        }

        return sb.toString();
    }
}
