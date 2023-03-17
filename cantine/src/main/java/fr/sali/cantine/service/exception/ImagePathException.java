package fr.sali.cantine.service.exception;

public class ImagePathException extends   Exception {

    public ImagePathException(String message) {
        super(message);
    }

    public ImagePathException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImagePathException(Throwable cause) {
        super(cause);
    }

    public ImagePathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
