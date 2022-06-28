import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { mascotaService } from "../services/mascota.service";
import { Mascota } from "../clases/mascota";
import { TokenService } from '../services/token.service';
import { Token } from '../clases/token';
import { Usuario } from "../clases/usuario";
import { Suscriptor } from "../clases/suscriptor";
import { SuscriptorService } from '../services/suscriptor.service';
import { ComentarioMascota } from "../clases/comentarioMascota";
import { ComentarioService } from "../services/comentario.service";
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'crearservicio-comp',
  templateUrl: 'ComentarMascota.component.html',
  styleUrls: ['crearservicio.component.css'],
  providers: [
    mascotaService,
    TokenService,
    SuscriptorService,
    ComentarioService
  ]
})

export class ComentarMascota implements OnInit {
  urlVal = "";
  comentario = "";
  mascota = new Mascota();
  suscriptor: Suscriptor = new Suscriptor();
  id = 0;

  constructor(
    private _mascotaService: mascotaService,
    private _activRoute: ActivatedRoute,
    private _tokenService: TokenService,
    private _suscriptorService: SuscriptorService,
    private _comentarioService: ComentarioService,
    private _usuarioService: UsuarioService,

  ) { }

  ngOnInit(): void {
    this._activRoute.paramMap.subscribe(
      (params) => {
        this.urlVal = params.get("id") + "";
        this.id = Number(this.urlVal);
        this._mascotaService.getMascotaById(this.id)
          .subscribe((result) => {
            this.mascota = result;
            console.log(this.mascota);
          }, (e) => { console.log("error: ", e) });
      }
    );
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
              console.log(result);
              this._usuarioService.getUsuarioById(result)
                .subscribe((usuario) => {
                  if (usuario.tipo_usuario != 1) {
                    //Guardamos el suscriptor en una variable
                    this._suscriptorService.getSuscriptorByIdUsuario(result)
                      .subscribe((suscriptor) => {
                        this.suscriptor = suscriptor;
                      }, (er) => { console.log("error: ", er) });
                  } else {
                    document.location.href = '**';
                  }
                }, (er) => { console.log("error: ", er) });
            }, (err) => { console.log("error: ", err) });
        }, (error) => { console.log("error: ", error) });
    } else {
      document.location.href = '**';
    }
  }


  crearComentario() {
    let fecha = new Date();
    let newComentario = new ComentarioMascota(
      -1, this.comentario,
      fecha.getFullYear() + "-" + fecha.getMonth() + "-" + fecha.getDate() + " " +
      fecha.getHours() + ":" + fecha.getMinutes() + ":" + fecha.getSeconds() + " ",
      this.mascota.id, this.suscriptor.id, this.suscriptor.nombre
    );


    this._comentarioService.postMascotaComentario(newComentario).subscribe((result) => {
      console.log(result);
    });
    document.location.href = 'http://localhost:4200/vistamascota/' + this.mascota.id;

  }
}
