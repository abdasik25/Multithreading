package by.epam.multithreading.thread;

import by.epam.multithreading.entity.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiagonalChangerThread implements Runnable {

    private static Logger LOGGER = LogManager.getLogger();
    private int threadNumber;

    public DiagonalChangerThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public void setDiagonalElement(Matrix matrix, int element) {
        for (int i = 0; i < matrix.getColumnSize(); i++) {
            for (int j = 0; j < matrix.getRowSize(); j++) {
                if (i == j) {
                    matrix.setElement(i, j, element);
                }
            }
        }
    }

    //TODO
    @Override
    public void run() {
        Matrix matrix = Matrix.getInstance();
        setDiagonalElement(matrix, threadNumber);
    }
}
