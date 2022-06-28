import { Component, OnInit } from "@angular/core";
import { Usuario } from "../clases/usuario";
import { Mascota } from "../clases/mascota";
import { RegistrarServices } from "../services/registrar.service";
import { countries } from "./countries/country-data-store";
import { ActivatedRoute, Router } from '@angular/router';
import { TokenService } from "../services/token.service";
import { credencialesUsuario } from "../clases/credencialesLogin";
import { UsuarioService } from "../services/usuario.service";


@Component({
    selector: 'crearcuenta-comp',
    templateUrl: 'crearcuenta.component.html',
    styleUrls: ['crearcuenta.component.css'],
    providers: [RegistrarServices, TokenService, UsuarioService]
})

export class crearcuentaComponent implements OnInit {
    public countries: any = countries;
    constructor(
        private _registrar: RegistrarServices,
        protected router: Router,
        protected route: ActivatedRoute,
        private tokenService: TokenService,
        private usuarioService: UsuarioService,

    ) {

    }
    ngOnInit(): void {
    }
    usuario = new Usuario();
    step = 0;
    cuenta: any[] = [];
    nombre = "";
    primerApellido = "";
    segundoApellido = "";
    email = "";
    password = "";
    dni = "";
    nacimiento = "";
    telefono = 0;
    ciudad = "";
    direccion = "";
    mascotas: Array<Mascota> = [];
    filesToUpload: any;
    foto = "";
    credenciales: credencialesUsuario = new credencialesUsuario();


    handleFileInput(event: Event) {
        const el = event.currentTarget as HTMLInputElement;
        let FileList: FileList | null = el.files;
        this.filesToUpload = FileList;
        if (this.filesToUpload) {
            var form = new FormData();
            form.append("file", this.filesToUpload[0]);
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("POST", 'http://localhost/Proyecto/CARPETA_PHP/imagenCuenta.php', true);
            xmlHttp.send(form);
            var self = this;
            xmlHttp.onreadystatechange = function x() {
                if (xmlHttp.readyState == 4) {
                    if (xmlHttp.status == 200) {
                        self.data = JSON.parse(xmlHttp.responseText);
                    }
                }
            }
        }
    }
    data: any;

    crearCuenta() {
        if (
            this.nombre == "" || this.email == "" ||
            this.password == "" || this.nacimiento == "" ||
            this.telefono == 0
        ) {
            alert(
            `Los campos Nombre, Correo electronico, Contraseña, 
            Fecha de nacimiento y Telefono no pueden estar vacios`
            );
        } else {
            var foto = document.getElementById("imageUrl")?.textContent;
            var finalFoto = foto?.slice(1, -1);
            this.usuario = new Usuario(
                -1, this.nombre,
                this.primerApellido, this.segundoApellido,
                this.email, this.password,
                this.dni, this.nacimiento,
                String(this.telefono), this.ciudad,
                this.direccion, finalFoto,
                1, this.mascotas
            );

            this._registrar.postUsuario(this.usuario).subscribe((result) => {
                console.log(result);
            }, (error) => { console.log("error: ", error) });
            document.location.href = 'http://localhost:4200/login/';
        }
    }

    nextStep() {
        if (this.step == 1) {
            this.step = 2;
        } else if (this.step == 0) {
            this.step = 1;
        }
    }

    backStep() {
        if (this.step == 1) {
            this.step = 0;
        } else if (this.step == 2) {
            this.step = 1;
        }
    }
}