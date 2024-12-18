package com.idi.backend.infrastructure.config;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.idi.backend.adpters.output.response.ResponseRest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseRest> handleRuntimeException(RuntimeException ex) {
        ResponseRest response = new ResponseRest();
        response.setMetadata("Respuesta nok", "500", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseRest> handleGenericException(Exception ex) {
        ResponseRest response = new ResponseRest();
        response.setMetadata("Respuesta nok", "500", "Error inesperado en el servidor");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
