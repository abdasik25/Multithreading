package by.epam.multithreading.thread;

import by.epam.multithreading.matrix.Matrix;
import by.epam.multithreading.matrix.MatrixCell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.Phaser;

public class DiagonalChangerThread implements Runnable {

    private static Logger LOGGER = LogManager.getLogger();
    private int threadNumber;
    private Phaser phaser;

    public DiagonalChangerThread(int threadNumber, Phaser phaser) {
        this.threadNumber = threadNumber;
        this.phaser = phaser;
        phaser.register();
    }

    @Override
    public void run() {
        Matrix matrix = Matrix.getInstance();
        Optional<MatrixCell> matrixCell;
        while (true) {
            matrixCell = matrix.getUnchangedDiagonalElement();
            if (matrixCell.isPresent()) {
                matrixCell.get().setCellValue(threadNumber);
                LOGGER.info("Matrix cell was initialized with " + matrixCell.get().getCellValue());
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
                break;
            }
        }
        LOGGER.info(Thread.currentThread() + " was finished.");
    }
}
