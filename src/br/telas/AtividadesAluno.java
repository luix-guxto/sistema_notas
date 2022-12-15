package br.telas;

import br.alunos.Aluno;
import br.alunos.AtividadeAluno;
import br.alunos.FuncoesAlunos;
import br.imgs.ImageLoader;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AtividadesAluno extends JFrame {
    private final Aluno aluno;
    private JPanel fundo;
    @SuppressWarnings("rawtypes")
    private JList lista;
    private JButton voltar;

    @SuppressWarnings("unchecked")
    public AtividadesAluno(Aluno aluno) {
        super("Atividades do Aluno");
        this.aluno = aluno;
        setContentPane(fundo);
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);

        AtividadeAluno[] atividades = FuncoesAlunos.listaAtividades(aluno);
        lista.setListData(atividades);
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    onAtividadeSelecionado();
                }
            }
        });

        voltar.addActionListener(e -> onVoltar());
    }

    private void onVoltar() {
        EditarAlunos t = new EditarAlunos(aluno);
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onAtividadeSelecionado() {
        EditarAtividadeAluno t = new EditarAtividadeAluno(aluno, (AtividadeAluno) lista.getSelectedValue());
        t.pack();
        t.setVisible(true);
        dispose();
    }
}
