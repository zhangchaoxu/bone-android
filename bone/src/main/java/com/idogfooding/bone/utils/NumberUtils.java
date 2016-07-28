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

}
