package com.example.piper.piperchat.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity<Object> handleAuthExceptions(TokenExpiredException exception, WebRequest request){
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
