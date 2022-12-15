package br.alunos;

@SuppressWarnings("unused")
public class AtividadeAluno {
    private int id, atividade_id, aluno_id, nota_total, nota_recebida;
    private String nome;

    @Override
    public String toString() {
        return "Nome: "+ nome;
    }

    public AtividadeAluno(int id, int atividade_id, int aluno_id, int nota_total, int nota_recebida, String nome) {
        this.id = id;
        this.atividade_id = atividade_id;
        this.aluno_id = aluno_id;
        this.nota_total = nota_total;
        this.nota_recebida = nota_recebida;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAtividade_id() {
        return atividade_id;
    }

    public void setAtividade_id(int atividade_id) {
        this.atividade_id = atividade_id;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public int getNota_total() {
        return nota_total;
    }

    public void setNota_total(int nota_total) {
        this.nota_total = nota_total;
    }

    public int getNota_recebida() {
        return nota_recebida;
    }

    public void setNota_recebida(int nota_recebida) {
        this.nota_recebida = nota_recebida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
