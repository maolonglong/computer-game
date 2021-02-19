package com.csl.core.util;

/**
 * @author MaoLongLong
 * @date 2021-02-18 21:44
 */
public class IntUtil {

    private IntUtil() {
    }

    public static boolean anyEquals(int expected, int... nums) {
        for (int num : nums) {
            if (expected == num) {
                return true;
            }
        }
        return false;
    }
}
