<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sistemaestagio.bean.Aluno" %>
<%@ page import="com.sistemaestagio.dao.AlunoDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Listagem de Alunos</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>

<%
  int currentPage = 1;
  int pageSize = 10;

  if (request.getParameter("page") != null) {
      try {
          currentPage = Integer.parseInt(request.getParameter("page"));
      } catch (NumberFormatException e) {
          currentPage = 1;
      }
  }

  List<Aluno> list = AlunoDao.getAlunosByPage(currentPage, pageSize);
  int totalAlunos = AlunoDao.getAlunoCount();
  int totalPages = (int) Math.ceil((double) totalAlunos / pageSize);

  request.setAttribute("list", list);
  request.setAttribute("currentPage", currentPage);
  request.setAttribute("totalPages", totalPages);
%>

<p><a href="index.html" class="btn">[home]</a></p>
<h1>Listagem de Alunos</h1>
<p><a href="formAluno.jsp" class="btn">➕ Novo Aluno</a></p>

<table class="table-user">
  <tr>
    <th>ID</th>
    <th>Nome</th>
    <th>Matrícula</th>
    <th>Email</th>
    <th>Telefone</th>
    <th>Nascimento</th>
    <th>Cidade</th>
    <th>Estado</th>
    <th>CPF</th>
    <th>Ações</th>
  </tr>
  <c:forEach items="${list}" var="aluno">
    <tr>
      <td>${aluno.id}</td>
      <td>${aluno.nome}</td>
      <td>${aluno.matricula}</td>
      <td>${aluno.emailAcademico}</td>
      <td>${aluno.telefone}</td>
      <td>${aluno.dataNascimento}</td>
      <td>${aluno.cidade}</td>
      <td>${aluno.estado}</td>
      <td>${aluno.cpf}</td>
      <td>
        <a href="editformAluno.jsp?id=${aluno.id}" class="btn">✏️ Editar</a>
        <a href="deleteAluno.jsp?id=${aluno.id}" class="btn" onclick="return confirm('Deseja realmente excluir este aluno?');">🗑️ Excluir</a>
      </td>
    </tr>
  </c:forEach>
</table>

<div class="pagination">
  <c:if test="${currentPage > 1}">
    <a href="viewAluno.jsp?page=${currentPage - 1}">⬅ Anterior</a>
  </c:if>

  <c:forEach begin="1" end="${totalPages}" var="i">
    <a href="viewAluno.jsp?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
  </c:forEach>

  <c:if test="${currentPage < totalPages}">
    <a href="viewAluno.jsp?page=${currentPage + 1}">Próxima ➡</a>
  </c:if>
</div>

</body>
</html>
