package br;

import br.telas.Login;
import ferramentas.utilidades.DataBase;

public class Launch {
    public static void main(String[] args) {
        DataBase.criarTabelas();
        Login tela = new Login();
        tela.pack();
        tela.setVisible(true);
    }
}
