package br.telas;

import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;

public class ExibirAlunos extends JFrame {

    private JPanel fundo;
    private JTextField id;
    private JTextField nome;
    private JTextField nota;
    private JTextField faltas;
    private JButton sair;
    private JButton buscar;
    private JButton atualizar;
    private JButton atividades;

    public ExibirAlunos() {
        super("Exibir Alunos");
        setContentPane(fundo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA_JANELA, VariaveisTela.ALTURA_JANELA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA_JANELA/2, 20);
        setResizable(false);
    }
}
