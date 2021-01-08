package br.com.compasso.backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientePatchDto extends IdDto {

    @ApiModelProperty(value = "Nome do cliente", required = true, example = "string")
    @NotBlank
    @Size(max = 100, min = 1)
    private String nome;
}
