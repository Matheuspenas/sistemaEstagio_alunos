<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Novo Aluno</title>
  <link rel="stylesheet" href="style.css">
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<h2>Cadastro de Novo Aluno</h2>

<form action="addAluno.jsp" method="post">
  <table>
    <tr><td><label for="nome">Nome:</label></td><td><input type="text" id="nome" name="nome" required></td></tr>
    <tr><td><label for="matricula">Matrícula:</label></td><td><input type="text" id="matricula" name="matricula" required></td></tr>
    <tr><td><label for="email">E-mail Acadêmico:</label></td><td><input type="email" id="email" name="email_academico" required></td></tr>
    <tr><td><label for="telefone">Telefone:</label></td><td><input type="text" id="telefone" name="telefone"></td></tr>
    <tr><td><label for="data_nascimento">Data de Nascimento:</label></td><td><input type="date" id="data_nascimento" name="data_nascimento"></td></tr>
    <tr><td><label for="endereco">Endereço:</label></td><td><input type="text" id="endereco" name="endereco"></td></tr>
    <tr><td><label for="cidade">Cidade:</label></td><td><input type="text" id="cidade" name="cidade"></td></tr>
    <tr><td><label for="estado">Estado:</label></td><td><input type="text" id="estado" name="estado" maxlength="2"></td></tr>
    <tr><td><label for="cep">CEP:</label></td><td><input type="text" id="cep" name="cep"></td></tr>
    <tr><td><label for="cpf">CPF:</label></td><td><input type="text" id="cpf" name="cpf" required></td></tr>
    <tr><td colspan="2"><input type="submit" value="Salvar"></td></tr>
  </table>
</form>

<p><a href="viewAluno.jsp">Voltar à lista</a></p>

<c:if test="${resultado == 'sucess'}">
  <script>
    Swal.fire({
      icon: 'success',
      title: 'Aluno cadastrado!',
      text: 'O novo aluno foi salvo com sucesso.',
      confirmButtonColor: '#3085d6'
    });
  </script>
</c:if>

<c:if test="${resultado == 'error'}">
  <script>
    Swal.fire({
      icon: 'error',
      title: 'Erro ao salvar',
      text: 'Não foi possível cadastrar o aluno. Tente novamente.',
      confirmButtonColor: '#d33'
    });
  </script>
</c:if>

</body>
</html>
