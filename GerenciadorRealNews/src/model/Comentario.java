package model;

public class Comentario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Attributes
	private int id = -1;
	private int idNoticia = -1;
	private String nome;
	private String texto;

	// Constructors
	public Comentario() {
	}

	public Comentario(int id, int id_noticia, String nome, String texto) {
		this.id = id;
		this.idNoticia = id_noticia;
		this.nome = nome;
		this.texto = texto;
	}

	// Check if is a valid Comentario
	public boolean isValid() {
		return this.id != -1 && this.idNoticia != -1 && this.nome != null && this.texto != null;
	}

	// Get/Set id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// Get/Set id_noticia
	public int getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(int id_noticia) {
		this.idNoticia = id_noticia;
	}

	// Get/Set nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// Get/Set texto
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
