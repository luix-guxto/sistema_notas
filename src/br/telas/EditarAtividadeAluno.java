package br.telas;

import br.alunos.Aluno;
import br.alunos.AtividadeAluno;
import br.alunos.FuncoesAlunos;
import br.imgs.ImageLoader;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EditarAtividadeAluno extends JFrame {
    private final Aluno aluno;
    private final AtividadeAluno atividadeAluno;
    private JPanel fundo;
    private JTextField nome;
    private JButton voltarButton;
    private JButton salvarButton;
    private JTextField nota_total;
    private JTextField nota_recebida;

    public EditarAtividadeAluno(Aluno aluno, AtividadeAluno atividadeAluno) {
        super("Editar Atividade do Aluno");
        this.aluno = aluno;
        this.atividadeAluno = atividadeAluno;
        setContentPane(fundo);
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);

        nome.setText(atividadeAluno.getNome());
        nota_total.setText(atividadeAluno.getNota_total()+"");
        nota_recebida.setText(atividadeAluno.getNota_recebida()+"");

        voltarButton.addActionListener(e -> onVoltar());
        salvarButton.addActionListener(e -> onSalvar());
    }

    private void onSalvar() {
        if(nota_recebida.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Defina a nota que o aluno ira receber!");
        } else if (Integer.parseInt(nota_recebida.getText())>atividadeAluno.getNota_total()) {
            JOptionPane.showMessageDialog(null, "A nota recebida nao pode ser maior que a nota total!");
        } else{
            int notaAnti = atividadeAluno.getNota_recebida();
            atividadeAluno.setNota_recebida(Integer.parseInt(nota_recebida.getText()));
            aluno.setNota_final(aluno.getNota_final()+atividadeAluno.getNota_recebida()-notaAnti);
            FuncoesAlunos.atualizarAluno(aluno);
            FuncoesAlunos.atualizarAtividadeAluno(atividadeAluno);
            JOptionPane.showMessageDialog(null, "Atividade atualizada com sucesso!");
            onVoltar();
        }
    }

    private void onVoltar() {
        AtividadesAluno t = new AtividadesAluno(aluno);
        t.pack();
        t.setVisible(true);
        dispose();
    }
}
