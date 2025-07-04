//package com.fei.playground.util;
//
///**
// * Created by yuans on 2017-11-05.
// */
//
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//import javax.crypto.*;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidKeyException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//
///*
// * AES对称加密和解密
// */
//public class AesUtil {
//
//    public static String encodeRules="waveVito_1.0";
//    /*
//     * 加密
//     * 1.构造密钥生成器
//     * 2.根据ecnodeRules规则初始化密钥生成器
//     * 3.产生密钥
//     * 4.创建和初始化密码器
//     * 5.内容加密
//     * 6.返回字符串
//     */
//    public static String AESEncode(String encodeRules,String content){
//        try {
//            //1.构造密钥生成器，指定为AES算法,不区分大小写
//            KeyGenerator keygen=KeyGenerator.getInstance("AES");
//            //2.根据ecnodeRules规则初始化密钥生成器
//            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
//            secureRandom.setSeed(encodeRules.getBytes());
//            //生成一个128位的随机源,根据传入的字节数组
//            keygen.init(128, secureRandom);
//            //3.产生原始对称密钥
//            SecretKey original_key=keygen.generateKey();
//            //4.获得原始对称密钥的字节数组
//            byte [] raw=original_key.getEncoded();
//            //5.根据字节数组生成AES密钥
//            SecretKey key=new SecretKeySpec(raw, "AES");
//            //6.根据指定算法AES自成密码器
//            Cipher cipher=Cipher.getInstance("AES");
//            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
//            byte [] byte_encode=content.getBytes("utf-8");
//            //9.根据密码器的初始化方式--加密：将数据加密
//            byte [] byte_AES=cipher.doFinal(byte_encode);
//            //10.将加密后的数据转换为字符串
//            //这里用Base64Encoder中会找不到包
//            //解决办法：
//            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
//            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
//            //11.将字符串返回
//            return AES_encode;
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        //如果有错就返加nulll
//        return null;
//    }
//    /*
//     * 解密
//     * 解密过程：
//     * 1.同加密1-4步
//     * 2.将加密后的字符串反纺成byte[]数组
//     * 3.将加密内容解密
//     */
//    public static String AESDncode(String encodeRules,String content){
//        try {
//            //1.构造密钥生成器，指定为AES算法,不区分大小写
//            KeyGenerator keygen=KeyGenerator.getInstance("AES");
//            //2.根据ecnodeRules规则初始化密钥生成器
//            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
//            secureRandom.setSeed(encodeRules.getBytes());
//            //生成一个128位的随机源,根据传入的字节数组
//            keygen.init(128, secureRandom);
//            //3.产生原始对称密钥
//            SecretKey original_key=keygen.generateKey();
//            //4.获得原始对称密钥的字节数组
//            byte [] raw=original_key.getEncoded();
//            //5.根据字节数组生成AES密钥
//            SecretKey key=new SecretKeySpec(raw, "AES");
//            //6.根据指定算法AES自成密码器
//            Cipher cipher=Cipher.getInstance("AES");
//            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
//            cipher.init(Cipher.DECRYPT_MODE, key);
//            //8.将加密并编码后的内容解码成字节数组
//            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
//            /*
//             * 解密
//             */
//            byte [] byte_decode=cipher.doFinal(byte_content);
//            String AES_decode=new String(byte_decode,"utf-8");
//            return AES_decode;
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        }
//
//        //如果有错就返加nulll
//        return null;
//    }
//
//    /**
//     * 加密
//     * @param content
//     * @return
//     */
//    public static String    aesEncode (String content){
//        return AESEncode(encodeRules,content);
//    }
//
//    /**
//     * 解密
//     * @param content
//     * @return
//     */
//    public static String  aesDecode(String content){
//        return AESDncode(encodeRules,content);
//    }
//
//    /**
//     * 用于与uniCloud接口交互的解密
//     * @param seed 种子
//     * @param encrypted 要解密的字符串
//     */
//    public static String decryptUniCloudAES(String seed, String encrypted) throws Exception {
//        byte[] keyb = seed.getBytes("UTF-8");
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] thedigest = md.digest(keyb);
//        SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
//        Cipher dcipher = Cipher.getInstance("AES");
//        dcipher.init(Cipher.DECRYPT_MODE, skey);
//
//        byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
//        return new String(clearbyte);
//    }
//
//    private static byte[] toByte(String hexString) {
//        int len = hexString.length()/2;
//        byte[] result = new byte[len];
//        for (int i = 0; i < len; i++) {
//            result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
//        }
//        return result;
//    }
//
//    public static void main(String[] args) throws Exception {
//        System.out.println(decryptUniCloudAES(encodeRules, "296ee338522ed473ccebf67b74a4d0a2"));
//
//        String  pwd="101";
//        System.out.println("明文长度："+pwd.length());
//        /*
//         * 加密
//         */
//        String pwd_code=aesEncode("10017");
//
//        System.out.println(pwd_code); System.out.println("密文长度"+pwd_code.length());
//        System.out.println(aesDecode("WbrrTFKf0ZlsewiNohfVUg=="));
//
//    }
//
//}