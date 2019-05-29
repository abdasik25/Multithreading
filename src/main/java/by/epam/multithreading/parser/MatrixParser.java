package by.epam.multithreading.parser;

import java.util.Arrays;
import java.util.List;

public class MatrixParser {
    private static final String MATRIX_SPACES_REGEX_PATTERN = " ";

    public int[][] parseMatrix(List<String> data) {
        int[][] matrix = new int[data.size()][data.size()];
        for (int i = 0; i < matrix.length; i++) {
            List<String> numbers = Arrays.asList(data.get(i).split(MATRIX_SPACES_REGEX_PATTERN));
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = Integer.parseInt(numbers.get(j));
            }
        }
        return matrix;
    }
}
