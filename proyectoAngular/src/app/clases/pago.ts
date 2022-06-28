export class Pago {
    id: number = -1;
    tipo: String = "";

    constructor
        (
            id: number = -1, tipo: String = ""
        ) {
        this.id = id;
        this.tipo = tipo;
    }
}