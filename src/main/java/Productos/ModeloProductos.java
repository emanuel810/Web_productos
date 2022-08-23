package Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public class ModeloProductos {
	
	private DataSource origenDatos;
	
	
	
	public ModeloProductos(DataSource origenDatos){
		this.origenDatos = origenDatos;
	}
	
	
	public List<Productos> listaProductos() throws Exception{
		
		List<Productos> productos = new ArrayList<>();
		
		Connection miConexion = null;
		
		Statement miStatement = null;
		
		ResultSet miResultSet = null;
		
		
		try {
		miConexion= origenDatos.getConnection();
		
		String sql = "SELECT * FROM productos";
		
		miStatement = miConexion.createStatement();
		
		miResultSet= miStatement.executeQuery(sql);
		
		
		while(miResultSet.next()) {
			
			Integer id = miResultSet.getInt("producto_id");
			String seccion = miResultSet.getString("seccion");
			String nombre_articulo = miResultSet.getString("nombre_articulo");
			double precio = miResultSet.getDouble("precio");
			Date fecha = miResultSet.getDate("fecha");
			String nacionalidad = miResultSet.getString("nacionalidad");
			
			
			Productos productosTemp = new Productos(id,seccion,nombre_articulo,fecha,precio,nacionalidad);
			
			productos.add(productosTemp);
		}}finally{
			miStatement.close();
			miConexion.close();
		}
		
		
		
		return productos;
	}




	public void insertarProductos(Productos producto) throws Exception{
		// TODO Auto-generated method stub
		
		//obtener conexion
		Connection miConexion = null;
		
		PreparedStatement miStatement = null;
				
		//crear instruccion
		
		try {
			miConexion = origenDatos.getConnection();

	
		String sql = "INSERT INTO productos(seccion,nombre_articulo,precio,fecha,nacionalidad) "
			+"VALUES(?,?,?,?,?)";
		
		
		miStatement = miConexion.prepareStatement(sql);
		
		
		//establecer parametros
		
		miStatement.setString(1, producto.getSeccion());
		
		miStatement.setString(2, producto.getNombre());
		
		miStatement.setDouble(3, producto.getPrecio());
		
		java.util.Date utilDate = producto.getFecha();
		
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
		
		miStatement.setDate(4, fechaConvertida );
		
		miStatement.setString(5, producto.getNacionalidad());
		
		miStatement.execute();
		
		
		//ejecutar el sql
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			miStatement.close();
			miConexion.close();
		}
		
		
	}


	public Productos getProducto(String codigoArticulo) throws Exception {
		// TODO Auto-generated method stub
		
		//Establecer la conexion con la BBDD
		
		Productos elProducto = null;
		
		Connection miConexion = null;
		
		PreparedStatement miStatement = null;
		
		ResultSet miResultSet =null;
		
		String id_producto = codigoArticulo;
		
		try {
			miConexion=origenDatos.getConnection();
		
		
		//Crear SQL que busque el producto
		String sql="SELECT * FROM productos WHERE producto_id=?";
		
		//Crear la consulta preparada
		miStatement = miConexion.prepareStatement(sql);
		
		
		//Establecer parametros
		
		miStatement.setString(1, id_producto);
		
		//Ejecutar la consulta
		miResultSet=miStatement.executeQuery();
		
		//Obtener datos dela respuesta
		
		if(miResultSet.next()) {
			Integer id = miResultSet.getInt("producto_id");
			String seccion = miResultSet.getString("seccion");
			String nombre_articulo = miResultSet.getString("nombre_articulo");
			double precio = miResultSet.getDouble("precio");
			Date fecha = miResultSet.getDate("fecha");
			String nacionalidad = miResultSet.getString("nacionalidad");
			
			
			elProducto = new Productos(id,seccion,nombre_articulo,fecha,precio,nacionalidad);
			
			
		}else {
			throw new Exception("No hemos encontrado el id"+codigoArticulo);
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			miStatement.close();
			miConexion.close();
		}
		
			
			
			
			
		return elProducto;
	}


	public void actualizarProducto(Productos productoActualizado) throws SQLException {
		// TODO Auto-generated method stub
		
		
		//Establecer la conexion
		Connection miConexion = null;
		
		PreparedStatement miStatement = null;
		
		
		try {
		miConexion = origenDatos.getConnection();
		//crear sentencia SQL
		
		String sql = "UPDATE productos SET seccion=?, nombre_articulo=?, precio=?, fecha=?, nacionalidad=? WHERE producto_id=?";

		
		//Crear la consulta preparara
		
		miStatement= miConexion.prepareStatement(sql);
		
		//establecer los parametros
		
		miStatement.setString(1, productoActualizado.getSeccion());
		
		miStatement.setString(2, productoActualizado.getNombre());
		
		miStatement.setDouble(3, productoActualizado.getPrecio());
		
		java.util.Date utilDate = productoActualizado.getFecha();
		
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
		
		miStatement.setDate(4, fechaConvertida );
		
		miStatement.setString(5, productoActualizado.getNacionalidad() );
		
		miStatement.setInt(6, productoActualizado.getProducto_id());
		
		
		
		//ejecurar la instruccion SQL
		miStatement.execute();
		
		}finally{
			miStatement.close();
			miConexion.close();
		}
		
		
	}


	public void eliminarProductos(Integer id) throws Exception{
		// TODO Auto-generated method stub
		
		//Establecer la conexion
		Connection miConexion = null;
		
		PreparedStatement miStatement = null;
		
		try {
		miConexion = origenDatos.getConnection();
		
		//crear instruccion SQL de eliminacion
		
		String sql = "DELETE FROM productos WHERE producto_id=?";
		
		//preparar la consulta
		
		miStatement =miConexion.prepareStatement(sql);
		
		//Establecer parametros de consulta
		miStatement.setInt(1, id);
		
		//Ejecutar secuencia SQL
		
		miStatement.execute();
		}finally{
			miStatement.close();
			miConexion.close();
		}
		
		
	}
	
	

}
