<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1 style="text-align:center">Insertar Registros</h1>
<form name="forml"method="get"action="ControladorProductos">
<input type="hidden" name="instruccion" value="actualizar">
<input type="hidden" name="codigoProducto" value="${productosIden.producto_id}">

  <table width="50%"border="0">
    <tr>
       <td>Seccion</td>
       <td><label for="seccion"></label>
       <input type="text"name="seccion"id="seccion" value="${productosIden.seccion}" ></td>
    </tr>
    <tr>
       <td>Nombre Articulo</td>
       <td><label for="nombre"></label>
       <input type="text"name="nombre"id="nombre" value="${productosIden.nombre}" ></td>
    </tr>
    <tr>
       <td>Fecha</td>
       <td><label for="fecha"></label>
       <input type="text"name="fecha"id="fecha" value="${productosIden.fecha}" ></td>
    </tr>
    
    <tr>
       <td>Precio</td>
       <td><label for="precio"></label>
       <input type="text"name="precio"id="precio" value="${productosIden.precio}" ></td>
    </tr>
    <tr>
       <td>Nacionalidad</td>
       <td><label for="nacionalidad"></label>
       <input type="text"name="nacionalidad"id="nacionalidad" value="${productosIden.nacionalidad}" ></td>
    </tr>
    
    <tr>
       <td><input type="submit" name="envio" id="envio" value="Enviar"></td>
       <td><input type="reset" name="borrar" id="borrar" value="Restablecer"></td>
    </tr>
    
    
    </table>
    </form>


</body>
</html>