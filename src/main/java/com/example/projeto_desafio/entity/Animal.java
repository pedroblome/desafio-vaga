package com.example.projeto_desafio.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "animal")
@Getter
@Setter
@NoArgsConstructor
public class Animal {

    @Id
    private Integer id;
    private String nome;
    private String descricao;
    private String urlImagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
