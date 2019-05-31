/**
 * Created by Alexander Lomat on 31.05.19
 * Version 0.0.2
 */

package by.epam.multithreading.exception;

public class DataFileNotFoundException extends Exception {

    public DataFileNotFoundException() {
        super();
    }

    public DataFileNotFoundException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
