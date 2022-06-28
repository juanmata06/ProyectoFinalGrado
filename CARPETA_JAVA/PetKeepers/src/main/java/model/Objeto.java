package model;

//CLASES
import java.util.ArrayList;
import java.util.Comparator;

//DEPENDENCIAS
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
//OBJETOS

public class Objeto {
	// ATRIBUTOS
	@JsonProperty("id")
	private int id;

	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("descripcion")
	private String descripcion;

	@JsonProperty("precio")
	private double precio;

	@JsonProperty("puntuacion")
	private double puntuacion;

	@JsonProperty("activo")
	private boolean activo;

	@JsonProperty("imagenes")
	private String imagenes;

	// CONSTRUCTORES
	@JsonCreator
	public Objeto(@JsonProperty("id") final int id, @JsonProperty("nombre") final String nombre,
			@JsonProperty("descripcion") final String descripcion, @JsonProperty("precio") final double precio,
			@JsonProperty("puntuacion") final double puntuacion, @JsonProperty("activo") final boolean activo,
			@JsonProperty("imagenes") final String imagenes) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.puntuacion = puntuacion;
		this.activo = activo;
		this.imagenes = imagenes;
	}

	@JsonCreator
	public Objeto() {
	}

	// ENCAPSULACION
	@JsonProperty("id")
	public int getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("nombre")
	public String getNombre() {
		return nombre;
	}

	@JsonProperty("descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	@JsonProperty("precio")
	public double getPrecio() {
		return precio;
	}

	@JsonProperty("puntuacion")
	public double getPuntuacion() {
		return puntuacion;
	}

	@JsonProperty("activo")
	public boolean isActivo() {
		return activo;
	}

	@JsonProperty("imagenes")
	public String getImagenes() {
		return imagenes;
	}

}
