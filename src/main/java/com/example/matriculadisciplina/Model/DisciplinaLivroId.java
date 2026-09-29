package com.example.matriculadisciplina.Model;

import java.io.Serializable;
import java.util.Objects;

public class DisciplinaLivroId implements Serializable {
    private int disciplina;
    private int livro;
    public DisciplinaLivroId() {}
    public DisciplinaLivroId(int disciplina, int livro) { this.disciplina = disciplina; this.livro = livro; }
    public int getDisciplina() { return disciplina; }
    public void setDisciplina(int disciplina) { this.disciplina = disciplina; }
    public int getLivro() { return livro; }
    public void setLivro(int livro) { this.livro = livro; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DisciplinaLivroId that)) return false;
        return disciplina == that.disciplina && livro == that.livro;
    }
    @Override public int hashCode() { return Objects.hash(disciplina, livro); }
}
