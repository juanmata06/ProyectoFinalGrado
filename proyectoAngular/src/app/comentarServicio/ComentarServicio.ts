import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ServicioService } from "../services/servicio.service";
import { Servicio } from "../clases/servicio";
import { TokenService } from '../services/token.service';
import { Token } from '../clases/token';
import { Usuario } from "../clases/usuario";
import { UsuarioService } from '../services/usuario.service';
import { ComentarioServicio } from "../clases/comentarioServicio";
import { ComentarioService } from "../services/comentario.service";

@Component({
  selector: 'crearservicio-comp',
  templateUrl: 'ComentarServicio.component.html',
  styleUrls: ['crearservicio.component.css'],
  providers: [
    ServicioService,
    TokenService,
    UsuarioService,
    ComentarioService
  ]
})

export class ComentarServicio implements OnInit {
  urlVal = "";
  comentario = "";
  servicio = new Servicio();
  usuario: Usuario = new Usuario();
  id = 0;

  constructor(
    private _servicio: ServicioService,
    private _activRoute: ActivatedRoute,
    private _tokenService: TokenService,
    private _usuarioService: UsuarioService,
    private _comentarioService: ComentarioService

  ) { }

  ngOnInit(): void {
    this._activRoute.paramMap.subscribe(
      (params) => {
        this.urlVal = params.get("id") + "";
        this.id = Number(this.urlVal);
        this._servicio.getServicioById(this.id)
          .subscribe((result) => {
            this.servicio = result;
            console.log(this.servicio);
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
              //Guardamos el usuario en una variable
              this._usuarioService.getUsuarioById(result)
                .subscribe((usuario) => {
                  this.usuario = usuario;
                }, (er) => { console.log("error: ", er) });
            }, (err) => { console.log("error: ", err) });
        }, (error) => { console.log("error: ", error) });
    } else {
      document.location.href = '**';
    }
  }


  crearComentario() {
    let fecha = new Date();
    let newComentario = new ComentarioServicio(
      -1, this.comentario,
      fecha.getFullYear() + "-" + fecha.getMonth() + "-" + fecha.getDate() + " " +
      fecha.getHours() + ":" + fecha.getMinutes() + ":" + fecha.getSeconds() + " ",
      this.servicio.id, this.usuario.id, this.usuario.nombre
    );
    

    this._comentarioService.postServicioComentario(newComentario).subscribe((result) => {
      console.log(result);
    });
    document.location.href = 'http://localhost:4200/vistaservicio/' + this.servicio.id;

  }
}
