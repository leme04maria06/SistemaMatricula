package com.example.matriculadisciplina.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Aluno extends Pessoa {

    @Column(name = "prontuario")
    private String prontuario;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "contato_responsavel")
    private String contatoResponsavel;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public int getIdAluno() {
        return getId_pessoa();
    }

    public void setIdAluno(int idAluno) {
        setId_pessoa(idAluno);
    }

    public Integer getIdCurso() {
        if (curso != null) {
            return curso.getIdCurso();
        }
        return 0;
    }

    public void setIdCurso(Integer idCurso) {
        if (idCurso != null) {
            if (this.curso == null) {
                this.curso = new Curso();
            }
            this.curso.setIdCurso(idCurso);
        } else {
            this.curso = null;
        }
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getContatoResponsavel() {
        return contatoResponsavel;
    }

    public void setContatoResponsavel(String contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }
}