package fr.sali.cantine.service.exception;


import fr.sali.cantine.dto.out.ExceptionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class HandlerException {

    private static final Logger LOG = LogManager.getLogger();
    @ExceptionHandler(MealException.class)
    public ResponseEntity<ExceptionDto> exceptionHandler(MealException exception){
        HandlerException.LOG.info("EXCEPTION HAS BEEN THROWED   :  {}", exception.getMessage());
        return   new ResponseEntity<ExceptionDto>(new ExceptionDto("INVALID ARGUMENT(S)"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImagePathException.class)
    public ResponseEntity<ExceptionDto> exceptionHandler(ImagePathException exception){
        HandlerException.LOG.info("EXCEPTION HAS BEEN THROWED, May Be The Directory Has Not Been Found : {}", exception.getMessage());
        return   new ResponseEntity<ExceptionDto>(new ExceptionDto("SERVER ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionDto> exceptionHandler(IOException exception){
        HandlerException.LOG.info("EXCEPTION HAS BEEN THROWED OF IOEXCEPTION : {}", exception.getMessage());
        return   new ResponseEntity<ExceptionDto>(new ExceptionDto("SERVER ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(RemoveMealException.class)
    public ResponseEntity<ExceptionDto> exceptionHandler(RemoveMealException exception){
        HandlerException.LOG.info("EXCEPTION HAS BEEN THROWED : {}", exception.getMessage());
        return   new ResponseEntity<ExceptionDto>(new ExceptionDto("CAN NOT DELETE THE MEAL BECAUSE ITS  PRESENTE IN SOME MENU"), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
