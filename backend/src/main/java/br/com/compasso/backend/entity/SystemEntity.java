package br.com.compasso.backend.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public abstract class SystemEntity implements Serializable {

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "dataInsercao")
    private Timestamp dataInsercao;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "dataAlteracao")
    private Timestamp dataAlteracao;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "dataExclusao")
    private Timestamp dataExclusao;
}
