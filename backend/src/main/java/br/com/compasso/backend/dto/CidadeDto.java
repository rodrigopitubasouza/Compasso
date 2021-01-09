package br.com.compasso.backend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class CidadeDto extends IdDto{

    @NotBlank
    @Size(max = 100, min = 1)
    private String nome;

    @NotBlank
    @Size(max = 30, min = 1)
    private String estado;
}