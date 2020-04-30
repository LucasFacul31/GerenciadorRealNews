package servlets;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comentario;
import service.ComentarioService;
import service.NoticiaService;

/**
 * Servlet implementation class Noticia
 */
@WebServlet(name = "Noticia.do", urlPatterns = { "/Noticia.do" })
public class Noticia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 

			String nome = request.getParameter("nome"),
					texto = request.getParameter("texto"),
					parameterIdNoticia = request.getParameter("idNoticia") != null && !request.getParameter("idNoticia").isEmpty()
							? request.getParameter("idNoticia")
									: "-1";
			int idNoticia = Integer.parseInt(parameterIdNoticia);

			Comentario comentario = new Comentario();
			comentario.setIdNoticia(idNoticia);
			comentario.setNome(nome);
			comentario.setTexto(texto);
			
			if(comentario.isValid()) {
				ComentarioService comentarioService = new ComentarioService();
				comentarioService.cadastrar(comentario);
			}
		 
		PrintWriter out = response.getWriter();

		String parameterId = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
				? request.getParameter("id")
				: "-1";
		int id = Integer.parseInt(parameterId);

		if (id <= 0) {
			out.println("<h1>Notícia não encontrada.</h1>");
			return;
		}

		NoticiaService noticiaService = new NoticiaService();
		model.Noticia resultado = noticiaService.consultar(id);

		if (!resultado.isValid()) {
			out.println("<h1>Notícia não encontrada.</h1>");
		} else {
			ComentarioService comentarioService = new ComentarioService();
			ArrayList<Comentario> comentarios = comentarioService.listarComentariosNoticia(id);
			resultado.setComentario(comentarios);
			
			 String json = new Gson().toJson(resultado);
			 setAccessControlHeaders(response);
		     response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     out.print(json);
		     out.flush(); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAccessControlHeaders(response);
		
		doGet(request, response);
	}
	
	  //for Preflight
	  @Override
	  protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
	      setAccessControlHeaders(resp);
	      resp.setStatus(HttpServletResponse.SC_OK);
	  }
	  
	  

	  private void setAccessControlHeaders(HttpServletResponse resp) {
	      resp.setHeader("Access-Control-Allow-Origin", "*");
	      resp.setHeader("Access-Control-Allow-Methods", "*");
	  }

	

}
