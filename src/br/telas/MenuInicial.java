package br.telas;

import br.imgs.ImageLoader;
import br.usuarios.UsuarioLogado;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuInicial extends JFrame {
    private JPanel fundo;
    private JButton atividades;
    private JButton alunos;
    private JButton exibicao;
    @SuppressWarnings("unused")
    private JLabel titulo;
    private JButton sair;
    private JButton listaAlunos;
    private JButton listaAtividades;
    private JButton alterarAtividade;
    private JButton conta;

    public MenuInicial() {
        super("Menu Inicial");
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
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
        listaAlunos.addActionListener(e -> onListaAlunos());
        listaAtividades.addActionListener(e -> onListaAtividades());
        alterarAtividade.addActionListener(e -> onAlterarAtividade());
        conta.addActionListener(e -> onConta());
    }

    private void onConta() {
        EditarConta t = new EditarConta(UsuarioLogado.getUsuarioLogado());
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onAlterarAtividade() {
        EditarAtividades t = new EditarAtividades(null);
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onListaAtividades() {
        ListaAtividades t = new ListaAtividades();
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onListaAlunos() {
        ListaAlunos t = new ListaAlunos();
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onSair() {
        Login t = new Login();
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onExibicao() {
        EditarAlunos t = new EditarAlunos(null);
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
