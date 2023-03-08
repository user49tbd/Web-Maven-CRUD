<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./CSS/cf.css">
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<form action="ProdutoS" method="post">
<table border="1" align="center">
<caption>Produtos</caption>
<tr>
<td colspan="3"><input id="fid" name="fid" type="number" 
placeholder="ID" step="1" min="1" value="<c:out value="${produto.id}"></c:out>"></td>
<td><input id="bt" name="bt" type="submit" 
value="Buscar"></td>
</tr>
<tr>
<td colspan="4"><input id="fnome" name="fnome" type="text" 
placeholder="nome" value="<c:if test="${not empty marca }">
<c:out value="${produto.nome}"></c:out></c:if>"></td>
</tr>
<td colspan="4"><input id="fpro" name="fpro" type="number" 
placeholder="preco" min="0" step="0.01"
value="<c:out value="${produto.preco}"></c:out>"></td>
</tr>
<tr>
	<td colspan="4">
		<select id="marca" name="marca">
		<option value="0">Select</option>
		<c:if test="${ not empty marcas }">
			<c:forEach items="${marcas }" var="m">
				<c:if test="${not empty marca  }">
					<c:if test="${m.id eq marca.id  }">
						<option value="${m.id}" selected="selected"><c:out value="${m.nome}"></c:out></option>
					</c:if>
				</c:if>
				<c:if test="${(empty marca) || (m.id ne marca.id) }">
					<option value="${m.id}"><c:out value="${m.nome}"></c:out></option>
				</c:if>
			</c:forEach>
		</c:if>
		</select>
	</td>
</tr>
<tr>
<td><input id="bt" name="bt" value="Inserir" type="submit"></td>
<td><input id="bt" name="bt" value="Atualizar" type="submit"></td>
<td><input id="bt" name="bt" value="Excluir" type="submit"></td>
<td><input id="bt" name="bt" value="Listar" type="submit"></td>
</tr>
</table>
</form>
</div>
<br/>
<div align="center">
<c:if test="${not empty val}">
<c:out value="${val}"></c:out>
</c:if>
</div>
<br/>
<div align="center">
<c:if test="${not empty produtos }">
<table border="1">
<thead>
<tr>
<th>id</th>
<th>nome</th>
<th>preco</th>
<th>marca</th>
</tr>
</thead>
<tbody>
<c:forEach items="${produtos }" var="p">
<tr>
<td><c:out value="${ p.id}"></c:out></td>
<td><c:out value="${ p.nome}"></c:out></td>
<td><c:out value="${ p.preco}"></c:out></td>
<td><c:out value="${ p.mark}"></c:out></td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
</div>
</body>
</html>