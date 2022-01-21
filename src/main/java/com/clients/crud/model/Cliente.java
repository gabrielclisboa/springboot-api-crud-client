package com.clients.crud.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "inscricao",nullable = false,length =18)
    private String inscricao;

    @Column(name = "nome",nullable = false,length =50)
    private String nome;

    @Column(name = "apelido",nullable = false,length =30)
    private String apelido;

    @Column(columnDefinition = "ENUM('ATIVADO', 'DESATIVADO', 'SUSPENSO')",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente")
    private List<Email> email = new ArrayList<Email>();

}
