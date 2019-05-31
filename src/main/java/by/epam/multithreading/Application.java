/**
 * Created by Alexander Lomat on 31.05.19
 * Version 0.0.3
 */

package by.epam.multithreading;

import by.epam.multithreading.matrix.Matrix;
import by.epam.multithreading.thread.DiagonalChangerThread;
import by.epam.multithreading.thread.ThreadCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {

    private static Logger LOGGER = LogManager.getLogger();
    private static final String THREAD_DATA_PATH = "data/threads_data";
    private static final int TIMEOUT_IN_MILLISECONDS = 2000;

    public static void main(String[] args) {
        try {
            ThreadCreator threadCreator = new ThreadCreator();
            Matrix matrix = Matrix.getInstance();
            LOGGER.info("\n" + matrix + " was initialized.");
            List<DiagonalChangerThread> diagonalChangerThreads = threadCreator.create(THREAD_DATA_PATH);
            ExecutorService executorService = Executors.newFixedThreadPool(diagonalChangerThreads.size());
            diagonalChangerThreads.forEach(executorService::execute);
            executorService.shutdown();
            executorService.awaitTermination(TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
            LOGGER.info("\n" + matrix + " was updated.");
        } catch (InterruptedException e) {
            LOGGER.warn("Was interrupted by ", e);
            Thread.currentThread().interrupt();
        }
    }
}
