package Productos;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorProductos
 */
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloProductos modeloProductos;
	
	@Resource( name="jdbc/productos")
	
	private DataSource miPool;
	
	
	
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {		
			modeloProductos = new ModeloProductos(miPool);
		}catch(Exception e) {
			throw new ServletException(e);
			
		}
	}





	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//leer el parametro que llega del formulario
		
		String parametro = request.getParameter("instruccion");
		
		
		//sino se envia el parametro, listar productos
		
		if(parametro==null) parametro="listar";
		
		
		switch(parametro) {
		
		case "listar":
			
			//Redirigir el flujo de ejecucion al metodo adecuado
			obtenerProductos(request,response);
			break;
			
		case "insertar":
			
			agregarProductos(request,response);
			break;
			
		case "cargar":
			try {
			actualizarProductos(request,response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "actualizar":
			
			try {
				actualizaNuevoProductos(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "eliminar":
			
			try {
				eliminarProducto(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
			
		default:
			obtenerProductos(request,response);
		
		}
		
		
		
		
		
	}





	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		//capturar el codigo articulo
		
		Integer id = Integer.parseInt(request.getParameter("articulo_id"));
		
		//borrar al producto de la BBDD
		
		modeloProductos.eliminarProductos(id);
		
		//Volver a la lista de productos
		
		obtenerProductos(request,response);
	}





	private void actualizaNuevoProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//Leer los datos que le vienen del formulario actualizar
	

		Integer id = Integer.parseInt(request.getParameter("codigoProducto"));
	
		String seccion =request.getParameter("seccion");
		
		String nombre =request.getParameter("nombre");
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha= null;
		
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		String nacionalidad =request.getParameter("nacionalidad");
		
		//Crear un objeto de tipo Producto con la info del formulario
		
		Productos productoActualizado = new Productos(id,seccion,nombre,fecha,precio,nacionalidad);
		
		System.out.print("aqui vamos");
		//Actualizar la BBDD con la info del obj Producto
		modeloProductos.actualizarProducto(productoActualizado);
		System.out.print("aqui vamos");
		
		//Volver al listado con la info actualizada
		obtenerProductos(request,response);
	}





	private void actualizarProductos(HttpServletRequest request, HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
		
		//leer el codigo de id_productos
		
		String codigoArticulo = request.getParameter("articulo_id");
		
		
		
		//Enviar codigo articulo a modelo
		
		Productos elProducto =modeloProductos.getProducto(codigoArticulo);
		
		//Colocar el atributo correspondiente al id_producto
		
		request.setAttribute("productosIden", elProducto);
		// enviar producto al formulario de actualizar
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarProducto.jsp");
		
		dispatcher.forward(request, response);
		
	}





	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//leer la informacion del formulario
		
		
		String seccion =request.getParameter("seccion");
		
		String nombre =request.getParameter("nombre");
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha= null;
		
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		String nacionalidad =request.getParameter("nacionalidad");
		
		//crear un objeto de tipo producto
		
		Productos producto = new Productos(seccion,nombre,fecha,precio,nacionalidad);
		
		try {
			modeloProductos.insertarProductos(producto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//enviar el objeto al modelo e insertarlo a la base de datos
		
		//volver al listado de productos
		
		obtenerProductos(request, response);
	}





	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {

		List<Productos> productos;
		
		
		try {
			
			productos=modeloProductos.listaProductos();
			
			request.setAttribute("listaProductos", productos);
			
			
			RequestDispatcher miDispatcher =request.getRequestDispatcher("/ListaProductos.jsp");
			 
			miDispatcher.forward(request, response);
	
	}catch(Exception e) {
		}// TODO Auto-generated method stub
		
	}

}
