import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class LoginServices {
    constructor(private conexHttp: HttpClient) { }

    postDadesAjax(email: any, password: any): Observable<any> {
        return this.conexHttp.post(
            "/Proyecto/CARPETA_PHP/myAPI.php",
            { email, password }, this.generarHeaders()
        );

    }

    registrarUsuario(email: any, password: any): Observable<any> {
        return this.conexHttp.post(
            "/Proyecto/CARPETA_PHP/register.php",
            { email, password }, this.generarHeaders()
        )
    }
    loguearUsuario(email: any, password: any): Observable<any> {
        return this.conexHttp.post(
            "http://localhost:4200/Proyecto/CARPETA_PHP/login.php",
            { email, password }, this.generarHeaders()
        )
    }

    generarHeaders() {
        if (localStorage.getItem("token") && localStorage.getItem("token") != "undefined") {
            return { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': '' + localStorage.getItem("token") }) };
        } else {
            return { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };
        }
    }
}