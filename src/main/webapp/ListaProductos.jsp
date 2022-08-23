<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<style>

.cabecera{

	font-size:1.2em;
	font-weigth:bold;
	color:#FFFFFF;
	background-color: #08088A;
	
}

.filas{

	text-align: center;
	background-color: #5882FA;
	

table{
	float:left;
}

#contenedorBoton{
	
	margin-left:1000px;

}

}

</style>

</head>
<body>

	<table>
	<tr>
	<td class="cabecera">Código Articulo        </td>
	<td class="cabecera">Sección</td>
	<td class="cabecera">Nombre Articulo</td>
	<td class="cabecera">Fecha</td>
	<td class="cabecera">Precio</td>
	<td class="cabecera">Nacionalidad</td>
	<td class="cabecera">Modificar</td>
	</tr>
	
	<c:forEach var="tempProd" items="${listaProductos}">
	
	<!-- link para cada producto con su campo clave -->
	
	<c:url var="linkTemp" value="ControladorProductos">
	
		<c:param name="instruccion" value="cargar"></c:param>
	
		<c:param name="articulo_id" value="${tempProd.producto_id }"></c:param>
	</c:url>
	
	<!-- link para elimninar cada registro para eliminar su campo clave -->
	
	<c:url var="linkTempEliminar" value="ControladorProductos">
	
		<c:param name="instruccion" value="eliminar"></c:param>
	
		<c:param name="articulo_id" value="${tempProd.producto_id }"></c:param>
	</c:url>
	
	
	
	<tr>
	<td class="filas">${tempProd.producto_id }</td>
	<td class="filas">${tempProd.seccion }</td>
	<td class="filas">${tempProd.nombre }</td>
	<td class="filas">${tempProd.precio}</td>
	<td class="filas">${tempProd.fecha }</td>
	<td class="filas">${tempProd.nacionalidad }</td>
	<td class="filas"><a href="${linkTemp}">Modificar</a>&nbsp; &nbsp; &nbsp; <a href="${linkTempEliminar}">Eliminar</a> </td>
	</tr>

	</c:forEach>
	
	</table>
	
	
	<div id="contenedorBoton">
		<input type="button" value="Ingresar mercaderia" onclick="window.location.href='insertarProducto.jsp'">
	</div>
            

</body>
</html>