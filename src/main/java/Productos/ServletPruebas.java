package Productos;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * Servlet implementation class ServletPruebas
 */
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Establecer el data Source
	
	
	@Resource( name="jdbc/productos")
	
	private DataSource miPool;
	
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter salida = response.getWriter();
		
		response.setContentType("Text/plain");
		
		Connection miConexion = null;
		
		Statement miStatement = null;
		
		ResultSet miResulset = null;
		
		
		try {
			miConexion = miPool.getConnection();
			
			String mysql = "SELECT * FROM productos";
			
			miStatement =miConexion.createStatement();
			
			miResulset=miStatement.executeQuery(mysql);
			
			while(miResulset.next()) {
				String nombre=miResulset.getString(3);
				
				salida.println(nombre);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
