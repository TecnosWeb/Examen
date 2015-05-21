package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ConexionBDMySQL;

/**
 * Servlet implementation class InsertarPais
 */
@WebServlet("/registro")
public class InsertarPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarPais() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConexionBDMySQL con = new ConexionBDMySQL();
		con.InicializacionDB("jdbc:mysql://localhost:3306/examen", "root", "pass");
		String codigo = request.getParameter("codigoTxt");
		String nombre = request.getParameter("nombreTxt");
		String continente = request.getParameter("continenteTxt");
		String poblacion = request.getParameter("poblacionTxt");
		con.insertar(codigo, nombre, continente, poblacion);
	}

}
