package by.epam.multithreading.thread;

import by.epam.multithreading.exception.DataFileNotFoundException;
import by.epam.multithreading.reader.DataFileReader;
import by.epam.multithreading.validator.ThreadValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Phaser;
import java.util.stream.Collectors;

public class ThreadCreator {

    private static Logger LOGGER = LogManager.getLogger();


    public List<DiagonalChangerThread> create(String path) {
        DataFileReader dataFileReader = new DataFileReader();
        ThreadValidator threadValidator = new ThreadValidator();
        List<String> matrixDigits = null;
        try {
            matrixDigits = dataFileReader.read(path)
                    .stream()
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (DataFileNotFoundException e) {
            e.printStackTrace();
        }

        List<DiagonalChangerThread> diagonalChangers = null;
        if (threadValidator.validate(matrixDigits)) {
            Phaser phaser = new Phaser();
            diagonalChangers = matrixDigits
                    .stream()
                    .map(Integer::parseInt)
                    .map(d -> new DiagonalChangerThread(d, phaser))
                    .collect(Collectors.toList());
        } else {
            LOGGER.warn("Input data for threads is incorrect");
        }
        return diagonalChangers;
    }
}
