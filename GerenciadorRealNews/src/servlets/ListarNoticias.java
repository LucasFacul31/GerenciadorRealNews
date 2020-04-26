package servlets;

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

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticiaService noticiaService = new NoticiaService();
		ArrayList<Noticia> noticias = noticiaService.listarNoticias();
		PrintWriter saida = response.getWriter();

		saida.println("<h1>Notícias:</h1>");
		if (noticias.isEmpty()) {
			saida.println("<p>Não há notícias cadastradas.</p>");
		} else {
			for (Noticia noticia : noticias) {
				saida.println("<p><b>Id: </b><a href='Noticia.do?id=" + noticia.getId() + "'>" + noticia.getId()
						+ "</a></p>");
				saida.println("<p><b>Título: </b><a href='Noticia.do?id=" + noticia.getId() + "'>" + noticia.getTitulo() + "</a></p>");
				saida.println("<p><b>Descrição: </b>" + noticia.getDescricao() + "</p>");
				saida.println("<p><b>Texto: </b>" + noticia.getTexto() + "</p>");
				saida.println("<hr>");
			}
		}

		saida.println("<a href=\"CadastrarNoticia.jsp\">Cadastrar notícia</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"AlterarNoticia.jsp\">Alterar notícia</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"ExcluirNoticia.jsp\">Excluir notícia</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"ConsultarNoticia.jsp\">Consultar notícia</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"ListarNoticias.do\">Listar notícias</a>");
	}

}
