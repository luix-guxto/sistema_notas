package br.telas;

import br.usuarios.UsuarioDAO;
import ferramentas.utilidades.ValidaCPF;
import ferramentas.utilidades.ValidaEmail;
import ferramentas.utilidades.ValidaTelefone;
import br.variaveisglobais.VariaveisLogin;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static javax.swing.JOptionPane.*;

public class Registrar extends JFrame {

    @SuppressWarnings("unused")
    private JLabel lblNome, lblCPF, lblEmail, lblTelefone, lblSenha, lblConfirmaSenha;

    private JTextField nome;
    private JTextField cpf;
    private JPanel fundo;
    private JButton cancelButton;
    private JButton registrarButton;
    private JTextField telefone;
    private JTextField email;
    private JPasswordField senha;
    private JPasswordField senhaConfirm;

    public Registrar() {
        super("Registrar - Sistema de Notas Escolares");
        setContentPane(fundo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisLogin.LARGURA, VariaveisLogin.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisLogin.LARGURA/2, 20);
        setResizable(false);
        pack();
        setVisible(true);

        registrarButton.addActionListener(e -> onRegistrar());
        cancelButton.addActionListener(e -> onCancel());
    }

    private void onCancel() {
        Login t = new Login();
        t.pack();
        t.setVisible(true);
        dispose();
    }

    @SuppressWarnings("deprecation")
    private void onRegistrar() {
        String tel = telefone.getText();
        String CPF = cpf.getText();

        // tira qualquer simbolo do cpf
        CPF = CPF.replaceAll("[^0-9]", "");

        // tira qualquer simbolo do telefone
        tel = tel.replaceAll("[^0-9]", "");

        // verifica se os campos estão preenchidos
        if (nome.getText().isEmpty() || cpf.getText().isEmpty() || telefone.getText().isEmpty() || email.getText().isEmpty() || senha.getText().isEmpty() || senhaConfirm.getText().isEmpty()) {
            showMessageDialog(null, "Preencha todos os campos!");
        } else {
            // verifica o tamanho da senha
            if(senha.getText().length()>=8){
                // verifica se as senhas são iguais
                if (Arrays.equals(senha.getPassword(), senhaConfirm.getPassword())) {
                    // verifica se o cpf é válido
                    if (ValidaCPF.isCPF(CPF)) {
                        // verifica se o email é válido
                        if (ValidaEmail.isEmail(email.getText())) {
                            // verifica se o telefone é válido
                            if (ValidaTelefone.isTelefone(tel)) {
                                // verifica se o usuário já existe
                                if (UsuarioDAO.verificaUsuario(CPF)) {
                                    showMessageDialog(null, "Usuário já cadastrado!");
                                } else {
                                    // cria o usuário
                                    UsuarioDAO.criaUsuario(nome.getText(), CPF, tel, email.getText(), senha.getText());
                                    showMessageDialog(null, "Usuário cadastrado com sucesso!");
                                    onCancel();
                                }
                            } else {
                                showMessageDialog(null, "Telefone inválido!");
                            }
                        } else {
                            showMessageDialog(null, "Email inválido!");
                        }
                    } else {
                        showMessageDialog(null, "CPF inválido!");
                    }
                } else {
                    showMessageDialog(null, "Senhas não conferem!");
                }
            }else {
                showMessageDialog(null, "Senha muito curta, mínimo de 8 caracteres!");
            }
        }
    }

    //testar tela registrar
    public static void main(String[] args) {
        Registrar t = new Registrar();
        t.pack();
        t.setVisible(true);
    }
}
