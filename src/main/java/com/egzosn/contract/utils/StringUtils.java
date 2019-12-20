package com.egzosn.contract.utils;

/**
 * @author egan
 * <br/>
 * Create time 2019/12/20 10:36
 * <br/>
 */
public final class StringUtils {

    private StringUtils() {
    }
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}
