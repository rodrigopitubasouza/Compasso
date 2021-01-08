package br.com.compasso.backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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
    private Integer idade;
}
