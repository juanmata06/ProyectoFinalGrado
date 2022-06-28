import { Component, OnInit } from "@angular/core";
import { LoginServices } from "../services/login.service";
import { UsuarioService } from "../services/usuario.service";
import { credencialesUsuario } from "../clases/credencialesLogin";
import { TokenService } from "../services/token.service";

@Component({
    selector: 'login-comp',
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.css'],
    providers: [LoginServices, UsuarioService, TokenService]
})

export class loginComponent implements OnInit {
    email = '';
    password = '';
    email2 = '';
    password2 = '';
    credenciales: credencialesUsuario = new credencialesUsuario();

    constructor(
        private loginService: LoginServices,
        private usuarioService: UsuarioService,
        private tokenService: TokenService
    ) { }


    loginUser() {
        this.loginService.loguearUsuario(this.email, this.password)
            .subscribe(
                (result) => {
                    console.log(result.login);
                    if (result.login == true) {
                        this.loginService.postDadesAjax(this.email, this.password)
                            .subscribe(
                                (result) => {
                                    console.log(result);
                                    localStorage.setItem("token", result.token);
                                    document.location.href = 'http://localhost:4200/';

                                }, (error) => { console.log("error: ", error) }

                            );
                    } else {
                        alert("Contraseña incorrecta");
                    }
                }, (error) => { console.log("error: ", error) }

            );
    }

    loginJava() {
        this.credenciales = new credencialesUsuario(this.email, this.password);
        this.usuarioService.getUsarioIdByLogin(this.credenciales)
            .subscribe((id) => {
                if (id != -1) {
                    this.tokenService.crearTokenByUserId(id).subscribe((token) => {
                        localStorage.setItem("token", token.valor);
                        document.location.href = 'http://localhost:4200/';
                    }, (err) => {
                        console.log("error: ", err);
                    });
                } else {
                    alert("No tienes cuenta")
                }
            }, (error) => {
                console.log("error: ", error);
            });
    }

    /*
    registerUser() {
        this.loginService.registrarUsuario(this.email2, this.password2)
            .subscribe(
                (result) => {
                    console.log(result);
                    this.loginService.postDadesAjax(this.email2, this.password2)
                        .subscribe(
                            (result) => {
                                console.log(result);
                                localStorage.setItem("token", result.token);
                                document.location.href = 'http://localhost:4200/';

                            }, (error) => { console.log("error: ", error) }

                        );

                }, (error) => { console.log("error: ", error) }

            );
    }
    */

    logout() {
        localStorage.removeItem("token");
        window.location.reload();
    }

    ngOnInit(): void {

    }

}
