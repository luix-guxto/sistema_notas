package br.variaveisglobais;

import br.telas.Conexao;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.FileReader;

final public class VariaveisSQL {

    public static String USERNAME, PASSWORD, DATABASE, HOST;
    public static boolean conectado = false;

    public static void iniciarVariaveis() {
        // ler .json da conexao
        JSONObject obj;
        JSONParser parser = new JSONParser();
        try {
            obj = (JSONObject) parser.parse(new FileReader("./conexao.json"));
            if(!obj.containsKey("username") || !obj.containsKey("password") || !obj.containsKey("database") || !obj.containsKey("host")) {
                JOptionPane.showMessageDialog(null, "Arquivo de conexão corrompido, criar novo arquivo");
                Conexao conexao = new Conexao();
                conexao.pack();
                conexao.setVisible(true);
                return;
            }
            USERNAME = (String) obj.get("username");
            PASSWORD = (String) obj.get("password");
            DATABASE = (String) obj.get("database");
            HOST = (String) obj.get("host");
            conectado = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Arquivo de conexão corrompido, criar novo arquivo");
            Conexao conexao = new Conexao();
            conexao.pack();
            conexao.setVisible(true);
        }
    }

}
