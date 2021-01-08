package br.com.compasso.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class SystemDto {

    @ApiModelProperty(hidden = true)
    private LocalDateTime dtInclusao;

    @ApiModelProperty(hidden = true)
    private LocalDateTime dtAlteracao;

    @ApiModelProperty(hidden = true)
    private LocalDateTime dtExclusao;
}
