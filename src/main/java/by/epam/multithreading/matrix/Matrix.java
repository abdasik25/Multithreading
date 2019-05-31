package by.epam.multithreading.matrix;

import by.epam.multithreading.exception.InvalidMatrixDataException;
import by.epam.multithreading.exception.DataFileNotFoundException;
import by.epam.multithreading.reader.DataFileReader;
import by.epam.multithreading.validator.MatrixValidator;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Matrix {

    private static Logger LOGGER = LogManager.getLogger();
    private MatrixCell[][] data;
    private static Matrix instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static String MATRIX_DATA_FILEPATH = "data/matrix_data";

    private Matrix() {
    }

    public static Matrix getInstance() {
        if (!isCreated.get()) {
            reentrantLock.lock();
            try {
                if (instance == null) {
                    try {
                        instance = initializeMatrix();
                    } catch (InvalidMatrixDataException e) {
                        e.printStackTrace();
                    }
                    isCreated.set(true);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return instance;
    }

    public Optional<MatrixCell> getUnchangedDiagonalElement() {
        reentrantLock.lock();
        try {
            for (int i = 0; i < data.length; i++) {
                if (!data[i][i].wasChanged()) {
                    data[i][i].setWasChanged(true);
                    LOGGER.info("Cell at [" + i + "]" + "[" + i + "] was changed.");
                    return Optional.of(data[i][i]);
                }
            }
            return Optional.empty();
        } finally {
            reentrantLock.unlock();
        }
    }

    private static Matrix initializeMatrix() throws InvalidMatrixDataException {
        MatrixParser matrixParser = new MatrixParser();
        DataFileReader dataFileReader = new DataFileReader();
        MatrixValidator matrixValidator = new MatrixValidator();
        List<String> matrixDigitsList = null;
        try {
            matrixDigitsList = dataFileReader.read(MATRIX_DATA_FILEPATH);
        } catch (DataFileNotFoundException e) {
            e.printStackTrace();
        }
        if (!matrixValidator.validate(matrixDigitsList)) {
            LOGGER.fatal("Invalid matrix data at file " + MATRIX_DATA_FILEPATH);
            throw new InvalidMatrixDataException();
        }
        Matrix matrix = new Matrix();
        matrix.data = matrixParser.parseMatrix(matrixDigitsList);
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                stringBuilder.append(data[i][j].getCellValue()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}