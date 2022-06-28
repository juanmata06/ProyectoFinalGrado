import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Mascota } from "../clases/mascota";
import { Usuario } from "../clases/usuario";
import { mascotaService } from "../services/mascota.service";
import { UsuarioService } from "../services/usuario.service";
import { ComentarioService } from "../services/comentario.service";
import { ComentarioMascota } from "../clases/comentarioMascota";
import { SuscriptorService } from "../services/suscriptor.service";

@Component({
    selector: 'vistamascota-comp',
    templateUrl: 'vistamascota.component.html',
    styleUrls: ['vistamascota.component.css'],
    providers: [
        mascotaService,
        UsuarioService,
        ComentarioService,
        SuscriptorService
    ]
})

export class vistamascotaComponent implements OnInit {

    constructor(
        private _activRoute: ActivatedRoute,
        private _mascotaService: mascotaService,
        private _usuarioService: UsuarioService,
        private _comentarioService: ComentarioService,
        private _suscriptorService: SuscriptorService,
    ) { }

    id = "";
    cnt = false;
    mascota: Mascota = new Mascota();
    usuario: Usuario = new Usuario();
    comentarios: Array<ComentarioMascota> = [];

    ngOnInit(): void {
        this._activRoute.paramMap.subscribe(
            (params) => {
                this.id = params.get("mascota") + "";
            }
        );

        this._mascotaService.getMascotaById(parseInt(this.id)).
            subscribe((resultado) => {
                console.log(resultado);
                this.mascota = resultado;
                //Usuario creador del servicio
                this._usuarioService.getUsuarioById(this.mascota.usuario).
                    subscribe((resultado) => {
                        this.usuario = resultado;
                        console.log(this.usuario);
                    }, (err) => { console.log("Error: " + err) });
                //Comentarios de la mascota
                this._comentarioService.getMascotaComentariosByIdMascota(this.mascota.id)
                    .subscribe((response) => {
                        for (let comentario of response) {
                            //Nombre del comentador
                            this._suscriptorService.getSuscriptorById(comentario.id_suscriptor)
                                .subscribe((suscriptor) => {
                                    let coment = new ComentarioMascota(
                                        comentario.id, comentario.comentario,
                                        comentario.fecha, comentario.id_mascota,
                                        comentario.id_usuario, suscriptor.nombre + " " + suscriptor.primer_apellido
                                    );
                                    this.comentarios.push(coment);
                                });
                        }
                    }, (er) => { console.log("Error: " + er) });
            }, (error) => { console.log("Error: " + error) });
    }
}

