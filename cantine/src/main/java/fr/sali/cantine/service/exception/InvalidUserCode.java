package fr.sali.cantine.service.exception;

public class InvalidUserCode  extends   Exception {
    public InvalidUserCode() {
    }

    public InvalidUserCode(String message) {
        super(message);
    }

    public InvalidUserCode(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserCode(Throwable cause) {
        super(cause);
    }

    public InvalidUserCode(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
