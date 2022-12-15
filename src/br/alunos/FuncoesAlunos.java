package br.alunos;

import br.usuarios.UsuarioLogado;
import sql.mysql.ConecaoMySQL;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class FuncoesAlunos {

    public static boolean criarAluno(String nome, int id) {
        Statement stmt;

        // verifica se o aluno ja existe
        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT * FROM alunos WHERE nome = '" + nome + "' AND professor_id = " + id + ";");
            if (rs.next()) {
                System.out.println("Aluno ja existe!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate("INSERT INTO alunos VALUES (default, "+ id +", '" + nome + "', 0, 0);");

            // salva o id do aluno em uma variavel
            var rs = stmt.executeQuery("SELECT id FROM alunos WHERE professor_id = " + id + " AND nome = '" + nome + "';");
            rs.next();
            int idAluno = rs.getInt("id");
            // pega as atividades cadastradas e cria para o aluno
            var ra = stmt.executeQuery("SELECT * FROM atividades_cadastradas WHERE professores_id = " + id + ";");
            while(ra.next()){
                Statement a = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
                a.executeUpdate("INSERT INTO atividades VALUES (default, "+ idAluno +","+ra.getString("id")+", '" + ra.getString("nome") + "', " + ra.getInt("nota_total") + ", 0);");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }



    public static void atualizarAluno(Aluno aluno) {
        // atualiza o aluno no banco de dados
        try {
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate("UPDATE alunos SET nome = '" + aluno.getNome() + "', faltas = " + aluno.getFaltas() + ", nota_final = " + aluno.getNota_final() + " WHERE id = " + aluno.getId() + ";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Aluno buscarAlunoPorNome(String text) {
        // busca um aluno pelo nome
        try{
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT * FROM alunos WHERE nome = '" + text + "';");
            if(rs.next()){
                if(rs.getInt("professor_id") != UsuarioLogado.getUsuarioLogado().getId()){
                    System.out.println("Aluno nao pertence a esse professor!");
                    return null;
                }
                return new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getInt("faltas"), rs.getInt("nota_final"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Aluno buscarAlunoPorId(int parseInt) {
        // busca um aluno pelo id
        try{
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT * FROM alunos WHERE id = " + parseInt + ";");
            if(rs.next()){
                if(rs.getInt("professor_id") != UsuarioLogado.getUsuarioLogado().getId()){
                    System.out.println("Aluno nao pertence a esse professor!");
                    return null;
                }
                return new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getInt("faltas"), rs.getInt("nota_final"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Aluno[] listaAlunos() {
        // retorna uma lista com os alunos do professor
        try{
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT * FROM alunos WHERE professor_id = " + UsuarioLogado.getUsuarioLogado().getId() + ";");
            int i = 0;
            while(rs.next()){
                i++;
            }
            Aluno[] alunos = new Aluno[i];
            rs = stmt.executeQuery("SELECT * FROM alunos WHERE professor_id = " + UsuarioLogado.getUsuarioLogado().getId() + ";");
            i = 0;
            while(rs.next()){
                alunos[i] = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getInt("faltas"), rs.getInt("nota_final"));
                i++;
            }
            return alunos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static AtividadeAluno[] listaAtividades(Aluno aluno) {
// retorna uma lista com as atividades do aluno
        try{
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            var rs = stmt.executeQuery("SELECT * FROM atividades WHERE aluno_id = " + aluno.getId() + ";");
            int i = 0;
            while(rs.next()){
                i++;
            }
            AtividadeAluno[] atividades = new AtividadeAluno[i];
            rs = stmt.executeQuery("SELECT * FROM atividades WHERE aluno_id = " + aluno.getId() + ";");
            i = 0;
            while(rs.next()){
                atividades[i] = new AtividadeAluno(rs.getInt("id"), rs.getInt("atividade_id"), rs.getInt("aluno_id"), rs.getInt("nota_total"), rs.getInt("nota_recebida"), rs.getString("nome"));
                i++;
            }
            return atividades;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void atualizarAtividadeAluno(AtividadeAluno atividadeAluno) {
        // atualiza a atividade do aluno no banco de dados
        try {
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate("UPDATE atividades SET nota_recebida = " + atividadeAluno.getNota_recebida() + " WHERE id = " + atividadeAluno.getId() + ";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void limparAlunos() {
        // limpa a tabela de alunos
        try {
            Aluno[] alunos = FuncoesAlunos.listaAlunos();
            assert alunos != null;

            for (Aluno aluno : alunos) {
                Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
                stmt.executeUpdate("DELETE FROM atividades WHERE aluno_id = " + aluno.getId() + ";");
            }
            Statement stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate("DELETE FROM alunos WHERE professor_id = " + UsuarioLogado.getUsuarioLogado().getId() + ";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gerarBoletim(Aluno aluno, String path) {
        String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String texto = "- Boletim do aluno - "+ data
                +"\nNome: "+ aluno.getNome()
                +"\nFaltas: "+aluno.getFaltas()
                +"\nNota final: "+aluno.getNota_final()
                +"\n\n- Atividades -\n";
        AtividadeAluno[] atividades = listaAtividades(aluno);
        assert atividades != null;
        for (AtividadeAluno atividade : atividades) {
            texto = texto.concat(
                    "Nome: "+atividade.getNome()
                    +"\nNota total: "+atividade.getNota_total()
                    +"\nNota recebida: "+atividade.getNota_recebida()
                    +"\n\n");
        }
        try {
            FileWriter myWriter = new FileWriter
                    (path + "/boletim_" + aluno.getNome() + ".txt");
            myWriter.write(texto);
            myWriter.close();
            System.out.println("Boletim gerado com sucesso!");

            // abre o arquivo gerado
            File file = new File(path + "/boletim_" + aluno.getNome() + ".txt");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar boletim!");
        }
    }
}
