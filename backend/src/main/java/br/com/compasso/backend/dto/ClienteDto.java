package br.com.compasso.backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDto extends IdDto {

    @ApiModelProperty(value = "Nome do cliente", required = true, example = "string")
    @NotBlank
    @Size(max = 100, min = 1)
    private String nome;

    @ApiModelProperty(value = "Sexo do cliente", required = true, example = "string")
    @NotBlank
    @Size(max = 30, min = 1)
    private String sexo;

    @ApiModelProperty(value = "Data de nascimento do cliente", required = true, example = "01/01/2020")
    @PastOrPresent
    @NotNull
    private LocalDate nascimento;

    @ApiModelProperty(value = "Idade do cliente", required = true, example = "1")
    @NotNull
    @PositiveOrZero
    private Integer idade;

    @ApiModelProperty(value = "Id da cidade", required = true, example = "1")
    @NotNull
    @Positive
    private Long cidade;

    @AssertTrue(message = "A idade não está de acordo com a data de nascimento")
    private boolean isIdade() {
        if (idade != null && idade >= 0 && nascimento != null) {
            LocalDate now = LocalDate.now();
            Integer idadeCalculada = now.getYear() - nascimento.getYear();
            if (now.getMonth().getValue() < nascimento.getMonth().getValue()) {
                idadeCalculada--;
            } else if (now.getDayOfMonth() < now.getDayOfMonth()) {
                idadeCalculada--;
            }
            return idade.equals(idadeCalculada);
        }
        return true;
    }
}
