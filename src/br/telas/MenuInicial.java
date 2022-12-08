package br.telas;

import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;

public class MenuInicial extends JFrame {
    private JPanel fundo;
    private JButton atividades;
    private JButton alunos;
    private JButton exibicao;
    private JLabel titulo;
    private JButton sair;
    private JButton listaAlunos;
    private JButton listaAtividades;

    public MenuInicial() {
        super("Menu Inicial");
        setContentPane(fundo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);

        // botoes
        atividades.addActionListener(e -> onAtividades());
        alunos.addActionListener(e -> onAlunos());
        exibicao.addActionListener(e -> onExibicao());
        sair.addActionListener(e -> onSair());
    }

    private void onSair() {
        Login t = new Login();
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onExibicao() {
        ExibirAlunos t = new ExibirAlunos();
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onAlunos() {
        CadastrarAlunos t = new CadastrarAlunos();
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onAtividades() {
        CadastrarAtividades t = new CadastrarAtividades();
        t.pack();
        t.setVisible(true);
        dispose();
    }
}
