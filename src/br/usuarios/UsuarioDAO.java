package br.usuarios;

import br.alunos.*;
import br.atividades.Atividade;
import br.atividades.FuncoesAtividades;
import sql.mysql.ConecaoMySQL;
import ferramentas.utilidades.ValidaEmail;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UsuarioDAO {

    public static boolean verificaUsuario(String cpf){
        boolean usuarioExiste = false;
        try {
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            String sqlComando = "SELECT * FROM professores WHERE cpf = '" + cpf + "'";
            var rs = stmt.executeQuery(sqlComando);
            if (rs.next()) {
                usuarioExiste = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarioExiste;
    }

    public static boolean login(String user, String senha){

        boolean login = false;
        try {
            if(!ValidaEmail.isEmail(user)) return false;
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            String sqlComando = "SELECT * FROM professores WHERE email = '" + user + "' AND senha = '" + senha + "'";
            var rs = stmt.executeQuery(sqlComando);
            if (rs.next()) {
                login = true;
                UsuarioLogado.setUsuarioLogado(new Usuario(Integer.parseInt(rs.getString("id")), rs.getString("nome"), rs.getString("cpf"), rs.getString("numero"), rs.getString("email"), rs.getString("senha")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return login;
    }

    public static void criaUsuario(String nome, String cpf, String tel, String email, String senha) {
        try {
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            String sqlComando = "INSERT INTO professores VALUES (default, '" + nome + "', '" + cpf + "', '" + tel + "', '" + email + "', '" + senha + "')";
            stmt.executeUpdate(sqlComando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void salvar(Usuario usuarioLogado) {
        try {
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            String sqlComando = "UPDATE professores SET nome = '" + usuarioLogado.getNome() + "', cpf = '" + usuarioLogado.getCpf() + "', numero = '" + usuarioLogado.getTelefone() + "', email = '" + usuarioLogado.getEmail() + "', senha = '" + usuarioLogado.getSenha() + "' WHERE id = " + usuarioLogado.getId();
            stmt.executeUpdate(sqlComando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void criarRelatorio(String path) {
        // data de hoje em texto
        String data = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String texto = "- Relatório de alunos - " + data
                + "\n- Alunos cadastrados - \n";
        Aluno[] alunos = FuncoesAlunos.listaAlunos();
        assert alunos != null;
        for (Aluno aluno:alunos){
            texto = texto.concat(
                    "Nome: "+aluno.getNome()
                    +"\nID: "+aluno.getId()
                    +"\nFaltas: "+aluno.getFaltas()
                    +"\nNota: "+aluno.getNota_final()
                    +"\n- Atividades do aluno - \n"
            );
            AtividadeAluno[] atividades = FuncoesAlunos.listaAtividades(aluno);
            assert atividades != null;
            for (AtividadeAluno atividade: atividades){
                texto = texto.concat("Nome: "+atividade.getNome()
                        +"\nID: "+atividade.getId()
                        +"\nAtividade_ID: "+atividade.getAtividade_id()
                        +"\nNota_total: "+atividade.getNota_total()
                        +"\nNota_recebida: "+atividade.getNota_recebida()
                        +"\n\n"
                );
            }
            texto = texto.concat("- Fim das atividades do aluno - \n\n");
        }
        texto = texto.concat("- Fim dos alunos cadastrados - \n\n- Atividades cadastradas - \n");
        Atividade[] atividadesCad = FuncoesAtividades.listaAtividades();
        for (Atividade atividade: atividadesCad){
            texto = texto.concat("Nome: "+atividade.getNome()
                    +"\nID: "+atividade.getId()
                    +"\nNota: "+atividade.getNota()
                    +"\n\n"
            );
        }
        texto = texto.concat("- Fim das atividades cadastradas - \n\n");
        try {
            FileWriter myWriter = new FileWriter
                    (path + "/Relatorio_" + data + ".txt");
            myWriter.write(texto);
            myWriter.close();
            System.out.println("Relatorio gerado com sucesso!");

            // abre o arquivo gerado
            File file = new File(path + "/Relatorio_" + data + ".txt");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar relatorio!");
        }
    }
}
