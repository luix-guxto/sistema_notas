package br.telas;

import br.atividades.Atividade;
import br.atividades.FuncoesAtividades;
import br.imgs.ImageLoader;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ListaAtividades extends JFrame {
    private JPanel fundo;
    @SuppressWarnings("rawtypes")
    private JList lista;
    private JButton sair;
    private JButton limparAtividades;

    @SuppressWarnings("unchecked")
    public ListaAtividades(){
        super("Lista de Atividades");
        setContentPane(fundo);
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);
        getRootPane().setDefaultButton(sair);

        Atividade[] atividades = FuncoesAtividades.listaAtividades();

        lista.setListData(atividades);

        // adiciona acao para o aluno selecionado com duplo clique na lista
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseClicked(java.awt.event.MouseEvent evt) {
               if (evt.getClickCount() == 2) {
                   onAtividadeSelecionado();
               }
           }
       });
        // botoes
        sair.addActionListener(e -> onSair());
        limparAtividades.addActionListener(e -> onLimparAtividades());
    }

    private void onLimparAtividades() {
        FuncoesAtividades.limparAtividades();
        onSair();
    }

    private void onSair() {
        MenuInicial t = new MenuInicial();
        t.pack();
        t.setVisible(true);
        dispose();
    }
    private void onAtividadeSelecionado() {
        Atividade atividade = (Atividade) lista.getSelectedValue();
        if (atividade != null) {
            EditarAtividades t = new EditarAtividades(atividade);
            t.pack();
            t.setVisible(true);
            dispose();
        }
    }
}


