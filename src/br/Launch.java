package br;

import br.telas.Login;
import sql.mysql.VariaveisSQL;
import ferramentas.utilidades.DataBase;

public class Launch {
    public static void main(String[] args) {
        VariaveisSQL.iniciarVariaveis();
        if(VariaveisSQL.conectado) {
            DataBase.criarTabelas();
            Login tela = new Login();
            tela.pack();
            tela.setVisible(true);
        }


    }
}
