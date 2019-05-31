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
