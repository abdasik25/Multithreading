package by.epam.multithreading.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class MatrixValidator implements Validator {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MATRIX_NUMBERS_REGEX_PATTERN = "^(-?\\d+ ){6}$";
    private static final String MATRIX_SPACES_REGEX_PATTERN = " ";

    private boolean validateLine(String line) {
        return line != null && line.matches(MATRIX_NUMBERS_REGEX_PATTERN);
    }

    @Override
    public boolean validate(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            List<String> numbers = Arrays.asList(list.get(i).split(MATRIX_SPACES_REGEX_PATTERN));
            for (int j = 0; j < numbers.size(); j++) {
                if (!validateLine(list.get(i))) {
                    LOGGER.error("Matrix was not validated. Line: " + list.get(i) + " is not valid.");
                    return false;
                }
                if (list.size() != numbers.size()) {
                    LOGGER.error("Matrix is not square.");
                    return false;
                }
                if (i == j && !numbers.get(j).equals("0")) {
                    LOGGER.error("Main diagonal element at [" + i + "]" + "[" + j + "] != 0");
                    return false;
                }
            }

        }
        return true;
    }
}
