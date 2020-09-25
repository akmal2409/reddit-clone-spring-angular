package tech.talci.redditclonespring.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.talci.redditclonespring.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest webRequest){

        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
