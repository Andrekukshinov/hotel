package by.kukshinov.hotel.exceptions;

public class PoolOverflowException extends RuntimeException {
    public PoolOverflowException() {
    }

    public PoolOverflowException(String message) {
        super(message);
    }

    public PoolOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolOverflowException(Throwable cause) {
        super(cause);
    }
}
