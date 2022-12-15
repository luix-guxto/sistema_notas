package br.telas;

import br.imgs.ImageLoader;
import br.usuarios.UsuarioDAO;
import br.usuarios.UsuarioLogado;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuInicial extends JFrame {
    private JPanel fundo;
    private JButton atividades;
    private JButton alunos;
    @SuppressWarnings("unused")
    private JLabel titulo;
    private JButton sair;
    private JButton listaAlunos;
    private JButton listaAtividades;
    private JButton conta;
    private JButton relatorio;

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
        sair.addActionListener(e -> onSair());
        listaAlunos.addActionListener(e -> onListaAlunos());
        listaAtividades.addActionListener(e -> onListaAtividades());
        conta.addActionListener(e -> onConta());
        relatorio.addActionListener(e -> onRelatorio());
    }

    private void onRelatorio() {
        // escolhe e salva o path de onde será criado o arquivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha o local para salvar o relatório");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            UsuarioDAO.criarRelatorio(path);
        }
    }

    private void onConta() {
        EditarConta t = new EditarConta(UsuarioLogado.getUsuarioLogado());
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
