package com.idogfooding.bone.utils;

/**
 * NumberUtils
 *
 * @author Charles
 */
public class NumberUtils {

    public static Double parseToDouble(String str, double defaultVal) {
        Double doubleVal;
        try {
            doubleVal = Double.valueOf(str);
        } catch (Exception e) {
            doubleVal = defaultVal;
        }
        return doubleVal;
    }

    public static Double parseToDouble(String str) {
        return parseToDouble(str, 0d);
    }

    public static int parseToInt(String str, int defaultVal) {
        int doubleVal;
        try {
            doubleVal = Integer.valueOf(str);
        } catch (Exception e) {
            doubleVal = defaultVal;
        }
        return doubleVal;
    }

    public static int parseToInt(String str) {
        return parseToInt(str, 0);
    }

    public static long parseToLong(String str, long defaultVal) {
        long doubleVal;
        try {
            doubleVal = Long.valueOf(str);
        } catch (Exception e) {
            doubleVal = defaultVal;
        }
        return doubleVal;
    }

    public static long parseToLong(String str) {
        return parseToLong(str, 0L);
    }

}
