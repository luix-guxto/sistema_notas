package br.telas;

import br.atividades.Atividade;
import br.atividades.FuncoesAtividades;
import br.imgs.ImageLoader;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EditarAtividades extends JFrame {
    private JPanel fundo;
    private JButton sair;
    private JButton buscar;
    private JButton salvar;
    private JTextField id;
    private JTextField nome;
    private JTextField nota;
    private Atividade atividade;

    public EditarAtividades(Atividade atividade) {
        super("Editar Atividades");
        this.atividade = atividade;
        setContentPane(fundo);
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);

        // define os campos
        if (atividade != null) {
            id.setText(atividade.getId()+"");
            nome.setText(atividade.getNome());
            nota.setText(atividade.getNota()+"");
        }

        // botoes
        sair.addActionListener(e -> onSair());
        buscar.addActionListener(e -> onBuscar());
        salvar.addActionListener(e -> onSalvar());
    }

    private void onSalvar() {
        if(nome.getText().isEmpty() || nota.getText().isEmpty() || id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }
        FuncoesAtividades.atualizarAtividade(atividade.getId(), nome.getText(), Integer.parseInt(nota.getText()));
        JOptionPane.showMessageDialog(null, "Atividade atualizada com sucesso!");
        onSair();
    }

    private void onBuscar() {
        if(id.getText().isEmpty()){
            atividade = FuncoesAtividades.buscarAtividadePorNome(nome.getText());
        }else{
            atividade = FuncoesAtividades.buscarAtividadePorId(Integer.parseInt(id.getText()));
        }
        if(atividade != null){
            id.setText(atividade.getId()+"");
            nome.setText(atividade.getNome());
            nota.setText(atividade.getNota()+"");
            JOptionPane.showMessageDialog(null, "Atividade encontrada!");
        }else{
            JOptionPane.showMessageDialog(null, "Atividade não encontrada!");
        }
    }

    private void onSair() {
        ListaAtividades t = new ListaAtividades();
        t.pack();
        t.setVisible(true);
        dispose();
    }
}
