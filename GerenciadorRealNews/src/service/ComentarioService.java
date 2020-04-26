package service;

import java.util.ArrayList;

import dao.ComentarioDAO;
import model.Comentario;

public class ComentarioService {
	private ComentarioDAO comentarioDAO;
	
	ComentarioService(){
		this.comentarioDAO = new ComentarioDAO();
	}

	public void cadastrar(Comentario comentario) {
		this.comentarioDAO.cadastrar(comentario);
	}

	public void alterar(Comentario comentario) {
		this.comentarioDAO.alterar(comentario);
	}

	public void excluir(Comentario comentario) {
		this.comentarioDAO.excluir(comentario);
	}

	public Comentario consultar(int id) {
		return this.comentarioDAO.consultar(id);
	}

	public ArrayList<Comentario> listarComentarios() {
		return this.comentarioDAO.listarComentarios();
	}

	public ArrayList<Comentario> listarComentariosNoticia(int idNoticia) {
		return this.comentarioDAO.listarComentariosNoticia(idNoticia);
	}
}
