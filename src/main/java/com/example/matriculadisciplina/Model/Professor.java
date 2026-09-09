package com.example.matriculadisciplina.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Professor extends Pessoa {

    @Column(name = "siape")
    private String siape;

    @Column(name = "area")
    private String area;

    @Column(name = "formacao")
    private String formacao;

    public int getIdProfessor() {
        return getId_pessoa();
    }

    public void setIdProfessor(int idProfessor) {
        setId_pessoa(idProfessor);
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}