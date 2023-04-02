package fr.sali.cantine.service.exception;

public class InvalidCode  extends   Exception{

    public InvalidCode() {
    }

    public InvalidCode(String message) {
        super(message);
    }

    public InvalidCode(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCode(Throwable cause) {
        super(cause);
    }

    public InvalidCode(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
