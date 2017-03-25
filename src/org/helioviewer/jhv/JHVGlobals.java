package org.helioviewer.jhv;

import java.util.concurrent.ExecutorService;

import org.helioviewer.jhv.threads.JHVExecutor;

public class JHVGlobals {

    public static String userAgent = "DataSources-schema";

    private static final ExecutorService executorService = JHVExecutor.getJHVWorkersExecutorService("MAIN", 10);

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static int getStdReadTimeout() {
        return 180000;
    }

    public static int getStdConnectTimeout() {
        return 60000;
    }

}
