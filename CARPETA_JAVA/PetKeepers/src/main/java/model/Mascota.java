package model;

//CLASES
import java.util.ArrayList;
//DEPENDENCIAS
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
//OBJETOS

public class Mascota extends Objeto {
	//ATRIBUTOS
	@JsonProperty("usuario")
	private int usuario;
	
	@JsonProperty("tipo")
	private String tipo;
	
	@JsonProperty("nombre_mascota")
	private String nombre_mascota;

	//CONSTRUCTORES
	@JsonCreator
	public Mascota(
			@JsonProperty("id") final int id,
			@JsonProperty("nombre") final String nombre,
			@JsonProperty("tipo") final String tipo,
			@JsonProperty("descripcion") final String descripcion,
			@JsonProperty("precio") final double precio,
			@JsonProperty("puntuacion") final double puntuacion,
			@JsonProperty("activo") final boolean activo,
			@JsonProperty("imagenes") final String imagenes,
			@JsonProperty("usuario") final int usuario,
			@JsonProperty("nombre_mascota") final String nombre_mascota) {
		super(id, nombre, descripcion, precio, puntuacion, activo, imagenes);
		this.tipo = tipo;
		this.usuario = usuario;
		this.nombre_mascota = nombre_mascota;
	}
	
	@JsonCreator
	public Mascota() {
		super();
	}

	//ENCAPSULACION
	@JsonProperty("usuario")
	public int getUsuario() {
		return usuario;
	}

	
	@JsonProperty("usuario")
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	@JsonProperty("tipo")
	public String getTipo() {
		return tipo;
	}

	@JsonProperty("tipo")
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@JsonProperty("nombre_mascota")
	public String getNombre_mascota() {
		return nombre_mascota;
	}

	@JsonProperty("nombre_mascota")
	public void setNombre_mascota(String nombre_mascota) {
		this.nombre_mascota = nombre_mascota;
	}
}
