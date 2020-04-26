package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Comentario;

public class ComentarioDAO {

	private Connection conexao;

	public ComentarioDAO() {
		this.conexao = ConnectionFactory.conectar();
	}

	public void cadastrar(Comentario comentario) {

		String sql = "INSERT INTO comentario " + "(nome, texto, fk_noticia_id) " + "VALUES (?, ?, ?);";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setString(1, comentario.getNome());
			pst.setString(2, comentario.getTexto());
			pst.setInt(3, comentario.getIdNoticia());

			pst.execute();

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Comentario.");
			ex.printStackTrace();

		}
	}

	public void alterar(Comentario comentario) {

		String sql = "UPDATE comentario " + "SET nome = ?, texto = ? " + "WHERE id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setString(1, comentario.getNome());
			pst.setString(2, comentario.getTexto());
			pst.setInt(3, comentario.getId());

			pst.execute();

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Comentario.");
			ex.printStackTrace();

		}
	}

	public void excluir(Comentario comentario) {

		String sql = "DELETE FROM comentario " + "WHERE id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setInt(1, comentario.getId());

			pst.execute();

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Comentario.");
			ex.printStackTrace();

		}
	}

	public Comentario consultar(int id) {

		String sql = "SELECT * FROM comentario " + "WHERE id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setInt(1, id);
			ResultSet resultado = pst.executeQuery();

			Comentario comentario = new Comentario();
			if (resultado.next()) {
				comentario.setId(id);
				comentario.setIdNoticia(resultado.getInt("fk_noticia_id"));
				comentario.setNome(resultado.getString("nome"));
				comentario.setTexto(resultado.getString("texto"));
			}
			return comentario;

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Comentario.");
			ex.printStackTrace();

			return null;
		}
	}

	public ArrayList<Comentario> listarComentarios() {

		String sql = "SELECT * FROM comentario";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			ResultSet resultado = pst.executeQuery();

			ArrayList<Comentario> lista = new ArrayList<>();
			while (resultado.next()) {
				Comentario comentario = new Comentario();
				comentario.setId(resultado.getInt("id"));
				comentario.setIdNoticia(resultado.getInt("fk_noticia_id"));
				comentario.setNome(resultado.getString("nome"));
				comentario.setTexto(resultado.getString("texto"));
				lista.add(comentario);
			}
			return lista;

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Comentario.");
			ex.printStackTrace();

			return null;
		}
	}

	public ArrayList<Comentario> listarComentariosNoticia(int idNoticia) {

		String sql = "SELECT * FROM comentario AS c " + "INNER JOIN noticia AS n " + "ON n.id = c.fk_noticia_id "
				+ "WHERE n.id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setInt(1, idNoticia);
			ResultSet resultado = pst.executeQuery();

			ArrayList<Comentario> lista = new ArrayList<>();
			while (resultado.next()) {
				Comentario comentario = new Comentario();
				comentario.setId(resultado.getInt("id"));
				comentario.setIdNoticia(resultado.getInt("fk_noticia_id"));
				comentario.setNome(resultado.getString("nome"));
				comentario.setTexto(resultado.getString("texto"));
				lista.add(comentario);
			}
			return lista;

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Comentario.");
			ex.printStackTrace();

			return null;
		}
	}

}
