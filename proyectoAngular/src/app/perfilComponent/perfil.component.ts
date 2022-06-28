import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Servicio } from "../clases/servicio";
import { Usuario } from "../clases/usuario";
import { ServicioService } from "../services/servicio.service";
import { UsuarioService } from "../services/usuario.service";
import { TokenService } from '../services/token.service';
import { Token } from '../clases/token';
import { Suscriptor } from "../clases/suscriptor";
import { SuscriptorService } from "../services/suscriptor.service";
import { mascotaService } from "../services/mascota.service";
import { Mascota } from "../clases/mascota";

@Component({
    selector: 'perfil-comp',
    templateUrl: 'perfil.component.html',
    styleUrls: ['perfil.component.css'],
    providers: [
        UsuarioService,
        ServicioService,
        TokenService,
        SuscriptorService,
        mascotaService
    ]
})


export class perfilComponent implements OnInit {
    constructor(
        private _activRoute: ActivatedRoute,
        private _usuarioService: UsuarioService,
        private _servicioService: ServicioService,
        private _tokenService: TokenService,
        private _suscriptorService: SuscriptorService,
        private _mascotaService: mascotaService
    ) { }

    urlVal = "";
    step = 0;
    service = 0;
    mascotaSelector = 0;
    usuario = new Usuario();
    servicios: Array<Servicio> = [];
    mascotas: Array<Mascota> = [];
    cantidadServicios: number = 0;
    cantidadMascotas: number = 0;
    suscriptor: Suscriptor = new Suscriptor();

    ngOnInit(): void {
        //Si existe un token:
        if (localStorage.getItem("token")) {
            //Verificamos el token:
            this._tokenService.verificarToken(new Token(localStorage.getItem("token")!))
                .subscribe((verificacion) => {
                    //Obtenemos el Id del usuario
                    this._tokenService.
                        obtenerUsuarioByToken(new Token(localStorage.getItem("token")!))
                        .subscribe((result) => {
                            //Guardamos el usuario en una variable
                            this._usuarioService.getUsuarioById(result)
                                .subscribe((usuario) => {
                                    this.usuario = usuario;
                                    console.log(this.usuario);
                                    //Guardar mascotas
                                    this._mascotaService.getMascotasByUsuarioId(this.usuario.id)
                                        .subscribe((mascotas) => {
                                            console.log(mascotas);
                                            for (let i of mascotas) {
                                                let mascota = new Mascota(
                                                    i['id'], i['nombre'], i['tipo'],
                                                    i['descripcion'], i['precio'], i['puntuacion'], i['activo'],
                                                    i['imagenes'], i['usuario'], i['nombre_mascota']
                                                );
                                                this.cantidadMascotas = ++this.cantidadMascotas;
                                                this.mascotas.push(mascota);
                                            }
                                        }, (r) => { console.log("error: ", r) });
                                    //Si el usuario es suscriptor lo guardamos en una var
                                    if (this.usuario.tipo_usuario != 1) {
                                        this._suscriptorService.getSuscriptorByIdUsuario(usuario.id)
                                            .subscribe((suscriptor) => {
                                                this.suscriptor = suscriptor;
                                                //Guardar servicios
                                                this._servicioService.
                                                    getServiciosBySuscriptorId(this.suscriptor.id)
                                                    .subscribe((servicios) => {
                                                        for (let i of servicios) {
                                                            let servicio = new Servicio(
                                                                i['id'], i['nombre'], i['descripcion'],
                                                                i['precio'], i['puntuacion'], i['activo'],
                                                                i['imagenes'], i['suscriptor']
                                                            );
                                                            this.cantidadServicios = ++this.cantidadServicios;
                                                            this.servicios.push(servicio);
                                                        }
                                                    }, (r) => { console.log("error: ", r) });
                                            }, (e) => { console.log("error: ", e) });
                                    }
                                }, (er) => { console.log("error: ", er) });
                        }, (err) => { console.log("error: ", err) });
                }, (error) => { console.log("error: ", error) });
        }
    }

    edit() {
        if (this.step == 0) {
            this.step = 1;
            this.service = 0;
            this.mascotaSelector = 0;
        } else if (this.step == 1) {
            this.step = 0;
        }
    }

    services() {
        if (this.service == 0) {
            this.service = 1;
            this.step = 0;
            this.mascotaSelector = 0;
        } else if (this.service == 1) {
            this.service = 0;
        }
    }

    editar(form: any) {
        console.log(form);
        console.log(form.controls["inputTelefono"].value);
        if (form.status != "VALID") {
            alert("El nombre, primer apellido, mail, contraseña, telefono, DNI deben tener un valor");
        } else {
            let usuarioActualizado = new Usuario(
                -1,
                form.controls["inputNombre"].value,
                form.controls["inputPrimerApellido"].value,
                form.controls["inputSegundoApellido"].value,
                form.controls["inputCorreo"].value,
                form.controls["inputPassword"].value,
                form.controls["inputDni"].value,
                form.controls["inputNacimiento"].value,
                form.controls["inputTelefono"].value,
                form.controls["inputCiudad"].value,
                form.controls["inputDireccion"].value,
                this.usuario.foto,
                this.usuario.tipo_usuario,
                this.usuario.mascotas
            );
            this._usuarioService.actualizarUsuario(this.usuario.id, usuarioActualizado)
                .subscribe((resul) => {
                    console.log(resul);
                }, (error) => { console.log("error: ", error) });
        }
    }

    mascotasS() {
        if (this.mascotaSelector == 0) {
            this.mascotaSelector = 1;
            this.step = 0;
            this.service = 0;
        } else if (this.mascotaSelector == 1) {
            this.mascotaSelector = 0;
        }
    }
}
