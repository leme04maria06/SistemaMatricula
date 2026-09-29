package com.example.matriculadisciplina.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "disciplina_livro")
@IdClass(DisciplinaLivroId.class)
public class DisciplinaLivro {
    @Id @ManyToOne @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    @Id @ManyToOne @JoinColumn(name = "id_livro")
    private Livro livro;

    @Enumerated(EnumType.STRING) @Column(name = "tipo", nullable = false)
    private TipoBibliografia tipo = TipoBibliografia.BASICA;

    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public TipoBibliografia getTipo() { return tipo; }
    public void setTipo(TipoBibliografia tipo) { this.tipo = tipo; }

}
