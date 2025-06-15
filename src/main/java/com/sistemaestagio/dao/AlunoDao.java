package com.sistemaestagio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sistemaestagio.bean.Aluno;

public class AlunoDao {

    // Conexão com o banco de dados (lança exceção para indicar falha)
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/sistema__estagio?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC",
            "root", "");
    }

    // Teste de conexão (opcional)
    public static void testarConexao() {
        try (Connection con = getConnection()) {
            System.out.println("Conexão com banco OK!");
        } catch (Exception e) {
            System.out.println("Falha ao conectar no banco:");
            e.printStackTrace();
        }
    }

    // Lista alunos por página (paginação)
    public static List<Aluno> getAlunosByPage(int page, int pageSize) {
        List<Aluno> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM dadosalunos LIMIT ? OFFSET ?")) {

            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setMatricula(rs.getString("matricula"));
                a.setEmailAcademico(rs.getString("email_academico"));
                a.setTelefone(rs.getString("telefone"));
                a.setDataNascimento(rs.getString("data_nascimento"));
                a.setEndereco(rs.getString("endereco"));
                a.setCidade(rs.getString("cidade"));
                a.setEstado(rs.getString("estado"));
                a.setCep(rs.getString("cep"));
                a.setCpf(rs.getString("cpf"));
                list.add(a);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // Conta o total de alunos
    public static int getAlunoCount() {
        int count = 0;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM dadosalunos");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Erro ao contar alunos: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    // Salva um novo aluno no banco
    public static int salvarAluno(Aluno aluno) {
        int status = 0;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO dadosalunos (nome, matricula, email_academico, telefone, data_nascimento, endereco, cidade, estado, cep, cpf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getMatricula());
            ps.setString(3, aluno.getEmailAcademico());
            ps.setString(4, aluno.getTelefone());

            if (aluno.getDataNascimento() != null && !aluno.getDataNascimento().isEmpty()) {
                try {
                    ps.setDate(5, java.sql.Date.valueOf(aluno.getDataNascimento()));
                } catch (Exception e) {
                    System.out.println("Erro na conversão da data: " + aluno.getDataNascimento());
                    ps.setDate(5, null);
                }
            } else {
                ps.setDate(5, null);
            }

            ps.setString(6, aluno.getEndereco());
            ps.setString(7, aluno.getCidade());
            ps.setString(8, aluno.getEstado());
            ps.setString(9, aluno.getCep());
            ps.setString(10, aluno.getCpf());

            status = ps.executeUpdate();
            System.out.println("Status da inserção: " + status);

        } catch (Exception e) {
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
            e.printStackTrace();
        }
        return status;
    }
}
