package br.telas;

import br.alunos.Aluno;
import br.alunos.FuncoesAlunos;
import br.imgs.ImageLoader;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EditarAlunos extends JFrame {
    private JPanel fundo;
    private JTextField id;
    private JTextField nome;
    private JTextField nota;
    private JTextField faltas;
    private JButton sair;
    private JButton buscar;
    private JButton salvar;
    private JButton atividades;
    private JButton boletim;
    private Aluno aluno;

    public EditarAlunos(Aluno aluno) {
        super("Editar Alunos");
        this.aluno = aluno;
        setContentPane(fundo);
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);

        // define os campos
        if (aluno != null) {
            id.setText(aluno.getId()+"");
            nome.setText(aluno.getNome());
            nota.setText(aluno.getNota_final()+"");
            faltas.setText(aluno.getFaltas()+"");
        }

        // botoes
        sair.addActionListener(e -> onSair());
        buscar.addActionListener(e -> onBuscar());
        salvar.addActionListener(e -> onSalvar());
        atividades.addActionListener(e -> onAtividades());
        boletim.addActionListener(e -> onBoletim());

    }

    private void onBoletim() {
        if(aluno == null) {
            JOptionPane.showMessageDialog(null, "Primeiro busque um aluno");
            return;
        }
        // escolhe e salva o path de onde será criado o arquivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha o local para salvar o boletim do aluno: "+aluno.getNome());
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            FuncoesAlunos.gerarBoletim(aluno, path);
        }
    }

    private void onAtividades() {
        AtividadesAluno t = new AtividadesAluno(aluno);
        t.pack();
        t.setVisible(true);
        dispose();
    }

    private void onSalvar() {
        if(nome.getText().isEmpty() || nota.getText().isEmpty() || faltas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        } else {
            aluno = new Aluno(aluno.getId(), nome.getText(), Integer.parseInt(faltas.getText()), aluno.getNota_final());
        }
        FuncoesAlunos.atualizarAluno(aluno);
        JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!");
    }

    private void onBuscar() {
        if(id.getText().isEmpty() && nome.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "O campo ID ou Nome deve ser preenchido!");
        }
        else{
            if(!id.getText().isEmpty()){
                aluno = FuncoesAlunos.buscarAlunoPorId(Integer.parseInt(id.getText()));
                assert aluno != null;
                System.out.println("faltas: "+aluno.getFaltas()+" - nota: "+aluno.getNota_final());
            }
            else{
                aluno = FuncoesAlunos.buscarAlunoPorNome(nome.getText());
                assert aluno != null;
                System.out.println("faltas: "+aluno.getFaltas()+" - nota: "+aluno.getNota_final());
            }
            if(aluno == null){
                JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
            }
            else{
                id.setText(aluno.getId()+"");
                nome.setText(aluno.getNome());
                nota.setText(aluno.getNota_final()+"");
                faltas.setText(aluno.getFaltas()+"");
                JOptionPane.showMessageDialog(null, "Aluno encontrado!");
            }
        }
    }

    private void onSair() {
        ListaAlunos t = new ListaAlunos();
        t.pack();
        t.setVisible(true);
        dispose();
    }
}
