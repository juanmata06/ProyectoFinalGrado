package model;

//CLASES
import java.util.ArrayList;
//DEPENDENCIAS
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
//OBJETOS

public class Servicio extends Objeto {
	// ATRIBUTOS
	@JsonProperty("suscriptor")
	private int suscriptor;

	// CONSTRUCTORES
	@JsonCreator
	public Servicio(
			@JsonProperty("id") final int id,
			@JsonProperty("nombre") final String nombre,
			@JsonProperty("descripcion") final String descripcion,
			@JsonProperty("precio") final double precio,
			@JsonProperty("puntuacion") final double puntuacion,
			@JsonProperty("activo") final boolean activo,
			@JsonProperty("imagenes") final String imagenes,
			@JsonProperty("suscriptor") final int suscriptor) {
		super(id, nombre, descripcion, precio, puntuacion, activo, imagenes);
		this.suscriptor = suscriptor;
	}
	
	@JsonCreator
	public Servicio() {	
	}

	//ENCAPSULACION
	@JsonProperty("suscriptor")
	public int getSuscriptor() {
		return suscriptor;
	}
	
}
