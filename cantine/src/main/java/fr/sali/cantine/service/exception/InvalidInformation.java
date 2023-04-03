package fr.sali.cantine.service.exception;

public class InvalidInformation  extends   Exception{
    public InvalidInformation() {
    }

    public InvalidInformation(String message) {
        super(message);
    }

    public InvalidInformation(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInformation(Throwable cause) {
        super(cause);
    }

    public InvalidInformation(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
