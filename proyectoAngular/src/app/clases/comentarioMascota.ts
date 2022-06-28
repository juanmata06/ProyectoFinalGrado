import { Comentario } from "./comentario";

export class ComentarioMascota extends Comentario {
    id_mascota: number = -1;
    id_suscriptor: number = -1;
    nombre_suscriptor: String = "";

    constructor
        (
            id: number = -1, comentario: String = "", fecha: String = "",
            id_mascota: number = -1, id_suscriptor: number = -1, nombre_suscriptor: String = ""
            ) {
        super
            (
                id, comentario, fecha,
            );
        this.id_mascota = id_mascota;
        this.id_suscriptor = id_suscriptor;
        this.nombre_suscriptor = nombre_suscriptor;
    }
}