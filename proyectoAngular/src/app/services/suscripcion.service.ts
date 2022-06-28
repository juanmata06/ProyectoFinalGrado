import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ComentarioServicio } from "../clases/comentarioServicio";
import { ComentarioMascota } from "../clases/comentarioMascota";

@Injectable()
export class SuscripcionService {
    constructor(private conexHttp: HttpClient) { }

    getSuscripcion(id: number): Observable<any> {
        return this.conexHttp.get(
            "/api/suscripcion/" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }
}