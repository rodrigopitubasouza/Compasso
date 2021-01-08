package br.com.compasso.backend.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_CLIENTE")
public class Cliente extends IdEntity{

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "NASCIMENTO")
    private LocalDate nascimento;

    @Column(name = "IDADE")
    private Integer idade;
}