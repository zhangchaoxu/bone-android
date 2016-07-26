package com.idogfooding.bone.utils;

import java.security.MessageDigest;

/**
 * EncryptionUtil
 *
 * @author Charles
 */
public class EncryptionUtil {

    public static String getMD5(String srcStr) {
        return getMD5(srcStr, true);
    }

    /**
     * 使用md5散列字符串,默认32
     *
     * @param srcStr 输入的字符串
     * @return 加密后的字符串
     */
    public static String getMD5(String srcStr, boolean full) {
        try {
            String result = "";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF).toUpperCase();
                result += ((hex.length() == 1) ? "0" : "") + hex;
            }
            return full ? result : result.substring(8, 24);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
