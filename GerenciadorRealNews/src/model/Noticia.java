package model;

public class Noticia implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Attributes
	private int id = -1;
	private String titulo;
	private String descricao;
	private String texto;

	// Constructors
	public Noticia() {
	}

	public Noticia(int id, String titulo, String descricao, String texto) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.texto = texto;
	}

	// Check if is a valid Noticia
	public boolean isValid() {
		return this.id != -1 && this.titulo != null && this.descricao != null && this.texto != null;
	}

	// Get/Set id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// Get/Set titulo
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	// Get/Set descricao
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// Get/Set texto
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
