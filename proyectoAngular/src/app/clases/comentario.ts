export class Comentario {
    id: number = -1;
    comentario: String = "";
    fecha: String = "";

    constructor(
        id: number = -1, 
        comentario: String = "",
        fecha: String = ""
    ) {
        this.id = id;
        this.comentario = comentario;
        this.fecha = fecha;
    }
}