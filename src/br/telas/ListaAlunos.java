package br.telas;

import br.alunos.Aluno;
import br.alunos.FuncoesAlunos;
import br.imgs.ImageLoader;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ListaAlunos extends JFrame {

    @SuppressWarnings("rawtypes")
    private JList texto;
    private JButton sair;
    private JPanel fundo;
    private JButton limpar;

    @SuppressWarnings("unchecked")
    public ListaAlunos() {
        super("Lista de Alunos");
        setContentPane(fundo);
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);
        getRootPane().setDefaultButton(sair);
        Aluno[] alunos = FuncoesAlunos.listaAlunos();
        texto.setListData(alunos);

        // adiciona acao para o aluno selecionado com duplo clique na lista
        texto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    onAlunoSelecionado();
                }
            }
        });

        sair.addActionListener(e -> onSair());
        limpar.addActionListener(e -> onLimpar());
    }

    private void onLimpar() {
        FuncoesAlunos.limparAlunos();
        onSair();
    }

    private void onAlunoSelecionado() {
        Aluno aluno = (Aluno) texto.getSelectedValue();
        if (aluno != null) {
            EditarAlunos t = new EditarAlunos(aluno);
            t.pack();
            t.setVisible(true);
            dispose();
        }
    }

    private void onSair() {
        MenuInicial t = new MenuInicial();
        t.pack();
        t.setVisible(true);
        dispose();
    }

}
