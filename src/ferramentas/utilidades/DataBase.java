package ferramentas.utilidades;

import sql.mysql.ConecaoMySQL;

import java.sql.Statement;
import java.util.Objects;

public class DataBase {

    public void createDataBase(){
        // cria o banco de dados
        Statement stmt = null;
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

        Statement stmt = null;
        String sql = "USE db_sistema_notas; CREATE TABLE if not exists professores( id int NOT NULL unique auto_increment, nome varchar(50) not null, cpf varchar(11) not null, numero varchar(11) not null, email varchar(50) not null, senha varchar(50) not null, primary key(id));";
        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Tabela professores criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela professores!");
        }
    }

    public void createTabelaAlunos(){
        Statement stmt = null;
        String sql = "USE db_sistema_notas;"
                +"create table if not exists alunos("
                +"id int not null unique auto_increment,"
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
        }
    }

    public void criarTabelaAtividades(){
        Statement stmt = null;
        String sql = "USE db_sistema_notas;"
                +"create table if not exists atividades("
                +"id int not null unique auto_increment,"
                +"aluno_id int not null,"
                +"nome varchar(50) not null,"
                +"nota_total int not null,"
                +"nota_recebida int default 0,"
                +"primary key(id),"
                +"constraint fk_atividades_alunos foreign key(aluno_id) references alunos(id)"
                +");";
        try {
            stmt = Objects.requireNonNull(ConecaoMySQL.getConexaoMySql()).createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Tabela atividades criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela atividades!");
        }
    }

    public void criarTabelas(){
        createDataBase();
        createTabelaProfessores();
        createTabelaAlunos();
        criarTabelaAtividades();
    }
}
