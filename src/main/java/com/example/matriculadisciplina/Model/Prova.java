package com.example.matriculadisciplina.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prova")
public class Prova {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_prova")
    private int idProva;

    @ManyToOne @JoinColumn(name = "id_oferta", nullable = false)
    private OfertaDisciplina oferta;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "conteudo", nullable = false)
    private String conteudo;

    @Column(name = "peso", nullable = false)
    private Double peso = 1.0;

    public int getIdProva() { return idProva; }
    public void setIdProva(int idProva) { this.idProva = idProva; }

    public OfertaDisciplina getOferta() { return oferta; }
    public void setOferta(OfertaDisciplina oferta) { this.oferta = oferta; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

}
