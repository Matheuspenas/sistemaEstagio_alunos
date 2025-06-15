<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sistemaestagio.bean.Aluno" %>
<%@ page import="com.sistemaestagio.dao.AlunoDao" %>

<%
    request.setCharacterEncoding("UTF-8");

    Aluno aluno = new Aluno();
    aluno.setNome(request.getParameter("nome"));
    aluno.setMatricula(request.getParameter("matricula"));
    aluno.setEmailAcademico(request.getParameter("email_academico"));
    aluno.setTelefone(request.getParameter("telefone"));
    aluno.setDataNascimento(request.getParameter("data_nascimento"));
    aluno.setEndereco(request.getParameter("endereco"));
    aluno.setCidade(request.getParameter("cidade"));
    aluno.setEstado(request.getParameter("estado"));
    aluno.setCep(request.getParameter("cep"));
    aluno.setCpf(request.getParameter("cpf"));

    int status = AlunoDao.salvarAluno(aluno);
    System.out.println("Status da inserção via JSP: " + status);

    if (status > 0) {
        request.setAttribute("resultado", "sucesso");
    } else {
        request.setAttribute("resultado", "erro");
    }

    request.getRequestDispatcher("formAluno.jsp").forward(request, response);
%>
	