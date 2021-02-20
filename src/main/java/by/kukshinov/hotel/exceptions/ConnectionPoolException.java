package by.kukshinov.hotel.exceptions;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
