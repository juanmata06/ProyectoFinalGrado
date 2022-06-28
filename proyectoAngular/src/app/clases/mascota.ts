import { Objeto } from "./objeto";

export class Mascota extends Objeto {
    tipo: String = "";
    usuario: number = -1;
    nombre_mascota: String = "";

    constructor
        (
            id: number = -1, nombre: String = "", tipo: String = "",
            descripcion: String = "", precio: number = -1,
            puntuacion: number = -1, activo: boolean = true,
            imagenes: String = "", usuario: number = -1,
            nombre_mascota: String = ""
        ) {
        super
            (
                id, nombre, descripcion, precio,
                puntuacion, activo, imagenes
            );
        this.tipo = tipo;
        this.usuario = usuario;
        this.nombre_mascota = nombre_mascota;
    }
}