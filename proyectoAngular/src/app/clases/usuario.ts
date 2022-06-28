import { Mascota } from "./mascota";
import { Producto } from "./producto";

export class Usuario {
    id: number = -1;
    nombre: String = "";
    primer_apellido: String = "";
    segundo_apellido: String = "";
    email: String = "";
    password: String = "";
    dni: String = "";
	nacimiento: String = "";
	telefono: String = "";
    ciudad: String = "";
	direccion: String = "";
	foto: String = "";
    tipo_usuario: number = -1;
    mascotas:Array<Mascota> = [];

    constructor(
        id: number = -1, nombre: String = "",
        primer_apellido: String = "", segundo_apellido: String = "",
        email: String = "", password: String = "",
        dni: String = "", nacimiento: String = "",
        telefono: String = "", ciudad: String = "",
        direccion: String = "", foto: String = "",
        tipo_usuario: number = -1, mascotas:Array<Mascota> = []
    ) {
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
}