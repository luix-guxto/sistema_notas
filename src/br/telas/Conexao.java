package br.telas;

import br.Launch;
import br.imgs.ImageLoader;
import br.variaveisglobais.VariaveisTela;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileWriter;

public class Conexao extends JFrame {
    @SuppressWarnings("unused")
    private JLabel lblNome;
    private JTextField username;
    private JTextField password;
    private JTextField database;
    private JTextField host;
    private JPanel fundo;
    private JButton conectar;

    public Conexao(){
        super("Conexão");
        setContentPane(fundo);
        BufferedImage icon = ImageLoader.loadImage(VariaveisTela.IMAGEM);
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(VariaveisTela.LARGURA, VariaveisTela.ALTURA));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2- VariaveisTela.LARGURA/2, 20);
        setResizable(false);
        conectar.addActionListener(e -> onConectar());
    }

    @SuppressWarnings("unchecked")
    private void onConectar() {
        JSONObject json = new JSONObject();
        json.put("username", username.getText());
        json.put("password", password.getText());
        json.put("database", database.getText());
        json.put("host", host.getText());

        try{
            FileWriter save = new FileWriter("./conexao.json");
            save.write(json.toJSONString());
            save.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JOptionPane.showMessageDialog(null, "Conexão salva com sucesso!");
        dispose();
        Launch.main(null);
    }
}
