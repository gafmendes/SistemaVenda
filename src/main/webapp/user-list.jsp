<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
	<title>Sistema de Vendas</title>
</head>
<body>
	<center>
		<h1>Sistema de Vendas</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/user?action=new">Novo usuário</a>
        </h2>
	</center>
    <div align="center">
        <table class="table table-striped table-bordered" style="width:100%">
            <tr class="thead-dark">
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">cpfOrCnpj</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td scope="row"><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.cpfOrCnpj}" /></td>
                    <td>
                    <a href="${pageContext.request.contextPath}/user?action=edit&id=<c:out value='${user.id}'/>">Atualizar</a>
                   	<a href="${pageContext.request.contextPath}/user?action=delete&id=<c:out value='${user.id}'/>">Deletar</a>
                   	<a href="${pageContext.request.contextPath}/product?action=list&user_id=<c:out value='${user.id}'/>">Listar produtos</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
</body>
</html>
