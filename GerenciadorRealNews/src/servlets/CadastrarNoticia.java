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

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter saida = response.getWriter();
		
		String titulo = request.getParameter("titulo"),
				descricao = request.getParameter("descricao"),
				texto = request.getParameter("texto");

		Noticia noticia = new Noticia();

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

		noticia.setTitulo(titulo);
		noticia.setDescricao(descricao);
		noticia.setTexto(texto);

		NoticiaService noticiaService = new NoticiaService();
		noticiaService.cadastrar(noticia);

		saida.println("<h1>Notícia cadastrada com sucesso!</h1>");

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
