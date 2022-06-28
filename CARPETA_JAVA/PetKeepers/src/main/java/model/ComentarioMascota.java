package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComentarioMascota extends Comentario {

	// ATRIBUTOS
	@JsonProperty("id_mascota")
	private int id_mascota;
	@JsonProperty("id_suscriptor")
	private int id_suscriptor;
	@JsonProperty("nombre_suscriptor")
	private String nombre_suscriptor;
	
	// CONSTRUCTORES
	@JsonCreator
	public ComentarioMascota(
			@JsonProperty("id") final int id, 
			@JsonProperty("comentario") final String comentario,
			@JsonProperty("id_mascota") final int id_mascota,
			@JsonProperty("id_suscriptor") final int id_suscriptor,
			@JsonProperty("fecha") final String fecha,
			@JsonProperty("nombre_suscriptor") final String nombre_suscriptor) {
		super(id, comentario, fecha);
		this.id_mascota = id_mascota;
		this.id_suscriptor = id_suscriptor;
		this.nombre_suscriptor = nombre_suscriptor;
	}
	
	@JsonCreator
	public ComentarioMascota() {
		super();
	}

	@JsonProperty("id_mascota")
	public int getId_mascota() {
		return id_mascota;
	}

	@JsonProperty("id_mascota")
	public void setId_mascota(int id_mascota) {
		this.id_mascota = id_mascota;
	}

	@JsonProperty("id_suscriptor")
	public int getId_suscriptor() {
		return id_suscriptor;
	}

	@JsonProperty("id_suscriptor")
	public void setId_suscriptor(int id_suscriptor) {
		this.id_suscriptor = id_suscriptor;
	}
	
	@JsonProperty("nombre_suscriptor")
	public String getNombre_suscriptor() {
		return nombre_suscriptor;
	}
	
}
