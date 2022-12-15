package br.alunos;

@SuppressWarnings("unused")
public class Aluno {
    private String  nome;
    private int id, faltas, nota_final;

    public Aluno(String id, String nome, String faltas, String nota_final) {
        this.id = Integer.parseInt(id);
        this.nome = nome;
        this.faltas =  Integer.parseInt(faltas);
        this.nota_final =  Integer.parseInt(nota_final);
    }
    public Aluno(int id, String nome, int faltas, int nota_final) {
        this.id = id;
        this.nome = nome;
        this.faltas = faltas;
        this.nota_final = nota_final;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFaltas() {
        return faltas;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " - ID: " + id;
    }

    public void setFaltas(String faltas) {
        this.faltas =  Integer.parseInt(faltas);
    }
    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getNota_final() {
        return nota_final;
    }

    public void setNota_final(String nota_final) {
        this.nota_final =  Integer.parseInt(nota_final);
    }
    public void setNota_final(int nota_final) {
        this.nota_final = nota_final;
    }
}
