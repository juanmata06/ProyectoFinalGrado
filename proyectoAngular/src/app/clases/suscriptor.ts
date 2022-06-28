import { Usuario } from "./usuario";
import { Pago } from "./pago";
import { Suscripcion } from "./suscripcion";
import { Mascota } from "./mascota";
import { Producto } from "./producto";

export class Suscriptor extends Usuario {
    id_cliente: number = -1;
    pago: Pago = new Pago();
    suscripcion: Suscripcion = new Suscripcion();
    servicios: Array<number> = [];

    constructor
        (
            id: number = -1, nombre: String = "",
            primer_apellido: String = "", segundo_apellido: String = "",
            email: String = "", password: String = "",
            dni: String = "", nacimiento: String = "",
            telefono: String = "", ciudad: String = "",
            direccion: String = "", foto: String = "",
            mascotas: Array<Mascota> = [], id_cliente: number = -1,
            tipo_usuario: number = -1, pago: Pago = new Pago(),
            suscripcion: Suscripcion = new Suscripcion(),
            servicios: Array<number> = [],
    ) {
        super
            (
                id, nombre, primer_apellido, segundo_apellido,
                email, password, dni, nacimiento, 
                telefono, ciudad, direccion, foto, tipo_usuario, mascotas
            );
        this.id_cliente = id_cliente;
        this.pago = pago;
        this.suscripcion = suscripcion;
        this.servicios = servicios;
    }
}