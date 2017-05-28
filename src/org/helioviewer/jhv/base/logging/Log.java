package org.helioviewer.jhv.base.logging;

import org.helioviewer.jhv.base.time.TimeUtils;

public class Log {

    private static String format(String s) {
        return TimeUtils.format(System.currentTimeMillis()) + " [" + Thread.currentThread().getName() + "] " + s;
    }

    public static void warn(Object obj) {
        System.err.println(format("WARN " + obj.toString()));
    }

    public static void error(Object obj) {
        System.err.println(format("ERROR " + obj.toString()));
    }

    public static void error(Object obj, Throwable error) {
        System.err.println(format("ERROR " + obj.toString() + error.toString()));
    }

    public static void debug(Object obj) {
        System.out.println(format("DEBUG " + obj.toString()));
    }

}
