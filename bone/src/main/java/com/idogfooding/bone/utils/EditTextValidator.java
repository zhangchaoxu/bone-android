package com.idogfooding.bone.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.rengwuxian.materialedittext.validation.METValidator;

import java.util.regex.Pattern;

/**
 * EditTextValidator
 *
 * @author Charles
 */
public class EditTextValidator {

    final static String formatterError = "格式不正确";
    final static String requiredError = "不允许为空";

    public static METValidator validateRequired(CharSequence name) {
        return new METValidator(name + formatterError) {
            @Override
            public boolean isValid(@NonNull CharSequence charSequence, boolean isEmpty) {
                return !TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(charSequence.toString().trim());
            }
        };
    }

    public static METValidator validatorString(CharSequence name, final int minLen, final int maxLen) {
        return new METValidator(name + formatterError) {
            @Override
            public boolean isValid(@NonNull CharSequence charSequence, boolean isEmpty) {
                return charSequence.length() >= minLen && charSequence.length() <= maxLen;
            }
        };
    }

    public static METValidator validatorRegex(CharSequence name, final String regex) {
        return new METValidator(name + formatterError) {
            @Override
            public boolean isValid(@NonNull CharSequence charSequence, boolean isEmpty) {
                return Pattern.compile(regex).matcher(charSequence).matches();
            }
        };
    }

    public static METValidator validatorPhone(CharSequence name) {
        return new METValidator(name + formatterError) {
            @Override
            public boolean isValid(@NonNull CharSequence charSequence, boolean isEmpty) {
                return Pattern.compile("\\b(1[3,4,5,6,7,8,9]\\d{9})\\b").matcher(charSequence).matches();
            }
        };
    }

    public static METValidator validatorNumber(CharSequence name, final int minLen, final int maxLen) {
        return new METValidator(name + formatterError) {
            @Override
            public boolean isValid(@NonNull CharSequence charSequence, boolean isEmpty) {
                return Pattern.compile("^\\d{" + minLen + "," + maxLen + "}$").matcher(charSequence).matches();
            }
        };
    }

}
