package ar.com.codoacodo.rama;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

	public String driver="com.mysql.cj.jdbc.Driver";
	
	public Connection getConnexion () throws ClassNotFoundException {
		
		Connection conexion=null;
		try {
			
			Class.forName(driver);
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculascac","root","rama17");
			
		}
		catch(SQLException e)
		{
			System.out.println("Hay un Error: " + e);
		}
		return conexion;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException , SQLException{
		
		Connection conexion=null;
		Conexion con=new Conexion();
		conexion=con.getConnexion();
		
		PreparedStatement ps;
		ResultSet rs;
		
		ps=conexion.prepareStatement("select * from pelicula");
		rs=ps.executeQuery();
		
		while(rs.next()) {
			
			String titulo=rs.getString("titulo");
			System.out.println(titulo);
			
		}
		
		System.out.println("Conexion Exitosa");

	}

}
