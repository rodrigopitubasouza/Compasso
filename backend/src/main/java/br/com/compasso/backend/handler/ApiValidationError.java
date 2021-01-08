package br.com.compasso.backend.handler;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiValidationError {
    private String field;
    private Object rejectedValue;
    private String message;
}