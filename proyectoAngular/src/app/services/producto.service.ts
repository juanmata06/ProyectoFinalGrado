import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


@Injectable()
export class ProductoService {
    constructor(private conexHttp: HttpClient) { }


    getAllProductos(): Observable<any> {
        return this.conexHttp.get(
            "/api/producto",
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getTop5Productos(): Observable<any> {
        return this.conexHttp.get(
            "/api/producto/top5",
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getProductosByName(nombre: String): Observable<any> {
        return this.conexHttp.get(
            "api/producto/nombre=" + nombre,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    getProductoById(id: number): Observable<any> {
        return this.conexHttp.get(
            "api/producto/" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }
}