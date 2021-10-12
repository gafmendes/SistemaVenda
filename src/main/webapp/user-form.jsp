<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Sistema de Vendas</title>
</head>
<body>
	<center>
		<h1>Sistema de Vendas</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/user?action=list">Listar usuário</a>
        </h2>
	</center>
    <div align="center">
		<c:if test="${user != null}">
			<form action="${pageContext.request.contextPath}/user?action=update" method="post">
        </c:if>
        <c:if test="${user == null}">
			<form action="${pageContext.request.contextPath}/user?action=insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${user != null}">
            			Atualizar usuário
            		</c:if>
            		<c:if test="${user == null}">
            			Adicionar usuário
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${user != null}">
        			<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
        		</c:if>            
            <tr>
                <th>Name: </th>
                <td>
                	<input type="text" name="name" size="45"
                			value="<c:out value='${user.name}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>CPF/CNPJ: </th>
                <td>
                	<input type="text" name="cpf" size="45"
                			value="<c:out value='${user.cpfOrCnpj}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            	<c:if test="${user != null}">
							<input type="submit" value="Atualizar" />
		        </c:if>
		        <c:if test="${user == null}">
							<input type="submit" value="Salvar" />
		        </c:if>
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
