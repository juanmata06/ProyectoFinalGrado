import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenService } from './services/token.service';
import { UsuarioService } from './services/usuario.service';
import { Usuario } from './clases/usuario';
import { Token } from './clases/token';
import { OnInit } from "@angular/core";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [TokenService, UsuarioService]
})
export class AppComponent implements OnInit {
    title = 'Pet Keepers';
    limit = true;
    search = '';
    id = 1;
    token = false;
    usuario: Usuario = new Usuario;

    constructor(
        private _tokenService: TokenService,
        private _usuarioService: UsuarioService) {

    }

    logout() {
        localStorage.removeItem("token");
        document.location.href = 'http://localhost:4200/';
    }

    buscador() {
        if (this.search == '') {
            this.limit = true;
        } else {
            this.limit = false;
        }

    }

    sendit() {
        if (this.search == '') {
            this.limit = true;
        } else {
            this.limit = false;
        }
        document.location.href = 'http://localhost:4200/servicio/' + this.search;
    }

    ngOnInit(): void {
        //Si existe un token:
        if (localStorage.getItem("token")) {
            //Verificamos el token:
            this._tokenService.verificarToken(new Token(localStorage.getItem("token")!))
                .subscribe((verificacion) => {
                    console.log("El token es: " + verificacion);
                    //Obtenemos el Id del usuario
                    this._tokenService.
                        obtenerUsuarioByToken(new Token(localStorage.getItem("token")!))
                        .subscribe((result) => {
                                this.token = true;
                                //Guardamos el usuario en una variable
                                this._usuarioService.getUsuarioById(result)
                                    .subscribe((usuario) => {
                                        this.usuario = usuario;
                                        console.log("Tipo:" + this.usuario.tipo_usuario);
                                        console.log(this.usuario);
                                    }, (er) => {
                                        console.log("error: ", er);
                                    });
                            }, (err) => {
                                console.log("error: ", err);
                            }
                        );
                }, (error) => {
                    console.log("error: ", error);
                });
        }
    }
}
