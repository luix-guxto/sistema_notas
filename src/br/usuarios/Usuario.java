package br.usuarios;

@SuppressWarnings("unused")
public class Usuario {
    private String nome, cpf, telefone, email, senha;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario(int id, String nome, String cpf, String telefone, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{\n" +
                "nome='" + nome + '\'' +
                ", \ncpf='" + cpf + '\'' +
                ", \ntelefone='" + telefone + '\'' +
                ", \nemail='" + email + '\'' +
                ", \nsenha='" + senha + '\'' +
                "\n}";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
