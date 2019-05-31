/**
 * Created by Alexander Lomat on 31.05.19
 * Version 0.0.2
 */

package by.epam.multithreading.exception;

public class InvalidMatrixDataException extends Exception {

    public InvalidMatrixDataException() {
        super();
    }

    public InvalidMatrixDataException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
