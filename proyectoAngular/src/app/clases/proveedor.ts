export class Proveedor {
    id: number = -1;
    nombre: String = "";
    cif: String = "";
    email: String = "";
    telefono: String = "";
    ciudad: String = "";
    direccion: String = "";

    constructor(
        id: number = -1, nombre: String = "",
        cif: String = "", email: String = "",
        telefono: String = "", pais: String = "",
        ciudad: String = "", direccion: String = ""
    ) {
        this.id = id;
        this.nombre = nombre;
        this.cif = cif;
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }
}