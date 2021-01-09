package br.com.compasso.backend.dto;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ClientePatchDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void ClientePatchDto_shouldNotHasConstraintViolation() {
        ClientePatchDto param = new ClientePatchDto();
        param.setNome("nome");

        Set<ConstraintViolation<ClientePatchDto>> violations = validator.validate(param);

        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void ClientePatchDto_shouldHasConstraintViolationNomeBlank() {
        ClientePatchDto param = new ClientePatchDto();

        Set<ConstraintViolation<ClientePatchDto>> violations = validator.validate(param);

        Assert.assertEquals(violations.size(), 1);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "nome");
    }

    @Test
    public void ClientePatchDto_shouldHasConstraintViolationNomeSize() {
        ClientePatchDto param = new ClientePatchDto();
        param.setNome("TEXTO_COM_MAIS_DE_100_CARACTERES TEXTO_COM_MAIS_DE_100_CARACTERES TEXTO_COM_MAIS_DE_100_CARACTERES TEXTO_COM_MAIS_DE_100_CARACTERES");

        Set<ConstraintViolation<ClientePatchDto>> violations = validator.validate(param);

        Assert.assertEquals(violations.size(), 1);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "nome");
    }
}
