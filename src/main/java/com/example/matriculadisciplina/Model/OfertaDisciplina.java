package com.example.matriculadisciplina.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "oferta_disciplina")
public class OfertaDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_oferta;

    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @Column(name = "ano")
    private int ano;

    @Column(name = "semestre")
    private int semestre;


    public int getIdOferta() {
        return id_oferta;
    }

    public void setIdOferta(int idOferta) {
        this.id_oferta = idOferta;
    }


    public int getIdDisciplina() {

        if (disciplina != null) {
            return disciplina.getIdDisciplina();
        }

        return 0;
    }

    public void setIdDisciplina(int id) {

        if (this.disciplina == null) {
            this.disciplina = new Disciplina();
        }

        this.disciplina.setIdDisciplina(id);
    }


    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }


    public int getIdProfessor() {

        if (professor != null) {
            return professor.getIdProfessor();
        }

        return 0;
    }

    public void setIdProfessor(int id) {

        if (this.professor == null) {
            this.professor = new Professor();
        }

        this.professor.setIdProfessor(id);
    }


    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }


    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }


    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
}