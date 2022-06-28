import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Mascota } from "../clases/mascota";

@Injectable()
export class mascotaService {
    mascotas: Array<Mascota> = [];
    constructor(private conexHttp: HttpClient) { }

    getMascotaById(id: Number): Observable<any> {
        return this.conexHttp.get(
            "/api/mascota/" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getAllMascotas(): Observable<any> {
        return this.conexHttp.get(
            "/api/mascota",
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getTop5Mascotas(): Observable<any> {
        return this.conexHttp.get(
            "/api/mascota/top5",
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    postNuevaMascota(mascota: Mascota): Observable<any> {
        return this.conexHttp.post(
            "/api/mascota", mascota,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getMascotasByTipo(tipo: String): Observable<any> {
        return this.conexHttp.get(
            "api/mascota/tipo=" + tipo,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getMascotasByUsuarioId(id: Number): Observable<any> {
        return this.conexHttp.get(
            "/api/mascota/cliente=" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }
}