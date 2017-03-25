package org.helioviewer.jhv.threads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import org.jetbrains.annotations.NotNull;

public class JHVThread {

    public static void afterExecute(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException e) {
                t = e;
            } catch (ExecutionException e) {
                t = e.getCause();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // ??? ignore/reset
            }
        }
        if (t != null) {
            t.printStackTrace();
        }
    }

    // this creates daemon threads
    public static class NamedThreadFactory implements ThreadFactory {

        final String name;

        public NamedThreadFactory(String _name) {
            name = _name;
        }

        @Override
        public Thread newThread(@NotNull Runnable r) {
            Thread thread = new Thread(r, name);
            thread.setDaemon(true);
            return thread;
        }
    }

}
