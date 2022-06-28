package model;

//CLASES
//DEPENDENCIAS
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
//OBJETOS

public class Proveedor {
	//ATRIBUTOS
	@JsonProperty("id")
	private int id;

	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("cif")
	private String cif;

	@JsonProperty("email")
	private String email;

	@JsonProperty("telefono")
	private String telefono;

	@JsonProperty("ciudad")
	private String ciudad;

	@JsonProperty("direccion")
	private String direccion;

	//CONSTRUCTORES
	@JsonCreator
	public Proveedor(
			@JsonProperty("id") final int id, 
			@JsonProperty("nombre") final String nombre,
			@JsonProperty("cif") final String cif, 
			@JsonProperty("email") final String email,
			@JsonProperty("telefono") final String telefono,
			@JsonProperty("ciudad") final String ciudad,
			@JsonProperty("direccion") final String direccion) {
		this.id = id;
		this.nombre = nombre;
		this.cif = cif;
		this.email = email;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.direccion = direccion;
	}
	
	@JsonCreator
	public Proveedor() {}

	//ENCAPSULACION
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

	@JsonProperty("cif")
	public String getCif() {
		return cif;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("telefono")
	public String getTelefono() {
		return telefono;
	}

	@JsonProperty("ciudad")
	public String getCiudad() {
		return ciudad;
	}

	@JsonProperty("direccion")
	public String getDireccion() {
		return direccion;
	}
	
}
