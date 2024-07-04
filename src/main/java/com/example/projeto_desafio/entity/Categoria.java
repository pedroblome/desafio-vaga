package com.example.projeto_desafio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
public class Categoria {
    @Id
    private Integer id;
    @Column
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animais;


}
