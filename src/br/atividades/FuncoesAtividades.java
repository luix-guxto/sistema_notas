package br.atividades;

import br.alunos.Aluno;
import br.alunos.FuncoesAlunos;
import br.usuarios.UsuarioLogado;
import sql.mysql.ConecaoMySQL;

import java.sql.Statement;
import java.util.Objects;

public class FuncoesAtividades {

    public static void criarAtividade(String nome, int nota, int id) {
        // criar atividade cadastrada
        Statement stmt;
        int idCadastrada = 0;
        try{
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate("INSERT INTO atividades_cadastradas VALUES (default, "+ id +", '" + nome + "', " + nota + ");");
            System.out.println("Atividade cadastrada com sucesso!");
            // salva o id da atividade cadastrada em uma variavel
            var rs = stmt.executeQuery("SELECT id FROM atividades_cadastradas WHERE professores_id = " + id + " AND nome = '" + nome + "';");
            rs.next();
            idCadastrada = rs.getInt("id");
        }catch (Exception e) {
            e.printStackTrace();
        }
        // criar atividade para cada aluno
        try{
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            // pega todos os ids dos alunos
            var rs = stmt.executeQuery("SELECT id FROM alunos WHERE professor_id = " + id + ";");
            while(rs.next()){
                System.out.println("id: " + rs.getInt("id"));
                Statement a = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
                a.executeUpdate("INSERT INTO atividades VALUES (default, "+ rs.getInt("id") +","+idCadastrada+", '" + nome + "', " + nota + ", 0);");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Atividade[] listaAtividades() {

        // pega o numero de atividades cadastradas
        int numAtividades = 0;
        try{
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT COUNT(*) FROM atividades_cadastradas WHERE professores_id = " + UsuarioLogado.getUsuarioLogado().getId() + ";");
            rs.next();
            numAtividades = rs.getInt("COUNT(*)");
        }catch (Exception e) {
            e.printStackTrace();
        }
        // cria um array de atividades
        Atividade[] atividades = new Atividade[numAtividades];
        // pega as atividades cadastradas
        try{
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT * FROM atividades_cadastradas WHERE professores_id = " + UsuarioLogado.getUsuarioLogado().getId() + ";");
            int i = 0;
            while(rs.next()){
                atividades[i] = new Atividade(rs.getInt("id"), rs.getInt("nota_total"), rs.getString("nome"));
                i++;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return atividades;
    }

    public static Atividade buscarAtividadePorNome(String text) {
        try {
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT * FROM atividades_cadastradas WHERE professores_id = " + UsuarioLogado.getUsuarioLogado().getId() + " AND nome = '" + text + "';");
            if(rs.next()) {
                return new Atividade(rs.getInt("id"), rs.getInt("nota_total"), rs.getString("nome"));
            }else {
                System.out.println("Atividade não encontrada!");
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Atividade buscarAtividadePorId(int parseInt) {
        try {
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT * FROM atividades_cadastradas WHERE professores_id = " + UsuarioLogado.getUsuarioLogado().getId() + " AND id = " + parseInt + ";");
            if(rs.next()) {
                return new Atividade(rs.getInt("id"), rs.getInt("nota_total"), rs.getString("nome"));
            }else {
                System.out.println("Atividade não encontrada!");
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void atualizarAtividade(int id, String text, int parseInt) {
        try {
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate("UPDATE atividades_cadastradas SET nome = '" + text + "', nota_total = " + parseInt + " WHERE id = " + id + ";");
            System.out.println("Atividade atualizada com sucesso!");
            // atualizar a atividade para todos os alunos
            Statement a = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            a.executeUpdate("UPDATE atividades SET nome = '" + text + "', nota_total = " + parseInt + " WHERE atividade_id = " + id + ";");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void limparAtividades() {
        // apaga todas as atividades cadastradas
        try {
            // apaga todas as atividades dos alunos do professor
            Aluno[] alunos = FuncoesAlunos.listaAlunos();
            assert alunos != null;

            for (Aluno aluno : alunos) {
                Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
                stmt.executeUpdate("DELETE FROM atividades WHERE aluno_id = " + aluno.getId() + ";");
            }

            // apaga todas as atividades cadastradas
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate("DELETE FROM atividades_cadastradas WHERE professores_id = " + UsuarioLogado.getUsuarioLogado().getId() + ";");
            System.out.println("Atividades apagadas com sucesso!");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
