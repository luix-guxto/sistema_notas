package br.telas;

import br.imgs.ImageLoader;
import br.usuarios.UsuarioDAO;
import br.variaveisglobais.VariaveisTela;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static javax.swing.JOptionPane.showMessageDialog;

public class Login extends JFrame{
    private JPanel fundo;
    private JTextField user;
    private JPasswordField senha;
    private JButton entrar;
    private JButton registrar;

    public Login(){
        super("Login");
        setContentPane(fundo);
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(entrar);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);
        entrar.addActionListener(e -> onEntrar());
        registrar.addActionListener(e -> onRegistrar());
    }

    private void onRegistrar() {
        Registrar t = new Registrar();
        t.pack();
        t.setVisible(true);
        dispose();
    }

    @SuppressWarnings("deprecation")
    private void onEntrar() {
        String usuario = user.getText();
        String senha = this.senha.getText();
        if(usuario == null || usuario.isEmpty() || senha == null || senha.isEmpty()) {
            showMessageDialog(null, "Preencha todos os campos!");
        } else if (UsuarioDAO.login(usuario, senha)) {
            MenuInicial t = new MenuInicial();
            t.pack();
            t.setVisible(true);
            dispose();
        } else {
            showMessageDialog(null, "Usuário ou senha incorretos!");
        }
    }

    //teste login tela
    public static void main(String[] args) {
        Login t = new Login();
        t.pack();
        t.setVisible(true);
    }
}
