package ar.com.codoacodo.rama;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/pelicula")
public class ControladorPelicula extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PeliculaServicio peliculaServicio;
	private ObjectMapper objectMapper;
	
	public ControladorPelicula() {
		super();
	}

	@Override
	
	public void init() throws ServletException{
		
		peliculaServicio = new PeliculaServicio();
		objectMapper = new ObjectMapper();
	}
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String pathInfo=req.getPathInfo();
		try {
			
			
			if(pathInfo==null || pathInfo.equals("/")) {
				
				List<Pelicula> peliculas=peliculaServicio.getAllPeliculas();
				String json = objectMapper.writeValueAsString(peliculas);
				resp.setContentType("application/json");
				resp.getWriter().write(json);
			}
			else {
				
				String[] pathParts = pathInfo.split("/");
				int id=Integer.parseInt(pathParts[1]);
				Pelicula pelicula = peliculaServicio.getPeliculaById(id);
				
				if(pelicula!=null) {
					String json = objectMapper.writeValueAsString(pelicula);
					resp.setContentType("application/json");
					resp.getWriter().write(json);
				}
				else {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
				
			}
		}
		
		catch(SQLException | ClassNotFoundException e) {
			resp.getWriter().println(e.getMessage());
			//resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
	}

}
