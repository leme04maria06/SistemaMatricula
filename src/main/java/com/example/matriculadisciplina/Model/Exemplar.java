package com.example.matriculadisciplina.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplar")
public class Exemplar {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_exemplar")
    private int idExemplar;

    @ManyToOne @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @Column(name = "tombo", nullable = false, unique = true)
    private String tombo;

    @Enumerated(EnumType.STRING) @Column(name = "status", nullable = false)
    private StatusExemplar status = StatusExemplar.DISPONIVEL;

    public int getIdExemplar() { return idExemplar; }
    public void setIdExemplar(int idExemplar) { this.idExemplar = idExemplar; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public String getTombo() { return tombo; }
    public void setTombo(String tombo) { this.tombo = tombo; }

    public StatusExemplar getStatus() { return status; }
    public void setStatus(StatusExemplar status) { this.status = status; }

}
