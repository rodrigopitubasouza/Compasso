package br.com.compasso.backend.exception;

import br.com.compasso.backend.enums.ErrorMessageEnum;
import org.springframework.http.HttpStatus;

public class MethodNotImplemented extends ValidationException{

    private static final long serialVersionUID = 1L;
    public MethodNotImplemented(String message) {
        super(ErrorMessageEnum.METODO_NAO_IMPLEMENTADO.getMessage(message), HttpStatus.NOT_FOUND);
    }
}
