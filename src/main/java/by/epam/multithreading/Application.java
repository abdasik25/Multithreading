package by.epam.multithreading;

import by.epam.multithreading.entity.Matrix;
import by.epam.multithreading.reader.MatrixReader;
import by.epam.multithreading.thread.DiagonalChangerThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Application {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Matrix matrix = Matrix.getInstance();
            MatrixReader reader = new MatrixReader();
            ExecutorService executorService = Executors.newFixedThreadPool(6);
            List<String> list = reader.readMatrixFromFile("data/matrix_data")
                    .parallelStream()
                    .map(String::trim)
                    .collect(Collectors.toList());
//            list.parallelStream()
//                    .map(Integer::parseInt)
//                    .forEach(i -> executorService.execute(new DiagonalChangerThread(i)));
//            executorService.shutdown();
//            executorService.awaitTermination(1, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
