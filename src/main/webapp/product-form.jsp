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
        	<a href="${pageContext.request.contextPath}/user?action=list">Listar Usuários</a>
 
        </h2>
	</center>
    <div align="center">
    	<h2>
        	Usuário: <c:out value='${user.name}'/>
        </h2>
		<c:if test="${product != null}">
			<form action="${pageContext.request.contextPath}/product?action=update&user_id=<c:out value='${user.id}'/>" method="post">
        </c:if>
        <c:if test="${product == null}">
			<form action="${pageContext.request.contextPath}/product?action=insert&user_id=<c:out value='${user.id}'/>" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${product != null}">
            			Atualizar Produto
            		</c:if>
            		<c:if test="${product == null}">
            			Adicionar Produto
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${product != null}">
        			<input type="hidden" name="id" value="<c:out value='${product.id}' />" />
        		</c:if>            
            <tr>
                <th>Descrição: </th>
                <td>
                	<input type="text" name="descricao" size="60"
                			value="<c:out value='${product.descricao}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Preço: </th>
                <td>
                	<input type="number" name="preco" size="45"
                			value="<c:out value='${product.preco}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            	<c:if test="${product != null}">
							<input type="submit" value="Atualizar" />
		        </c:if>
		        <c:if test="${product == null}">
							<input type="submit" value="Salvar" />
		        </c:if>
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
