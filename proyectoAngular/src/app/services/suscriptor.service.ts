import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Suscriptor } from "../clases/suscriptor";

@Injectable()
export class SuscriptorService {
    constructor(private conexHttp: HttpClient) { }

    getSuscriptorById(id: number): Observable<any> {
        return this.conexHttp.get(
            "/api/suscriptor/" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getSuscriptorByIdUsuario(id: number): Observable<any> {
        return this.conexHttp.get(
            "/api/suscriptor/usuario=" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    postSuscriptor(suscriptor: Suscriptor): Observable<any> {
        return this.conexHttp.post(
            "/api/suscriptor", suscriptor,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getAllSuscriptores(): Observable<any> {
        return this.conexHttp.get(
            "/Proyecto/CARPETA_PHP/getAllSuscriptores.php", 
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }
}