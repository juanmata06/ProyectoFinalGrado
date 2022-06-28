package model;

//CLASES
//DEPENDENCIAS
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
//OBJETOS

public class Suscripcion {
	// ATRIBUTOS
	@JsonProperty("id")
	private int id;

	@JsonProperty("tiempo")
	private String tiempo;

	@JsonProperty("precio")
	private String precio;

	@JsonProperty("descripcion")
	private String descripcion;
	
	// CONSTRUCTORES
	@JsonCreator
	public Suscripcion(@JsonProperty("id") final int id, 
			@JsonProperty("tiempo") final String tiempo,
			@JsonProperty("precio") final String precio, 
			@JsonProperty("descripcion") final String descripcion) {
		this.id = id;
		this.tiempo = tiempo;
		this.precio = precio;
		this.descripcion = descripcion;
	}

	@JsonCreator
	public Suscripcion() {}
	
	// ENCAPSULACION
	@JsonProperty("id")
	public int getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("tiempo")
	public String getTiempo() {
		return tiempo;
	}

	@JsonProperty("precio")
	public String getPrecio() {
		return precio;
	}

	@JsonProperty("descripcion")
	public String getDescripcion() {
		return descripcion;
	}

}
