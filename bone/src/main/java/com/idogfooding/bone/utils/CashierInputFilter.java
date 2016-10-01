package com.idogfooding.bone.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CashierInputFilter
 *
 * @author Charles
 */
public class CashierInputFilter implements InputFilter {
    private Pattern mPattern;
    //输入的最大金额
    private final int MAX_VALUE = Integer.MAX_VALUE;

    //小数点后的2位数
    private final int POINTER_AFTER_LENGTH = 1;

    //小数点前的7位数
    private int POINTER_BEFORE_LENGTH = 1;

    private final String POINTER = ".";

    private static final String ZERO = "0";

    public CashierInputFilter() {
        mPattern = Pattern.compile("([0-9]|\\.)*");
    }

    public CashierInputFilter(int _maxLength) {
        mPattern = Pattern.compile("([0-9]|\\.)*");
        POINTER_BEFORE_LENGTH = _maxLength - POINTER_AFTER_LENGTH - 1;
    }

    /**
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return 输入内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();

        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }

        Matcher matcher = mPattern.matcher(source);
        //已经输入小数点的情况下，只能输入数字
        if (destText.contains(POINTER)) {
            if (!matcher.matches()) {
                return "";
            } else {
                if (POINTER.equals(source)) {  //只能输入一个小数点
                    return "";
                }
            }

            int index = destText.indexOf(POINTER);
            //验证小数点精度，保证小数点后只能输入两位
            int afterLength = dend - index;

            // 输入后，修改控制不了，//验证小数点精度，保证小数点前位数

            if (dstart == POINTER_BEFORE_LENGTH && index == POINTER_BEFORE_LENGTH) {
                return dest.subSequence(dstart, dend);
            }

            //验证小数点精度，保证小数点后只能输入两位
            if (afterLength > POINTER_AFTER_LENGTH) {
                return dest.subSequence(dstart, dend);
            }
        } else {
            //没有输入小数点的情况下，只能输入小数点和数字，但首位不能输入小数点和0
            if (!matcher.matches()) {
                return "";
            } else {
                //验证小数点精度，保证小数点前只能输入7位

                if (dstart == POINTER_BEFORE_LENGTH) {
                    if (sourceText.contains(POINTER))
                        return dest.subSequence(dstart, dend) + sourceText + ZERO + ZERO;
                    else
                        return dest.subSequence(dstart, dend) + POINTER + ZERO + ZERO;
                }
                if ((POINTER.equals(source) || ZERO.equals(source)) && TextUtils.isEmpty(destText)) {
                    return ZERO + POINTER + ZERO + ZERO;
                }
            }
        }

        //验证输入金额的大小
        double sumText = Double.parseDouble(destText + sourceText);
        if (sumText >= MAX_VALUE) {
            return dest.subSequence(dstart, dend);
        }

        return dest.subSequence(dstart, dend) + sourceText;
    }
}
