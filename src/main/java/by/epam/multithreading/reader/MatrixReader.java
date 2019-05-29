package by.epam.multithreading.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixReader {
    private static Logger LOGGER = LogManager.getLogger();

    public List<String> readMatrixFromFile(String filePath) throws FileNotFoundException {
        if (filePath == null || !Paths.get(filePath).toFile().exists()) {
            throw new FileNotFoundException("File with filepath " + filePath + " doesn't exist.");
        }
        try(Stream<String> lines = Files.newBufferedReader(Paths.get(filePath)).lines()) {
            return lines.map(l -> l.trim().concat(" ")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileNotFoundException("File wasn't found");
        }

    }


}
