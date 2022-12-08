package br.alunos;

import sql.mysql.ConecaoMySQL;

import java.sql.Statement;
import java.util.Objects;

public class FuncoesAlunos {

    public static boolean criarAluno(String nome, int id) {
        Statement stmt;

        // verifica se o aluno ja existe
        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT * FROM alunos WHERE nome = '" + nome + "' AND professor_id = " + id + ";");
            if (rs.next()) {
                System.out.println("Aluno ja existe!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate("INSERT INTO alunos VALUES (default, "+ id +", '" + nome + "', 0, 0);");

            // salva o id do aluno em uma variavel
            var rs = stmt.executeQuery("SELECT id FROM alunos WHERE professor_id = " + id + " AND nome = '" + nome + "';");
            rs.next();
            int idAluno = rs.getInt("id");
            // pega as atividades cadastradas e cria para o aluno
            var ra = stmt.executeQuery("SELECT * FROM atividades_cadastradas WHERE professores_id = " + id + ";");
            while(ra.next()){
                Statement a = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
                a.executeUpdate("INSERT INTO atividades VALUES (default, "+ idAluno +","+ra.getString("id")+", '" + ra.getString("nome") + "', " + ra.getInt("nota_total") + ", 0);");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
