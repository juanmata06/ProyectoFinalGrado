package model;

//CLASES
//DEPENDENCIAS
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//OBJETOS
public class Comentario {

	// ATRIBUTOS
	@JsonProperty("id")
	private int id;
	@JsonProperty("comentario")
	private String comentario;
	@JsonProperty("fecha")
	private String fecha;

	// CONSTRUCTORES
	@JsonCreator
	public Comentario(@JsonProperty("id") final int id, @JsonProperty("comentario") final String comentario,
			@JsonProperty("fecha") final String fecha) {
		this.id = id;
		this.comentario = comentario;
		this.fecha = fecha;
	}

	@JsonCreator
	public Comentario() {
	}

	// ENCAPSULACION
	@JsonProperty("id")
	public int getId() {
		return id;
	}
	
	@JsonProperty("comentario")
	public String getComentario() {
		return comentario;
	}

	@JsonProperty("comentario")
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@JsonProperty("fecha")
	public String getFecha() {
		return fecha;
	}

	@JsonProperty("fecha")
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
