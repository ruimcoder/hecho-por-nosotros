package com.folcamp.hechopornosotros.config.exceptions;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> defaultHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error generico",
                e.getMessage(),
                request.getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadRequestException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseEntity<ErrorMessage> badRequestHandler(HttpServletRequest request, Exception e) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                "BAD_REQUEST",
                e.getMessage(),
                request.getRequestURI()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class, FirebaseAuthException.class})
    @ResponseBody
    public ResponseEntity<ErrorMessage> notFoundHandler(HttpServletRequest request, Exception e) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND",
                e.getMessage(),
                request.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> notAuthorizedHandler(HttpServletRequest request, Exception e) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.UNAUTHORIZED.value(),
                "NOT_AUTHORIZED",
                e.getMessage(),
                request.getRequestURI()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> accessDeniedHandler(HttpServletRequest request, Exception e) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.FORBIDDEN.value(),
                "FORBIDDEN",
                e.getMessage(),
                request.getRequestURI()),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiError> methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String detail = "Fallo en algún campo";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, detail, errors);

        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
