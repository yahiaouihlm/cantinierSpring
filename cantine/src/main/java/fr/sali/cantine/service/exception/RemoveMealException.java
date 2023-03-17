package fr.sali.cantine.service.exception;

public class RemoveMealException extends  Exception {

    public RemoveMealException() {
    }

    public RemoveMealException(String message) {
        super(message);
    }

    public RemoveMealException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoveMealException(Throwable cause) {
        super(cause);
    }

    public RemoveMealException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
