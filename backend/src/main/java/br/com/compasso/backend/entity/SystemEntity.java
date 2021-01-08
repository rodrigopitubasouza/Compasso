package br.com.compasso.backend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class SystemEntity implements Serializable {

    @Column(name = "dataInsercao")
    private LocalDateTime dataInsercao;

    @Column(name = "dataAlteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "dataExclusao")
    private LocalDateTime dataExclusao;
}
