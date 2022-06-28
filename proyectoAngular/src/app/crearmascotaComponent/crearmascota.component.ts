import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { LoginServices } from "../services/login.service";
import { mascotaService } from "../services/mascota.service";
import { Mascota } from "../clases/mascota";
import { Servicio } from "../clases/servicio";
import { TokenService } from '../services/token.service';
import { UsuarioService } from '../services/usuario.service';
import { Usuario } from '../clases/usuario';
import { Token } from '../clases/token';

@Component({
    selector: 'crearmascota-comp',
    templateUrl: 'crearmascota.component.html',
    styleUrls: ['crearmascota.component.css'],
    providers: [
        mascotaService, TokenService, UsuarioService
    ]
})

export class crearmascotaComponent implements OnInit {
    nombreMascota = "";
    nombre = "";
    descripcion = "";
    precio = 0;
    foto = "";
    filesToUpload: any;
    imagenes: String = "";
    mascota = new Mascota();
    servicio = new Servicio();
    usuario: Usuario = new Usuario();
    tipos = ["Perro", "Gato", "Pez", "Ave"];
    tipoMascota = this.tipos[0];

    constructor(
        private _mascota: mascotaService,
        private _activRoute: ActivatedRoute,
        private _tokenService: TokenService,
        private _usuarioService: UsuarioService
    ) {

    }

    handleFileInput(event: Event) {
        const el = event.currentTarget as HTMLInputElement;
        let FileList: FileList | null = el.files;
        this.filesToUpload = FileList;
        if(this.filesToUpload){
            var form = new FormData();
        form.append("file",this.filesToUpload[0]);
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("POST", 'http://localhost/Proyecto/CARPETA_PHP/imagenMascota.php', true);
        xmlHttp.send(form);
        var self  = this;
        xmlHttp.onreadystatechange = function x() {
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                    self.data = JSON.parse(xmlHttp.responseText);
                }
            }
        }
        }
    }
    data:any;


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
                            //Guardamos el usuario en una variable
                            this._usuarioService.getUsuarioById(result)
                                .subscribe((usuario) => {
                                    this.usuario = usuario;
                                    console.log(this.usuario);
                                }, (er) => { console.log("error: ", er) });
                        }, (err) => { console.log("error: ", err) });
                }, (error) => { console.log("error: ", error) });
        }
    }

    crearMascota() {
        var foto = document.getElementById("imageUrl")?.textContent;
        var finalFoto = foto?.slice(1,-1);
        this.mascota = new Mascota(
            -1, this.nombre, this.tipoMascota, this.descripcion, this.precio,
            0, true, finalFoto, this.usuario.id,
            this.nombreMascota
        );

        this._mascota.postNuevaMascota(this.mascota).subscribe((result) => {
            console.log(result);
        });
        document.location.href = 'http://localhost:4200/perfil';
    }
}