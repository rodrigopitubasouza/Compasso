package br.com.compasso.backend.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public ValidationException(String message, HttpStatus httpStatus) {
        super(message);
        status = httpStatus;
    }

    public ValidationException(String message) {
        super(message);
        status = HttpStatus.BAD_REQUEST;
    }
}