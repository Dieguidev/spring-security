package com.dieguidev.spring_security.exception;

import com.dieguidev.spring_security.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejador de excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest request, Exception e) {
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(e.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error interno en el servidor");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }


    // Manejador de excepciones de acceso denegado solo cuando se utilizan decoradores en los controladores
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(e.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Acceso denegado. No tienes los permisos necesarios para acceder a esta función. " +
                "Por favor, contacta al administrador si crees que esto es un error.");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
    }


    // Manejador de excepciones de validación de argumentos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(HttpServletRequest request,
                                                                    MethodArgumentNotValidException e) {
        ApiError apiError = new ApiError();
        apiError.setBackendMessage(e.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error en la peticion enviada");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
