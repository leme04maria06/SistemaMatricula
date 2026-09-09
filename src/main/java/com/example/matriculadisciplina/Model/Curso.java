package com.example.matriculadisciplina.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_curso;
    @Column(name = "nome")
    private String nome;
    @Column(name = "ano_inicio")
    private int ano_inicio;
    public int getIdCurso() {
        return id_curso;
    }
    public void setIdCurso(int idCurso) {
        this.id_curso = idCurso;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getAno_inicio() {
        return ano_inicio;
    }
    public void setAno_inicio(int ano_inicio) {
        this.ano_inicio = ano_inicio;
    }

}
