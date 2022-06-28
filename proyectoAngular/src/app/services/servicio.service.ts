import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
//import { Objeto } from "../clases/objeto";
import { Servicio } from "../clases/servicio";
//import { Suscriptor } from "../clases/suscriptor";

@Injectable()
export class ServicioService {
    constructor(private conexHttp: HttpClient) { }

    servicio = [new Servicio];
    getServicios(): Array<Servicio> {
        return this.servicio;
    }

    getTipoServicio(tipo: String): Servicio {
        var ser = new Servicio;
        for (var i = 0; i < this.servicio.length; i++) {
            if (this.servicio[i].nombre == tipo) {
                ser = this.servicio[i];
            }
        }
        return ser;
    }

    getServicioAjax(): Observable<any> {
        return this.conexHttp.get(
            "/Proyecto/CARPETA_PHP/servicioTipoGET.php",
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getAllServicios(): Observable<any> {
        return this.conexHttp.get(
            "/api/servicio/",
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getTop5Servicios(): Observable<any> {
        return this.conexHttp.get(
            "/api/servicio/top5",
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getServiciosByName(nombre: String): Observable<any> {
        return this.conexHttp.get(
            "api/servicio/nombre=" + nombre,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getServicioById(id: Number): Observable<any> {
        return this.conexHttp.get(
            "/api/servicio/" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getServiciosBySuscriptorId(id: Number): Observable<any> {
        return this.conexHttp.get(
            "/api/servicio/suscriptor=" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    postNuevoServicio(servicio: Servicio): Observable<any> {
        return this.conexHttp.post(
            "/api/servicio", servicio,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }


}