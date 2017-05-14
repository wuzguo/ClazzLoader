package com.github.wuzguo.utils;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * IntelliJ IDEA 2016.3
 *
 * @author： admin
 * @date： 五月 13, 2017
 * @version: 1.0.0
 * @desc: ClazzLoader / com.github.wuzguo.utils
 */
public class EncryptHelper {

    // 简单的按位按位异或加密算法
    public static boolean encHelper(final InputStream input, final OutputStream output) {
        int bt = -1;
        try {
            while ((bt = input.read()) != -1) {
                output.write(bt ^ 0xff);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

