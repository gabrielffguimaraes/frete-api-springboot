package com.gabrielffguimaraes.freteapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CorreioLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_correio_log")
    private Long id;

    @Column(name= "servico")
    private String servico;

    @Column(name= "valor")
    private String valor;

    @Column(name= "prazoEntrega")
    private int prazoEntrega;

    @Column(name= "destinatario")
    private String destinatario;

    @Column(name= "cepOrigem")
    private String cepOrigem;

    @Column(name= "cepDestino")
    private String cepDestino;

    @Column(name= "created_at")
    private Date dataCriacao;

    @PrePersist
    private void prePersist() {
        this.dataCriacao = new Date();
    }
}
