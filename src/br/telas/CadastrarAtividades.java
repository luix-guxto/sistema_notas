package br.telas;

import br.atividades.FuncoesAtividades;
import br.imgs.ImageLoader;
import br.usuarios.UsuarioLogado;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CadastrarAtividades extends JFrame {
    private JPanel fundo;
    private JButton adicionar;
    private JButton sair;
    private JTextField nome;
    private JTextField nota;

    public CadastrarAtividades(){
        super("Cadastrar Atividades");
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setContentPane(fundo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);
        getRootPane().setDefaultButton(adicionar);

        // botoes
        adicionar.addActionListener(e -> onAdicionar());
        sair.addActionListener(e -> onSair());
    }

    private void onAdicionar() {
        FuncoesAtividades.criarAtividade(nome.getText(), Integer.parseInt(nota.getText()), UsuarioLogado.getUsuarioLogado().getId());
        nome.setText("");
        nota.setText("");
        JOptionPane.showMessageDialog(null, "Atividade adicionada com sucesso!");
    }

    private void onSair() {
        MenuInicial t = new MenuInicial();
        t.pack();
        t.setVisible(true);
        dispose();
    }
}
