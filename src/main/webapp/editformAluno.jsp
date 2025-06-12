<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sistemaestagio.bean.Aluno" %>
<%@ page import="com.sistemaestagio.dao.AlunoDao" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Aluno aluno = AlunoDao.getAlunoById(id);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Aluno</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Editar Aluno</h2>
    <form action="editAluno.jsp" method="post">
        <input type="hidden" name="id" value="<%= aluno.getId() %>" />
        <table>
            <tr><td>Nome:</td><td><input type="text" name="nome" value="<%= aluno.getNome() %>" required /></td></tr>
            <tr><td>Matrícula:</td><td><input type="text" name="matricula" value="<%= aluno.getMatricula() %>" required /></td></tr>
            <tr><td>Email Acadêmico:</td><td><input type="email" name="email_academico" value="<%= aluno.getEmailAcademico() %>" required /></td></tr>
            <tr><td>Telefone:</td><td><input type="text" name="telefone" value="<%= aluno.getTelefone() %>" /></td></tr>
            <tr><td>Data Nascimento:</td><td><input type="date" name="data_nascimento" value="<%= aluno.getDataNascimento() %>" /></td></tr>
            <tr><td>Endereço:</td><td><input type="text" name="endereco" value="<%= aluno.getEndereco() %>" /></td></tr>
            <tr><td>Cidade:</td><td><input type="text" name="cidade" value="<%= aluno.getCidade() %>" /></td></tr>
            <tr><td>Estado:</td><td><input type="text" name="estado" maxlength="2" value="<%= aluno.getEstado() %>" /></td></tr>
            <tr><td>CEP:</td><td><input type="text" name="cep" value="<%= aluno.getCep() %>" /></td></tr>
            <tr><td>CPF:</td><td><input type="text" name="cpf" value="<%= aluno.getCpf() %>" required /></td></tr>
            <tr><td colspan="2"><input type="submit" value="Atualizar" /></td></tr>
        </table>
    </form>
    <p><a href="viewAluno.jsp">⬅ Voltar à lista de alunos</a></p>
</body>
</html>
