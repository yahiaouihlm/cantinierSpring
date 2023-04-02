package fr.sali.cantine.service.exception;

public class ExpiredCode  extends  Exception {
    public ExpiredCode() {
    }

    public ExpiredCode(String message) {
        super(message);
    }

    public ExpiredCode(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredCode(Throwable cause) {
        super(cause);
    }

    public ExpiredCode(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
