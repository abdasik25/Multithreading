/**
 * Created by Alexander Lomat on 29.05.19
 * Version 0.0.1
 */

package by.epam.multithreading.matrix;

import java.util.Arrays;
import java.util.List;

class MatrixParser {

    private static final String MATRIX_SPACES_REGEX_PATTERN = " ";

    public MatrixCell[][] parseMatrix(List<String> data) {
        MatrixCell[][] matrix = new MatrixCell[data.size()][data.size()];
        for (int i = 0; i < matrix.length; i++) {
            List<String> numbers = Arrays.asList(data.get(i).split(MATRIX_SPACES_REGEX_PATTERN));
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new MatrixCell(Integer.parseInt(numbers.get(j)));
            }
        }
        return matrix;
    }
}
