package sql.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
