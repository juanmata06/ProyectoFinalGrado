import { Objeto } from "./objeto";
import { Proveedor } from "./proveedor";

export class Producto extends Objeto {
    proveedor: Proveedor = new Proveedor();

    constructor
        (
            id: number = -1, nombre: String = "",
            descripcion: String = "", precio: number = -1,
            puntuacion: number = -1, activo: boolean = true,
            imagenes:String = "", proveedor: Proveedor = new Proveedor()
        ) {
        super
            (
                id, nombre, descripcion, precio,
                puntuacion, activo, imagenes
            );
        this.proveedor = proveedor;
    }
}