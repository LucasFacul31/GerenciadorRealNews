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

import model.Noticia;
import service.NoticiaService;

/**
 * Servlet implementation class ListarNoticias
 */
@WebServlet(name = "ListarNoticias.do", urlPatterns = { "/ListarNoticias.do" })
public class ListarNoticias extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//protected void service(HttpServletRequest request, HttpServletResponse response)
	//		throws ServletException, IOException {
//
	//}
	

	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
			NoticiaService noticiaService = new NoticiaService();
			ArrayList<Noticia> noticias = noticiaService.listarNoticias();
			PrintWriter saida = resp.getWriter();
			if (noticias.isEmpty()) {
				saida.println("<p>Não há notícias cadastradas.</p>");
			} else {
		        String json = new Gson().toJson(noticias);
		        PrintWriter out = resp.getWriter();
		        setAccessControlHeaders(resp);
		        resp.setContentType("application/json");
		        resp.setCharacterEncoding("UTF-8");
		        out.print(json);
		        out.flush(); 
			}
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
	      resp.setHeader("Access-Control-Allow-Methods", "GET");
	  }

}
