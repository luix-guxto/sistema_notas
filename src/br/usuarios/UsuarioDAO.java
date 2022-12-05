package br.usuarios;

import sql.mysql.ConecaoMySQL;
import ferramentas.utilidades.ValidaEmail;

import java.sql.SQLException;
import java.sql.Statement;
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
}
