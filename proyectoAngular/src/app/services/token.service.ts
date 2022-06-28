import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Token } from "../clases/token";

@Injectable()
export class TokenService{

    constructor(private conexHttp:HttpClient){}

    getToken():Observable<any>{
        return this.conexHttp.get(
            "/Proyecto/CARPETA_PHP/repPeticiones.php",
            {headers:new HttpHeaders(
                {'Content-Type':'application/json','Authorization': ''+localStorage.getItem("token")})
           }
        );
    }

    getUsrToken():Observable<any>{
        return this.conexHttp.get(
            "/Proyecto/CARPETA_PHP/repUserToken.php",
            {headers:new HttpHeaders(
                {'Content-Type':'application/json','Authorization': ''+localStorage.getItem("token")})
           }
        );
    }

    //JAVA:
    crearTokenByUserId(id: number):Observable<any>{
        return this.conexHttp.get(
            "api/token/usuario=" + id,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    verificarToken(token: Token):Observable<any>{
        return this.conexHttp.post(
            "api/token/", token,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }

    obtenerUsuarioByToken(token: Token):Observable<any>{
        return this.conexHttp.post(
            "api/token/usuario", token,
            {
                headers: new HttpHeaders(
                    { 'Content-Type': 'application/json' })
            }
        );
    }
}