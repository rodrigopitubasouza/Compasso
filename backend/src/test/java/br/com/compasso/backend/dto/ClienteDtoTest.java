package br.com.compasso.backend.dto;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

public class ClienteDtoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void ClienteDto_shouldNotHasConstraintViolation() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setSexo("sexo");
        param.setNascimento(LocalDate.now());
        param.setIdade(0);
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);

        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationNomeBlank() {
        ClienteDto param = new ClienteDto();
        param.setSexo("sexo");
        param.setNascimento(LocalDate.now());
        param.setIdade(0);
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "nome");
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationNomeSize() {
        ClienteDto param = new ClienteDto();
        param.setNome("TEXTO_COM_MAIS_DE_100_CARACTERES TEXTO_COM_MAIS_DE_100_CARACTERES TEXTO_COM_MAIS_DE_100_CARACTERES TEXTO_COM_MAIS_DE_100_CARACTERES");
        param.setSexo("sexo");
        param.setNascimento(LocalDate.now());
        param.setIdade(0);
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "nome");
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationSexoBlank() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setNascimento(LocalDate.now());
        param.setIdade(0);
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "sexo");
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationSexoSize() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setSexo("TEXTO_COM_MAIS_DE_30_CARACTERES");
        param.setNascimento(LocalDate.now());
        param.setIdade(0);
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "sexo");
    }


    @Test
    public void ClienteDto_shouldHasConstraintViolationNascimentoNull() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setSexo("sexo");
        param.setIdade(0);
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "nascimento");
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationNascimentoFuture() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setSexo("sexo");
        param.setNascimento(LocalDate.now().plusDays(1));
        param.setIdade(0);
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "nascimento");
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationIdadeNull() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setSexo("sexo");
        param.setNascimento(LocalDate.now());
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "idade");
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationIdadeNegative() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setSexo("sexo");
        param.setNascimento(LocalDate.now());
        param.setIdade(-1);
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "idade");
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationCidadeZero() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setSexo("sexo");
        param.setNascimento(LocalDate.now());
        param.setIdade(0);
        param.setCidade(0L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "cidade");
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationCidadeNegative() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setSexo("sexo");
        param.setNascimento(LocalDate.now());
        param.setIdade(0);
        param.setCidade(-1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "cidade");
    }

    @Test
    public void ClienteDto_shouldHasConstraintViolationIdadeNotValid() {
        ClienteDto param = new ClienteDto();
        param.setNome("nome");
        param.setSexo("sexo");
        param.setNascimento(LocalDate.now());
        param.setIdade(10);
        param.setCidade(1L);

        Set<ConstraintViolation<ClienteDto>> violations = validator.validate(param);
        ConstraintViolation constraintViolation = violations.stream().findFirst().get();

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(constraintViolation.getPropertyPath().toString(), "idade");
    }
}
