import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


@Injectable()
export class PoveedorService {
    constructor(private conexHttp: HttpClient) { }

    getProveedorById(id: number): Observable<any> {
        return this.conexHttp.get(
            "api/proveedor/" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }
}