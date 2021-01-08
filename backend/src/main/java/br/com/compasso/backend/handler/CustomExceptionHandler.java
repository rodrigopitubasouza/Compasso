package br.com.compasso.backend.handler;


import br.com.compasso.backend.exception.BusinessException;
import br.com.compasso.backend.exception.ValidationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(1)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "JSON da requisição está com formato errado.";
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), Arrays.asList(message), ex.getLocalizedMessage(), null), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiValidationError> messageErrosField = getMessagesOfField(ex);
        List<String> messageErrorsGlobal = getMessagesGlobal(ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), messageErrorsGlobal, ex.getLocalizedMessage(), messageErrosField);

        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<Object> handleException(BusinessException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, LocalDateTime.now(), Arrays.asList(ex.getMessage()), ex.getLocalizedMessage(), null);
        return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ValidationException.class})
    protected ResponseEntity<Object> handleException(ValidationException ex) {
        ApiError apiError = new ApiError(ex.getStatus(), LocalDateTime.now(), Arrays.asList(ex.getMessage()), ex.getLocalizedMessage(), null);
        return new ResponseEntity<>(apiError, ex.getStatus());
    }

    private List<ApiValidationError> getMessagesOfField(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ApiValidationError(error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    private List<String> getMessagesGlobal(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getGlobalErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
