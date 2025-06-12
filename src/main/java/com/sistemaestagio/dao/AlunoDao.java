package com.sistemaestagio.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.sistemaestagio.bean.Aluno;

public class AlunoDao {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemaestagio", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static List<Aluno> getAlunosByPage(int page, int pageSize) {
        List<Aluno> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM dadosalunos LIMIT ? OFFSET ?");
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static int getAlunoCount() {
        int count = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM dadosalunos");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    // Métodos adicionais (CRUD) podem ser incluídos conforme necessário
}
