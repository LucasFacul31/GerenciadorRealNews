package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Noticia;
import service.NoticiaService;

/**
 * Servlet implementation class ExcluirNoticia
 */
@WebServlet(name = "ExcluirNoticia.do", urlPatterns = { "/ExcluirNoticia.do" })
public class ExcluirNoticia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter saida = response.getWriter();

		int id = Integer.parseInt(request.getParameter("id"));

		if (id <= 0) {
			saida.println("<h1>Volte e informe um id válido!</h1>");
			saida.println("<br>");
			saida.println("<a href='ExcluirNoticia.jsp'>Voltar</a>");
			return;
		}

		Noticia noticia = new Noticia();
		noticia.setId(id);

		NoticiaService noticiaService = new NoticiaService();
		Noticia existe = noticiaService.consultar(id);

		if (!existe.isValid()) {
			saida.println("<h1>Notícia não encontrada.</h1>");
		} else {
			noticiaService.excluir(noticia);
			saida.println("<h1>Notícia excluída com sucesso!</h1>");
		}

		saida.println("<a href=\"cadastrar.html\">Cadastrar notícia</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"alterar.html\">Alterar notícia</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"excluir.html\">Excluir notícia</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"http://localhost:8081\">Listar notícias</a>");
	}

}
