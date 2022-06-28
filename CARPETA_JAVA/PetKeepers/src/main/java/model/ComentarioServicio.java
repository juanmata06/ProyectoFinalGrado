package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComentarioServicio extends Comentario {

	// ATRIBUTOS
	@JsonProperty("id_servicio")
	private int id_servicio;
	@JsonProperty("id_usuario")
	private int id_usuario;
	@JsonProperty("nombre_usuario")
	private String nombre_usuario;

	// CONSTRUCTORES
	@JsonCreator
	public ComentarioServicio(
			@JsonProperty("id") final int id, 
			@JsonProperty("comentario") final String comentario,
			@JsonProperty("id_servicio") final int id_servicio,
			@JsonProperty("id_usuario") final int id_usuario,
			@JsonProperty("fecha") final String fecha,
			@JsonProperty("nombre_usuario") final String nombre_usuario) {
		super(id, comentario, fecha);
		this.id_servicio = id_servicio;
		this.id_usuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
	}

	@JsonCreator
	public ComentarioServicio() {
		super();
	}
	
	//ENCAPSULACION
	@JsonProperty("id_servicio")
	public int getId_Servicio() {
		return id_servicio;
	}

	@JsonProperty("id_servicio")
	public void setId_Servicio(int id_servicio) {
		this.id_servicio = id_servicio;
	}

	@JsonProperty("id_usuario")
	public int getId_Usuario() {
		return id_usuario;
	}
	
	@JsonProperty("nombre_usuario")
	public String getNombre_usuario() {
		return nombre_usuario;
	}

	@JsonProperty("id_usuario")
	public void setId_Usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
}
