package com.example.matriculadisciplina.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matricula")
public class Matricula {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_matricula")
    private int idMatricula;

    @ManyToOne @JoinColumn(name = "id_oferta", nullable = false)
    private OfertaDisciplina oferta;

    @ManyToOne @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Column(name = "status", nullable = false)
    private String status = "ATIVA";

    public int getIdMatricula() { return idMatricula; }
    public void setIdMatricula(int idMatricula) { this.idMatricula = idMatricula; }

    public OfertaDisciplina getOferta() { return oferta; }
    public void setOferta(OfertaDisciplina oferta) { this.oferta = oferta; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}
