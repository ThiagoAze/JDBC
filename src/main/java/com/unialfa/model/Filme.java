package com.unialfa.model;

public class Filme {
    private Integer id;
    private String nome;
    private String diretor;

    public Filme(String nome, String diretor) {
        this.nome = nome;
        this.diretor = diretor;
    }

    public Filme(Integer id, String nome, String diretor) {
        this.id = id;
        this.nome = nome;
        this.diretor = diretor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Nome: " + nome + " Diretor: " + diretor;
    }

}
