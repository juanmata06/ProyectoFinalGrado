import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ComentarioServicio } from "../clases/comentarioServicio";
import { ComentarioMascota } from "../clases/comentarioMascota";

@Injectable()
export class ComentarioService {
    constructor(private conexHttp: HttpClient) { }

    getMascotaComentariosByIdMascota(idMascota: number): Observable<any> {
        return this.conexHttp.get(
            "/api/mascota/comentarios/" + idMascota,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    postMascotaComentario(comentario: ComentarioMascota): Observable<any> {
        return this.conexHttp.post(
            "/api/mascota/comentarios", comentario,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getServicioComentariosByIdServicio(idServicio: number): Observable<any> {
        return this.conexHttp.get(
            "/api/servicio/comentarios/" + idServicio,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    postServicioComentario(comentario: ComentarioServicio): Observable<any> {
        return this.conexHttp.post(
            "/api/servicio/comentarios", comentario,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

}