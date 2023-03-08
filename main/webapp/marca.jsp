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
<form action="MarcaS" method="post">
<table border="1" align="center">
<caption>Marcas</caption>
<tr>
<td colspan="3"><input id="fid" name="fid" type="number" 
placeholder="ID" step="1" min="1"></td>
<td><input id="bt" name="bt" type="submit" 
value="Buscar"></td>
</tr>
<tr>
<td colspan="4"><input id="fnome" name="fnome" type="text" 
placeholder="nome" value="<c:if test="${not empty marca }">
<c:out value="${marca}"></c:out></c:if>"></td>
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
<div align="center">
<c:if test="${not empty lis }">
<table border="1">
<thead>
<tr>
<th>id</th>
<th>nome</th>
</tr>
</thead>
<tbody>
<c:forEach items="${lis }" var="c">
<tr>
<td>
<c:out value="${ c.id}"></c:out>
</td>
<td>
<c:out value="${ c.nome}"></c:out>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
</div>
</body>
</html>