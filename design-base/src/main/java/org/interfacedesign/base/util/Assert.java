package org.interfacedesign.base.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by lishaohua on 16-6-10.
 */
public abstract class Assert {

    public static void notEmpty(String value) {
        notEmpty(value, "参数不能为空");
    }

    public static void notEmpty(String value, String message) {
        if(StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void lengthNoGreater(String value, int length, String message) {
        if(value == null) {
            return;
        }
        if(value.length() > length) {
            throw new IllegalArgumentException(message);
        }
    }
}
