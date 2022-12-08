package br.atividades;

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
}
