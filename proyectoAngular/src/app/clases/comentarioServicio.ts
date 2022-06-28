import { Comentario } from "./comentario";

export class ComentarioServicio extends Comentario {
    id_servicio: number = -1;
    id_usuario: number = -1;
    nombre_usuario: String = "";

    constructor
        (
            id: number = -1, comentario: String = "", fecha: String = "",
            id_servicio: number = -1, id_usuario: number = -1, nombre_usuario: String = ""
            ) {
        super
            (
                id, comentario, fecha,
            );
        this.id_servicio = id_servicio;
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;

    }
}