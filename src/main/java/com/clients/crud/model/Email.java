package com.clients.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "email")
@NamedQueries({
        @NamedQuery(name = Email.RECUPERAR_ANTERIOR_BY_REGRA_ID, query = "SELECT e FROM Email e"
                + " JOIN e.cliente c" + " WHERE c.id not in (:ids)" + " AND c.id = :id") })

public class Email {

    public static final String RECUPERAR_ANTERIOR_BY_REGRA_ID = "RegraValidacao.recuperarAnteriorByRegraId";

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_email")

    private Long id;

    @Column(name = "categoria",nullable = false,length =20)
    private String categoria;

    @Column(name = "nome",nullable = false,length =20)
    private String nome;

    @Column(name = "email",nullable = false,length =30)
    private String email;

    @ManyToOne()
    @JoinColumn(name = "id_cliente")
    @JsonBackReference
    private Cliente cliente;

}