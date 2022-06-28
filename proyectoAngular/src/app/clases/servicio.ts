import { Objeto } from "./objeto";

export class Servicio extends Objeto {
    suscriptor: number = -1;

    constructor
        (
            id: number = -1, nombre: String = "",
            descripcion: String = "", precio: number = -1,
            puntuacion: number = -1, activo: boolean = true,
            imagenes: String = "", suscriptor: number = -1
        ) {
        super
            (
                id, nombre, descripcion, precio,
                puntuacion, activo, imagenes
            );
        this.suscriptor = suscriptor;
    }
}