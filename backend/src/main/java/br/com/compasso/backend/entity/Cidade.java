package br.com.compasso.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "TB_CIDADE")
public class Cidade extends IdEntity{
    @Column(name = "NOME")
    private String nome;

    @Column(name = "ESTADO")
    private String estado;

    @OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Cliente> clientes = new ArrayList<>();

}