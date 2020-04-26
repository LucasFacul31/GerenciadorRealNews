package servlets;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter saida = response.getWriter();

		String parameterId = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
				? request.getParameter("id")
				: "-1";
		int id = Integer.parseInt(parameterId);

		if (id <= 0) {
			saida.println("<h1>Notícia não encontrada.</h1>");
			return;
		}

		NoticiaService noticiaService = new NoticiaService();
		model.Noticia resultado = noticiaService.consultar(id);

		if (!resultado.isValid()) {
			saida.println("<h1>Notícia não encontrada.</h1>");
		} else {
			saida.println("<h2>RealNews<h2>");
			saida.println("<hr>");
			saida.println("<h1>" + resultado.getTitulo() + "</h1>");
			saida.println("<h3>" + resultado.getDescricao() + "</h3>");
			saida.println("<p>" + resultado.getTexto() + "</p>");
			saida.println("<hr>");
			saida.println("<h4>Comentários</h4>");

			ComentarioService comentarioService = new ComentarioService();
			ArrayList<Comentario> comentarios = comentarioService.listarComentariosNoticia(id);

			if (comentarios.isEmpty()) {
				saida.println("<p>Nenhum comentário.</p>");
			} else {
				for (Comentario comentario : comentarios) {
					saida.println("<h5>" + comentario.getNome() + "</h5>");
					saida.println("<p>" + comentario.getTexto() + "</p>");
					saida.println("<hr>");
				}
			}

			saida.println("<form method='post'>");
			saida.println("<label>Adicionar comentário:</label>");
			saida.println("<br>");
			saida.println("<input type='hidden' name='idNoticia' value='" + id + "'>");
			saida.println("<input type='text' name='nome' placeholder='Seu nome' required>");
			saida.println("<br>");
			saida.println("<textarea name='texto' placeholder='Seu comentário' required></textarea>");
			saida.println("<br>");
			saida.println("<input type='submit' value='Enviar'>");
			saida.println("</form>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		
		ComentarioService comentarioService = new ComentarioService();
		comentarioService.cadastrar(comentario);
		
		doGet(request, response);
	}

}
