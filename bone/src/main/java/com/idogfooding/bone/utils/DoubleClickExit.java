package com.idogfooding.bone.utils;

/**
 * DoubleClickExit
 *
 * @author Charles
 */
public class DoubleClickExit {

    public static long lastClick = 0L;
    private static final int THRESHOLD = 2000;// 1000ms

    public static boolean check() {
        long now = System.currentTimeMillis();
        boolean b = now - lastClick < THRESHOLD;
        lastClick = now;
        return b;
    }
}
