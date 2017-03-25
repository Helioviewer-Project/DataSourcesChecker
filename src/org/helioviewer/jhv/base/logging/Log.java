package org.helioviewer.jhv.base.logging;

public class Log {

    public static void warn(Object obj) {
        System.err.println(obj.toString());
    }

    public static void error(Object obj) {
        System.err.println(obj.toString());
    }

    public static void error(Object obj, Throwable error) {
        System.err.println(obj.toString() + error.toString());
    }

    public static void debug(Object obj) {
        System.out.println(obj.toString());
    }

}
