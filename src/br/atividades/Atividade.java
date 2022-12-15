package br.atividades;

public class Atividade {
    private int id, nota;
    private String nome;

    public Atividade(int id, int nota, String nome) {
        this.id = id;
        this.nota = nota;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "  -  Nota: " + nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    @SuppressWarnings("unused")
    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
