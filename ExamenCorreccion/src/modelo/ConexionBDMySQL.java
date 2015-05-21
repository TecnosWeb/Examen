package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBDMySQL {
	
	private Connection con=null;

	public ConexionBDMySQL() {
		// TODO Auto-generated constructor stub
	}
	
	public String getResultadoConsulta(String q)
	{
		String cadenaR = "sin resultados";
		try 
		{
			Statement st = this.con.createStatement();
			ResultSet resultados = st.executeQuery(q);
			resultados.next();
			cadenaR = (String) resultados.getObject(1);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cadenaR;
	}
	 
	
	public boolean InicializacionDB(String URL, String Usuario, String Password)
	{
		try
		{
			if (con!=null)
				System.out.println("Ya exixte una conexion");
			else {	
				Class.forName("com.mysql.jdbc.Driver");
				this.con = DriverManager.getConnection(URL, Usuario, Password);
				System.out.println("Conexion Establecida");
				}
			return true;
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	
	
public ResultSet select(String query) throws SQLException{
		
		
		Statement consulta = con.createStatement();
		System.out.println("consulta creada");
		ResultSet resultados = consulta.executeQuery(query);
		return resultados;
		
	}
	public String stringRes(ResultSet Resultados){
		java.sql.ResultSetMetaData md;
		try {
			md = Resultados.getMetaData();
		String tabla = "<table id='tabla1'> \n";
		int i = 0, j =1;
		int m =md.getColumnCount();
		tabla+="<tr>";
		for(j = 1; j <= m; j++){
			tabla+="<th>"+md.getColumnName(j)+"</th>";
		}

		  tabla += "</tr> \n";

				while(  Resultados.next()) {
				  if(++i%2!=0)
					tabla += "<tr> \n";
				  else tabla+="<tr class='alt'>";
				  for (j = 1; j <= m; j++){
				    tabla += "<td>" + Resultados.getString( j ) + "</td> \n";
				  }
				  tabla += "</tr> \n";
				}
				tabla += "</table>";
			return tabla;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void insertar(String codigo, String nombre, String continente, String poblacion){
		String query = "Insert into pais() values('" + codigo + "', '"+ nombre + ",'" + continente + "'," + poblacion +")";		
		try {
			Statement consulta = con.createStatement();
			System.out.println("consulta creada");
			consulta.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public String consultarCodigos(){
		String query = "Select codigo from Pais";
		ResultSet resultados = null;
		try {
			Statement consulta = con.createStatement();
			System.out.println("consulta creada");
			resultados = consulta.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return stringRes(resultados);
		
	}

}
