package com.fei.playground.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by yuans on 2017-10-18.
 */
public class IdUtil {
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getUUid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static List<String> getUUids(int size) {
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ids.add(getUUid());
        }
        return ids;
    }

    public static String MD5(String text) {
        MessageDigest msgDigest = null;

        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        try {
            msgDigest.update(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }
        byte[] bytes = msgDigest.digest();
        String md5Str = new String(encodeHex(bytes));
        return md5Str.toUpperCase();
    }

    public static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }
}
