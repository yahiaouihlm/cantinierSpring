package fr.sali.cantine.service.exception;

public class MealException extends  Exception{
    public MealException() {
    }

    public MealException(String message) {
        super(message);
    }

    public MealException(String message, Throwable cause) {
        super(message, cause);
    }

    public MealException(Throwable cause) {
        super(cause);
    }

    public MealException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
