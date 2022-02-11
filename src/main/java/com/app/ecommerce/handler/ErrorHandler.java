package com.app.ecommerce.handler;

import com.app.ecommerce.controller.ProductController;
import com.app.ecommerce.interceptor.AfterInterception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    //Manejo de excepción en caso de no encontrar un producto buscado por su ID
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorInfo> noSuchElementException(HttpServletRequest request, NoSuchElementException e) {
        ErrorInfo errorInfo = new ErrorInfo("No existe ningun producto con ese ID", HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

}