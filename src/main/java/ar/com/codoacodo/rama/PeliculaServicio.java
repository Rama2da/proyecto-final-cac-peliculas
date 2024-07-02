package ar.com.codoacodo.rama;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaServicio {

	
	private Conexion conexion;
	
	public PeliculaServicio () {
		
		this.conexion = new Conexion();
	}
	
	public List <Pelicula> getAllPeliculas() throws SQLException,ClassNotFoundException {
		
		
		List<Pelicula> peliculas=new ArrayList<>();
		Connection con=conexion.getConnexion();
		String sql = "Select * from pelicula";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			
			Pelicula pelicula=new Pelicula(
					rs.getInt("id"),
					rs.getString("titulo"),
					rs.getString("genero"),
					rs.getString("duracion"),
					rs.getString("director"),
					rs.getString("reparto"),
					rs.getString("sinopsis"),
					rs.getString("imagen")			
			);
			
			peliculas.add(pelicula);
		}
		
		rs.close();
		ps.close();
		con.close();
		return peliculas;
		
	} 
	
	public Pelicula getPeliculaById(int id) throws SQLException,ClassNotFoundException {
		
		Pelicula pelicula=null;
		Connection con=conexion.getConnexion();
		String sql = "Select * from peliculas where id = ?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		
		
		while(rs.next()) {
			
					pelicula=new Pelicula(
					rs.getInt("id"),
					rs.getString("titulo"),
					rs.getString("genero"),
					rs.getString("duracion"),
					rs.getString("director"),
					rs.getString("reparto"),
					rs.getString("sinopsis"),
					rs.getString("imagen")			
			);
		}
		
		rs.close();
		ps.close();
		con.close();
		return pelicula;
		
	}
	
	public void addPelicula (Pelicula pelicula) throws SQLException,ClassNotFoundException {
		
		Connection con=conexion.getConnexion();
		String sql = "insert into pelicula (titulo,genero,duracion,director,reparto,sinopsis,imagen)" 
				+ "VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, pelicula.getTitulo());
		ps.setString(2, pelicula.getGenero());
		ps.setString(3, pelicula.getDuracion());
		ps.setString(4, pelicula.getDirector());
		ps.setString(5, pelicula.getReparto());
		ps.setString(6, pelicula.getSinopsis());
		ps.setString(7, pelicula.getImagen());
		
		ps.executeUpdate();
		ps.close();
		con.close();
	} 	
	
	public void updatePelicula (Pelicula pelicula) throws SQLException,ClassNotFoundException {
		
		Connection con=conexion.getConnexion();
		String sql = "UPDATE pelicula set titulo=?,genero=?,duracion=?,director=?,reparto=?,sinopsis=?,imagen=?"
				+ "WHERE id=?" ;
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, pelicula.getTitulo());
		ps.setString(2, pelicula.getGenero());
		ps.setString(3, pelicula.getDuracion());
		ps.setString(4, pelicula.getDirector());
		ps.setString(5, pelicula.getReparto());
		ps.setString(6, pelicula.getSinopsis());
		ps.setString(7, pelicula.getImagen());
		ps.setInt(8, pelicula.getId());
		
		ps.executeUpdate();
		ps.close();
		con.close();
		
	}
	
	public void deletePelicula (int id) throws SQLException,ClassNotFoundException {
		Connection con=conexion.getConnexion();
		String sql = "DELETE FROM pelicula where id = ?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1 , id);			
		ps.executeUpdate();
		
		ps.close();
		con.close();
		
	}
}