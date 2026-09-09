package com.example.matriculadisciplina.Model;

public enum TipoUsuario{

    ALUNO(1, "Aluno", "Aluno"),
    PROFESSOR(2, "Professor", "Professor"),
    ADMINISTRADOR(3, "Administrador", "Admin");

    private final int idTipoUsuario;
    private final String nome;
    private final String sigla;
    
    TipoUsuario(int idTipoUsuario, String nome, String sigla) {
        this.idTipoUsuario = idTipoUsuario;
        this.nome = nome;
        this.sigla = sigla;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }
}