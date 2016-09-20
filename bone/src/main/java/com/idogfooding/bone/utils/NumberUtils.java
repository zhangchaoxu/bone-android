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

    public static float parseToFloat(String str, float defaultVal) {
        Float val;
        try {
            val = Float.valueOf(str);
        } catch (Exception e) {
            val = defaultVal;
        }
        return val;
    }

    public static float parseToFloat(String str) {
        return parseToFloat(str, 0f);
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

    public static float rountWithReserved(float number, int reserved) {
        if (reserved < 0)
            return 0;
        else if (reserved == 0)
            return Math.round(number);
        else
            return (float) (Math.round(number * 10 * reserved) / (10 * reserved));
    }

    public static double rountWithReserved(double number, int reserved) {
        if (reserved < 0)
            return 0;
        else if (reserved == 0)
            return Math.round(number);
        else
            return (double) (Math.round(number * 10 * reserved) / (10 * reserved));
    }

}
