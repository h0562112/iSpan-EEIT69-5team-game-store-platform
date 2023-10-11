package com.ispan.eeit69.utils.memberutils;

import java.util.Base64;

public class ImageUtils {
    public static String byteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}


