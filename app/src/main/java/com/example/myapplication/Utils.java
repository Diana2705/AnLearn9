package com.example.myapplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static void md5Custom(String string) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MDS");
            messageDigest.reset();
            messageDigest.update((string.getBytes()));
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}






