package br.telas;

import br.alunos.FuncoesAlunos;
import br.imgs.ImageLoader;
import br.usuarios.UsuarioLogado;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CadastrarAlunos extends JFrame {

    private JPanel fundo;
    private JButton adicionar;
    private JButton sair;
    private JTextField nome;

    public CadastrarAlunos(){
        super("Cadastrar Alunos");
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
        boolean a = FuncoesAlunos.criarAluno(nome.getText(), UsuarioLogado.getUsuarioLogado().getId());
        nome.setText("");
        if(a) {
            JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Aluno já cadastrado.");
        }
    }

    private void onSair() {
        MenuInicial t = new MenuInicial();
        t.pack();
        t.setVisible(true);
        dispose();
    }
}
