package com.idogfooding.bone.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

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
        } else {
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

    /**
     * 获得分隔后的第一个字符串
     *
     * @param list      需要处理的字符串.
     * @param separator 分隔符.
     * @return 转化后的字符串
     */
    public static String getFirstString(String list, String separator) {
        if (TextUtils.isEmpty(list)) {
            return null;
        } else {
            String[] picList = list.split(separator, -1);
            if (picList.length > 0) {
                return picList[0];
            } else {
                return null;
            }
        }
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    public static List<String> splitToArray(String str, String separator) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(str.split(separator, -1));
        }
    }

    public static String getBracketsContent(String str) {
        int start = str.lastIndexOf("(");
        int end = str.lastIndexOf(")");
        if (start < 0 || end < 0 || start >= end) {
            return null;
        } else {
            return str.substring(start + 1, end);
        }
    }

    /**
     * is car number
     * 只能判断普通的车牌号，无法判断 警 教练等特殊车牌
     */
    public static boolean isCarNumber(String str) {
        if (TextUtils.isEmpty(str))
            return false;

        String[] provence = new String[]{"京", "津", "冀", "晋", "辽", "吉", "黑", "沪", "苏", "浙", "皖", "闽", "赣", "鲁", "豫", "鄂", "湘", "粤", "桂", "琼", "渝", "川", "黔", "滇", "藏", "陕", "甘", "青", "宁", "新", "港", "澳", "蒙"};
        String reg = "[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}";
        return Arrays.asList(provence).contains(str.substring(0, 1)) && Pattern.compile(reg).matcher(str).matches();
    }

}
