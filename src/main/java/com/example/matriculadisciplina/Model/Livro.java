package com.example.matriculadisciplina.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "livro")
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_livro")
    private int idLivro;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "editora")
    private String editora;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @Column(name = "edicao")
    private String edicao;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "sinopse", columnDefinition = "TEXT")
    private String sinopse;

    @Column(name = "capa_imagem")
    private String capaImagem;

    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getEdicao() { return edicao; }
    public void setEdicao(String edicao) { this.edicao = edicao; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }

    public String getCapaImagem() { return capaImagem; }
    public void setCapaImagem(String capaImagem) { this.capaImagem = capaImagem; }

}
