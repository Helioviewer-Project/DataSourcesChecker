package org.helioviewer.jhv.log;

import org.helioviewer.jhv.time.TimeUtils;

public class Log {

    private static String format(String s) {
        return TimeUtils.format(System.currentTimeMillis()) + " [" + Thread.currentThread().getName() + "] " + s;
    }

    public static void info(Object obj) {
        System.err.println(format("INFO " + obj.toString()));
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
