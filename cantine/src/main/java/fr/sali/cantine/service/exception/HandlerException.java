package fr.sali.cantine.service.exception;


import fr.sali.cantine.dto.out.ExceptionDto;
import fr.sali.cantine.springsecurity.DelegatedAuthenticationEntryPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class HandlerException{

    private static final Logger LOG = LogManager.getLogger();

   /* @ExceptionHandler({AuthenticationException.class} )
    public ResponseEntity<ExceptionDto> handleAuthenticationException(AuthenticationException ex) {
        System.out.println(ex);

        return new ResponseEntity<ExceptionDto>(new ExceptionDto(" Conmpte disactiver ") , HttpStatus.UNAUTHORIZED);
    }*/

    @ExceptionHandler({InsufficientAuthenticationException.class} )
    public ResponseEntity<ExceptionDto> handleAuthenticationException(InsufficientAuthenticationException ex) {
        System.out.println(ex);
        System.out.println(ex.getMessage());
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(" La vrais  disactivation") , HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(MenuException.class)
    public ResponseEntity<ExceptionDto> exceptionHandler (MenuException exception){
        HandlerException.LOG.info("EXCEPTION HAS BEEN  THROWN ABOUT  :  {}", exception.getMessage());
        return new ResponseEntity<ExceptionDto>(new ExceptionDto("INVALID ARGUMENTS") , HttpStatus.BAD_REQUEST);
    }

    /*************************************** Meals Exceptions Handler ******************************************/
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
