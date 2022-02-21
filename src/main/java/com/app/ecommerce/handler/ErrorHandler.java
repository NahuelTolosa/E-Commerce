package com.app.ecommerce.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    //Manejo de excepción en caso de no encontrar un producto buscado por su ID
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorInfo> noSuchElementException(HttpServletRequest request, NoSuchElementException e) {
        ErrorInfo errorInfo = new ErrorInfo("No existe ningun producto con ese ID", HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    //Manejo de excepción en caso de no enviar un body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorInfo> httpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {
        ErrorInfo errorInfo = new ErrorInfo("Debe enviar un body que coincida con el solicitado", HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    //Manejo de excepción en caso de no enviar bien los parámetros
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorInfo> missingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {

        ErrorInfo errorInfo = null;

        if(request.getRequestURI().equals("/cart/buy"))
            errorInfo = new ErrorInfo("Debe enviar un email y una direccion como parametros", HttpStatus.BAD_REQUEST.value(), request.getRequestURI());

        if(request.getRequestURI().equals("/cart/get"))
        errorInfo = new ErrorInfo("Debe enviar un email como parametro", HttpStatus.BAD_REQUEST.value(), request.getRequestURI());

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(JsonProcessingException.class)

}