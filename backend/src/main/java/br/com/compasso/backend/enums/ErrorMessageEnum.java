package br.com.compasso.backend.enums;

import java.text.MessageFormat;

public enum ErrorMessageEnum {

    ENTIDADE_INEXISTENTE("Não existe {0} para o id {1}."),
    IDENTIFICADOR_INVALIDO("O identificador informado é inválido.");

    private String message;

    ErrorMessageEnum(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(final Object... params) {
        return MessageFormat.format(getMessage(), params);
    }
}
