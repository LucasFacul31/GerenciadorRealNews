package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Noticia;

public class NoticiaDAO {

	private Connection conexao;

	public NoticiaDAO() {
		this.conexao = ConnectionFactory.conectar();
	}

	public void cadastrar(Noticia noticia) {

		String sql = "INSERT INTO noticia " + "(titulo, descricao, texto) " + "VALUES (?, ?, ?);";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setString(1, noticia.getTitulo());
			pst.setString(2, noticia.getDescricao());
			pst.setString(3, noticia.getTexto());

			pst.execute();

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Noticia.");
			ex.printStackTrace();

		}
	}

	public void alterar(Noticia noticia) {

		String sql = "UPDATE noticia " + "SET titulo = ?, descricao = ?, texto = ? " + "WHERE id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setString(1, noticia.getTitulo());
			pst.setString(2, noticia.getDescricao());
			pst.setString(3, noticia.getTexto());
			pst.setInt(4, noticia.getId());

			pst.execute();

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Noticia.");
			ex.printStackTrace();

		}
	}

	public void excluir(Noticia noticia) {

		String sql = "DELETE FROM noticia " + "WHERE id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setInt(1, noticia.getId());

			pst.execute();

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Noticia.");
			ex.printStackTrace();

		}
	}

	public Noticia consultar(int id) {

		String sql = "SELECT * FROM noticia " + "WHERE id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setInt(1, id);
			ResultSet resultado = pst.executeQuery();

			Noticia noticia = new Noticia();
			if (resultado.next()) {
				noticia.setId(id);
				noticia.setTitulo(resultado.getString("titulo"));
				noticia.setDescricao(resultado.getString("descricao"));
				noticia.setTexto(resultado.getString("texto"));
			}
			return noticia;

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Noticia.");
			ex.printStackTrace();

			return null;
		}
	}

	public ArrayList<Noticia> listarNoticias() {

		String sql = "SELECT * FROM noticia;";

		try (PreparedStatement pst = conexao.prepareStatement(sql)) {

			ResultSet resultado = pst.executeQuery();

			ArrayList<Noticia> lista = new ArrayList<>();
			while (resultado.next()) {
				Noticia noticia = new Noticia();
				noticia.setId(resultado.getInt("id"));
				noticia.setTitulo(resultado.getString("titulo"));
				noticia.setDescricao(resultado.getString("descricao"));
				noticia.setTexto(resultado.getString("texto"));
				lista.add(noticia);
			}
			return lista;

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Noticia.");
			ex.printStackTrace();

			return null;
		}
	}

}
