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
        	<a href="${pageContext.request.contextPath}/product?action=new&user_id=<c:out value='${user.id}'/>">Novo Produto</a>
        	<a href="${pageContext.request.contextPath}/user?action=list">Listar usuários</a>
        </h2>
	</center>
    <div align="center">
    	<h2>
        	Usuário: <c:out value='${user.name}'/>
        </h2>
        <table class="table table-striped table-bordered" style="width:100%">
            <tr class="thead-dark">
                <th scope="col">ID</th>
                <th scope="col">Descrição</th>
                <th scope="col">Preço</th>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td scope="row"><c:out value="${product.id}" /></td>
                    <td><c:out value="${product.descricao}" /></td>
                    <td><c:out value="${product.preco}" /></td>
                    <td>
                    <a href="${pageContext.request.contextPath}/product?action=edit&user_id=<c:out value='${user.id}'/>&id=<c:out value='${product.id}'/>">Atualizar</a>
                   	<a href="${pageContext.request.contextPath}/product?action=delete&user_id=<c:out value='${user.id}'/>&id=<c:out value='${product.id}'/>">Deletar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
</body>
</html>
