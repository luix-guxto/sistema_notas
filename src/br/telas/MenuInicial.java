package br.telas;

import br.variaveisglobais.VariaveisLogin;

import javax.swing.*;
import java.awt.*;

public class MenuInicial extends JFrame {
    private JPanel fundo;

    public MenuInicial() {
        super("Menu Inicial - Sistema de Notas Escolares");
        setContentPane(fundo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisLogin.LARGURA, VariaveisLogin.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisLogin.LARGURA/2, 20);
        setResizable(false);
    }
}
