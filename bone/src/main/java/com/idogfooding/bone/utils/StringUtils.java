package com.idogfooding.bone.utils;

import java.util.List;
import java.util.UUID;

/**
 * StringUtils
 *
 * @author Charles
 */
public class StringUtils {
    
    /**
     * 将字符串List转化为字符串，以分隔符间隔.
     *
     * @param list      需要处理的List.
     * @param separator 分隔符.
     * @return 转化后的字符串
     */
    public static String toString(List<String> list, String separator) {
        if (null == list || list.isEmpty()) {
            return null;
        } else  {
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(separator + str);
            }
            return sb.deleteCharAt(0).toString();
        }
    }

    /**
     * 将字符串List转化为字符串，以分隔符间隔.
     *
     * @param list      需要处理的List.
     * @param separator 分隔符.
     * @return 转化后的字符串
     */
    public static String toString(CharSequence[] list, String separator) {
        if (list == null || list.length == 0) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (CharSequence str : list) {
                sb.append(separator + str);
            }
            return sb.deleteCharAt(0).toString();
        }
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    public static String getBracketsContent(String str) {
        int start = str.lastIndexOf("(");
        int end = str.lastIndexOf(")");
        if (start < 0 || end < 0 || start >= end) {
            return null;
        } else {
            return str.substring(start +  1, end);
        }
    }

}
