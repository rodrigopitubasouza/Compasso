package br.com.compasso.backend.dto;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class CidadeDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void CidadeDto_shouldNotHasConstraintViolation() {
        CidadeDto param = new CidadeDto();
        param.setNome("nome");
        param.setEstado("estado");

        Set<ConstraintViolation<CidadeDto>> violations = validator.validate(param);

        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void CidadeDto_shouldHasConstraintViolationNomeBlank() {
        CidadeDto param = new CidadeDto();
        param.setEstado("estado");

        Set<ConstraintViolation<CidadeDto>> violations = validator.validate(param);

        Assert.assertEquals(violations.size(), 1);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "nome");
    }

    @Test
    public void CidadeDto_shouldHasConstraintViolationNomeSize() {
        CidadeDto param = new CidadeDto();
        param.setEstado("estado");
        param.setNome("TEXTO_COM_MAIS_DE_100_CARACTERES TEXTO_COM_MAIS_DE_100_CARACTERES TEXTO_COM_MAIS_DE_100_CARACTERES TEXTO_COM_MAIS_DE_100_CARACTERES");

        Set<ConstraintViolation<CidadeDto>> violations = validator.validate(param);

        Assert.assertEquals(violations.size(), 1);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "nome");
    }

    @Test
    public void CidadeDto_shouldHasConstraintViolationEstadoBlank() {
        CidadeDto param = new CidadeDto();
        param.setNome("nome");

        Set<ConstraintViolation<CidadeDto>> violations = validator.validate(param);

        Assert.assertEquals(violations.size(), 1);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "estado");
    }

    @Test
    public void CidadeDto_shouldHasConstraintViolationEstadoSize() {
        CidadeDto param = new CidadeDto();
        param.setNome("nome");
        param.setEstado("TEXTO_COM_MAIS_DE_30_CARACTERES");

        Set<ConstraintViolation<CidadeDto>> violations = validator.validate(param);

        Assert.assertEquals(violations.size(), 1);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "estado");
    }
}
