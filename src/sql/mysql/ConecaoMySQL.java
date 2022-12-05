package sql.mysql;

import br.variaveisglobais.VariaveisSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class ConecaoMySQL {

    public static String status = "Não conectou...";
    public static java.sql.Connection getConexaoMySql(){


        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Connection connection;
            Class.forName(driverName);

            String serverName = VariaveisSQL.HOST;
            String mydatabase = VariaveisSQL.DATABASE;
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = VariaveisSQL.USERNAME;
            String password = VariaveisSQL.PASSWORD;

            connection = DriverManager.getConnection(url, username, password);

            // testa a conexao
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }
    public static String statusConecao(){
        return status;
    }
    public static void fecharConexao(){
        try{
            Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).close();
            status = "Conexão fechada com sucesso!";
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static java.sql.Connection reiniciarConexao(){
        fecharConexao();
        java.sql.Connection con;
        con = getConexaoMySql();
        if(!status.equals("STATUS--->Não foi possivel realizar conexão") || con != null){
            status = "Conexão reiniciada com sucesso!";
        }
        return con;
    }

}
