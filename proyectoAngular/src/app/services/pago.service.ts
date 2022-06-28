import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ComentarioServicio } from "../clases/comentarioServicio";
import { ComentarioMascota } from "../clases/comentarioMascota";

@Injectable()
export class PagoService {
    constructor(private conexHttp: HttpClient) { }

    getPago(id: number): Observable<any> {
        return this.conexHttp.get(
            "/api/pago/" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }
}