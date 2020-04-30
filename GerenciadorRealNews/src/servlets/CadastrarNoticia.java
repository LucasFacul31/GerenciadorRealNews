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
 * Servlet implementation class CadastrarNoticia
 */
@WebServlet(name = "CadastrarNoticia.do", urlPatterns = { "/CadastrarNoticia.do" })
public class CadastrarNoticia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter saida = response.getWriter();
		
		String titulo = request.getParameter("titulo"),
				descricao = request.getParameter("descricao"),
				texto = request.getParameter("texto");

		if (titulo == "" || titulo == null || titulo.length() <= 0 || titulo.isEmpty()) {
			saida.println("<h1>Volte e informe o título!</h1>");
			saida.println("<br>");
			saida.println("<a href='CadastrarNoticia.jsp'>Voltar</a>");
			return;
		}

		if (descricao == "" || descricao == null || descricao.length() <= 0 || descricao.isEmpty()) {
			saida.println("<h1>Volte e informe a descrição!</h1>");
			saida.println("<br>");
			saida.println("<a href='CadastrarNoticia.jsp'>Voltar</a>");
			return;
		}

		if (texto == "" || texto == null || texto.length() <= 0 || texto.isEmpty()) {
			saida.println("<h1>Volte e informe o texto!</h1>");
			saida.println("<br>");
			saida.println("<a href='CadastrarNoticia.jsp'>Voltar</a>");
			return;
		}

		Noticia noticia = new Noticia();
		noticia.setTitulo(titulo);
		noticia.setDescricao(descricao);
		noticia.setTexto(texto);

		NoticiaService noticiaService = new NoticiaService();
		noticiaService.cadastrar(noticia);

		saida.println("<h1>Notícia cadastrada com sucesso!</h1>");

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
