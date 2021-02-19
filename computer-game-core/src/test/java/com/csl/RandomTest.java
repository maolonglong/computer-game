package com.csl;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author MaoLongLong
 * @date 2021-02-19 16:25
 */
public class RandomTest {

    @Test
    void random() {
        int[] freq = new int[10];
        for (int i = 0; i < 1000000; i++) {
            freq[RandomUtil.randomInt(10)]++;
        }
        System.out.println(Arrays.toString(freq));
    }
}
