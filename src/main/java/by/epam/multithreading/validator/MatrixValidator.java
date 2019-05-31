/**
 * Created by Alexander Lomat on 29.05.19
 * Version 0.0.1
 */

package by.epam.multithreading.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class MatrixValidator implements Validator {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MATRIX_NUMBERS_REGEX_PATTERN = "^(-?\\d+ ){8,12}$"; //from 8x8 to 12x12
    private static final String MATRIX_SPACES_REGEX_PATTERN = " ";

    @Override
    public boolean validate(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            List<String> numbers = Arrays.asList(list.get(i).split(MATRIX_SPACES_REGEX_PATTERN));
            for (int j = 0; j < numbers.size(); j++) {
                if (list.get(i) == null || !list.get(i).matches(MATRIX_NUMBERS_REGEX_PATTERN)) {
                    LOGGER.error("Matrix was not validated. Line: " + list.get(i) + " is not valid.");
                    return false;
                }
                if (list.size() != numbers.size()) {
                    LOGGER.error("Matrix size is not valid after validation.");
                    return false;
                }
                if (i == j && !numbers.get(j).equals("0")) {
                    LOGGER.error("Main diagonal element at [" + i + "]" + "[" + j + "] is not 0.");
                    return false;
                }
            }

        }
        return true;
    }
}
