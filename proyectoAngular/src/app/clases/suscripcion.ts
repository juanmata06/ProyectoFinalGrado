export class Suscripcion {
    id: number = -1;
    tiempo: String = "";
    precio: String = "";
    descripcion: String = "";

    constructor(
        id: number = -1, tiempo: String = "",
        descripcion: String = "", precio: String = ""
    ) {
        this.id = id;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
        this.precio = precio;
    }
}