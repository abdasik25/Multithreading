package by.epam.multithreading.reader;

import by.epam.multithreading.exception.DataFileNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataFileReader {
    private static Logger LOGGER = LogManager.getLogger();

    public List<String> read(String filePath) throws DataFileNotFoundException {
        if (filePath == null || !Paths.get(filePath).toFile().exists()) {
            LOGGER.fatal(filePath + " doesn't exists.");
            throw new DataFileNotFoundException(filePath + " doesn't exists.");
        }
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(filePath)).lines()) {
            LOGGER.info("File at " + filePath + " was read.");
            return lines.map(l -> l.trim().concat(" ")).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.fatal("Can't read file.");
            throw new DataFileNotFoundException("Can't read file.");
        }
    }
}