package br.telas;

import br.imgs.ImageLoader;
import br.usuarios.Usuario;
import br.usuarios.UsuarioLogado;
import br.variaveisglobais.VariaveisTela;
import ferramentas.utilidades.ValidaCPF;
import ferramentas.utilidades.ValidaEmail;
import ferramentas.utilidades.ValidaTelefone;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class EditarConta extends JFrame {
    private JPanel fundo;
    private JLabel lblNome;
    private JLabel lblCPF;
    private JTextField cpf;
    private JLabel lblTelefone;
    private JTextField telefone;
    private JLabel lblEmail;
    private JTextField email;
    private JLabel lblSenha;
    private JPasswordField senha;
    private JLabel lblConfirmaSenha;
    private JPasswordField senhaConfirm;
    private JButton sair;
    private JButton editar;
    private JTextField nome;

    public EditarConta(Usuario usuario) {
        super("Editar Conta");
        setContentPane(fundo);
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);
        getRootPane().setDefaultButton(editar);

        // define os campos
        String CPF = usuario.getCpf();
        String tel = usuario.getTelefone();

        // define o CPF no formato 000.000.000-00
        cpf.setText(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));

        // define o telefone no formato (00) 00000-0000 ou (00) 0000-0000
        if (tel.length() == 11) {
            telefone.setText("(" + tel.substring(0, 2) + ") " + tel.substring(2, 7) + "-" + tel.substring(7, 11));
        } else {
            telefone.setText("(" + tel.substring(0, 2) + ") " + tel.substring(2, 6) + "-" + tel.substring(6, 10));
        }
        nome.setText(usuario.getNome());
        email.setText(usuario.getEmail());

        editar.addActionListener(e -> onEditar());
        sair.addActionListener(e -> onCancel());
    }

    private void onCancel() {
        MenuInicial t = new MenuInicial();
        t.pack();
        t.setVisible(true);
        dispose();
    }

    @SuppressWarnings("deprecation")
    private void onEditar() {
        String tel = telefone.getText();
        String CPF = cpf.getText();
        String nome = this.nome.getText();
        String email = this.email.getText();
        String senha = this.senha.getText().isEmpty() ? UsuarioLogado.getUsuarioLogado().getSenha() : this.senha.getText();
        String senhaConfirm = this.senhaConfirm.getText().isEmpty() ? UsuarioLogado.getUsuarioLogado().getSenha() : this.senhaConfirm.getText();

        // tira qualquer simbolo do cpf
        CPF = CPF.replaceAll("[^0-9]", "");

        // tira qualquer simbolo do telefone
        tel = tel.replaceAll("[^0-9]", "");

        if (nome.isEmpty() || CPF.isEmpty() || tel.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }

        if (!senha.equals(senhaConfirm)) {
            JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
            return;
        }

        if(senha.length()< 8){
            JOptionPane.showMessageDialog(null, "Senha muito curta, mínimo de 8 caracteres!");
            return;
        }
        if(this.senha.getText().equals(UsuarioLogado.getUsuarioLogado().getSenha())){
            JOptionPane.showMessageDialog(null, "A senha não pode ser igual a anterior!");
            return;
        }

        if(!ValidaCPF.isCPF(CPF)){
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            return;
        }
        if(!ValidaEmail.isEmail(email)){
            JOptionPane.showMessageDialog(null, "Email inválido!");
            return;
        }
        if(!ValidaTelefone.isTelefone(tel)){
            JOptionPane.showMessageDialog(null, "Telefone inválido!");
            return;
        }

        Usuario u = new Usuario(UsuarioLogado.getUsuarioLogado().getId(),nome, CPF, tel, email, senha);
        UsuarioLogado.setUsuarioLogado(u);
        UsuarioLogado.salvar();
        JOptionPane.showMessageDialog(null, "Conta editada com sucesso!");
    }

}
