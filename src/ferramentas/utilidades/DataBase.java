package ferramentas.utilidades;

import sql.mysql.ConecaoMySQL;

import java.sql.Statement;
import java.util.Objects;

public class DataBase {

    public void createDataBase(){
        // cria o banco de dados
        Statement stmt;
        String sql = "CREATE DATABASE IF NOT EXISTS `db_sistema_notas` /*!40100 DEFAULT CHARACTER SET utf8 */;";
        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Banco de dados criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar o banco de dados!");
        }
    }

    public void createTabelaProfessores(){

        Statement stmt;
        String sql = "CREATE TABLE if not exists professores( id int NOT NULL auto_increment, nome varchar(50) not null, cpf varchar(11) not null, numero varchar(11) not null, email varchar(50) not null, senha varchar(50) not null, primary key(id));";
        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Tabela professores criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela professores!");
            e.printStackTrace();
        }
    }

    public void createTabelaAlunos(){
        Statement stmt;
        String sql = "create table if not exists alunos("
                +"id int not null auto_increment,"
                +"professor_id int not null,"
                +"nome varchar(50) not null,"
                +"faltas int default 0,"
                +"nota_final int default 0,"
                +"primary key(id),"
                +"constraint fk_alunos_professores foreign key(professor_id) references professores(id)"
                +");";
        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Tabela alunos criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela alunos!");
            e.printStackTrace();
        }
    }

    public void criarTabelaAtividadesAlunos(){
        Statement stmt;
        String sql = "create table if not exists atividades("
                +"id int not null auto_increment,"
                +"aluno_id int not null,"
                +"atividade_id int not null,"
                +"nome varchar(50) not null,"
                +"nota_total int not null,"
                +"nota_recebida int default 0,"
                +"primary key(id),"
                +"constraint fk_atividades_alunos foreign key(aluno_id) references alunos(id),"
                +"constraint fk_atividades_atividades_cadastradas foreign key(atividade_id) references atividades_cadastradas(id)"
                +");";
        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Tabela atividades criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela atividades!");
            e.printStackTrace();
        }
    }
    public void criarTabelaAtividadesCadastradas(){
        Statement stmt;
        String sql = "create table if not exists atividades_cadastradas("
                +"id int not null auto_increment,"
                +"professores_id int not null,"
                +"nome varchar(50) not null,"
                +"nota_total int not null,"
                +"primary key(id),"
                +"constraint fk_atividades_cadastradas_professores foreign key(professores_id) references professores(id)"
                +");";
        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Tabela atividades criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela atividades!");
            e.printStackTrace();
        }
    }

    public static void criarTabelas(){
        DataBase dataBase = new DataBase();
        dataBase.createDataBase();
        dataBase.createTabelaProfessores();
        dataBase.createTabelaAlunos();
        dataBase.criarTabelaAtividadesAlunos();
        dataBase.criarTabelaAtividadesCadastradas();
    }

    // testes e criação das tabelas
    public static void main(String[] args) {
        criarTabelas();
    }
}

