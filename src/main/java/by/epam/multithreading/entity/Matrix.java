package by.epam.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Matrix {
    private static Logger LOGGER = LogManager.getLogger();
    private int[][] data;
    private static Matrix instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Semaphore semaphore = new Semaphore(6, true);;

//    {
//        isCreated = new AtomicBoolean(false);
//        reentrantLock = new ReentrantLock();
//        semaphore = new Semaphore(6, true);
//    }

    //TODO
    private Matrix() {

    }

    public static Matrix getInstance() {

        if (!isCreated.get()) {
            reentrantLock.lock();
        }
        try {
            if (instance == null) {
                instance = new Matrix();
            }
        } finally {
            reentrantLock.unlock();
        }

        return instance;
    }

    public int getRowSize() {
        return data.length;
    }

    public int getColumnSize() {
        return data[0].length;
    }

    public void setElement(int rowIndex, int columnIndex, int element) {
        try {
            semaphore.acquire();
            reentrantLock.lock();
            if (data[rowIndex][columnIndex] == 0) {
                data[rowIndex][columnIndex] = element;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            reentrantLock.unlock();
            semaphore.release();

            int oldElement = data[rowIndex][columnIndex];
            data[rowIndex][columnIndex] = element;
            LOGGER.info(oldElement + "at [" + rowIndex + "]" + "[" + columnIndex + "] was changed to " + element);
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                buffer.append(data[i][j]).append(" ");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
