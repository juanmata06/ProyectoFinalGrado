package model;

//CLASES
import java.util.ArrayList;
//DEPENDENCIAS
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
//OBJETOS

public class Usuario {
	// ATRIBUTOS
	@JsonProperty("id")
	private int id;

	@JsonProperty("nombre")
	private String nombre;
	
	@JsonProperty("primer_apellido")
	private String primer_apellido;

	@JsonProperty("segundo_apellido")
	private String segundo_apellido;

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;
	
	@JsonProperty("dni")
	private String dni;
	
	@JsonProperty("nacimiento")
	private String nacimiento;
	
	@JsonProperty("telefono")
	private String telefono;

	@JsonProperty("ciudad")
	private String ciudad;
	
	@JsonProperty("direccion")
	private String direccion;
	
	@JsonProperty("foto")
	private String foto;
	
	@JsonProperty("tipo_usuario")
	private int tipo_usuario;
	
	@JsonProperty("mascotas")
    private ArrayList<Integer> mascotas = new ArrayList<>();

	//CONSTRUCTORES
	@JsonCreator
	public Usuario(
			@JsonProperty("id") final int id,
			@JsonProperty("nombre") final String nombre,
			@JsonProperty("primer_apellido") final String primer_apellido,
			@JsonProperty("segundo_apellido") final String segundo_apellido,
			@JsonProperty("email") final String email,
			@JsonProperty("password") final String password,
			@JsonProperty("dni") final String dni,
			@JsonProperty("nacimiento") final String nacimiento,
			@JsonProperty("telefono") final String telefono,
			@JsonProperty("ciudad") final String ciudad,
			@JsonProperty("direccion") final String direccion,
			@JsonProperty("foto") final String foto,
			@JsonProperty("tipo_usuario") final int tipo_usuario,
			@JsonProperty("mascotas") final ArrayList<Integer> mascotas) {
		this.id = id;
		this.nombre = nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.email = email;
		this.password = password;
		this.dni = dni;
		this.nacimiento = nacimiento;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.foto = foto;
		this.tipo_usuario = tipo_usuario;
		this.mascotas = mascotas;
	}
	
	@JsonCreator
	public Usuario() {}
	
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

	@JsonProperty("primer_apellido")
	public String getPrimer_apellido() {
		return primer_apellido;
	}

	@JsonProperty("segundo_apellido")
	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("dni")
	public String getDni() {
		return dni;
	}

	@JsonProperty("nacimiento")
	public String getNacimiento() {
		return nacimiento;
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

	@JsonProperty("foto")
	public String getFoto() {
		return foto;
	}

	@JsonProperty("tipo_usuario")
	public int getTipo_usuario() {
		return tipo_usuario;
	}
	
	@JsonProperty("mascotas")
	public ArrayList<Integer> getMascotas() {
		return mascotas;
	}
}
