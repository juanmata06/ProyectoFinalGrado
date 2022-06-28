import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { SuscriptorService } from "../services/suscriptor.service";
import { UsuarioService } from "../services/usuario.service";
import { TokenService } from '../services/token.service';
import { Token } from '../clases/token';
import { Usuario } from '../clases/usuario';
import { Suscriptor } from "../clases/suscriptor";
import { SuscripcionService } from "../services/suscripcion.service";
import { PagoService } from "../services/pago.service";

@Component({
  selector: 'crearsubscription-comp',
  templateUrl: 'crearsubscription.component.html',
  styleUrls: ['crearsubscription.component.css'],
  providers: [
    UsuarioService,
    SuscriptorService,
    TokenService,
    SuscripcionService,
    PagoService
  ]
})

export class crearsubscriptionComponent implements OnInit {
  constructor(
    private _activRoute: ActivatedRoute,
    private _suscriptorService: SuscriptorService,
    private _pagoService: PagoService,
    private _tokenService: TokenService,
    private _usuarioService: UsuarioService,
    private _suscripcionService: SuscripcionService
  ) {

  }
  urlVal = "";
  idSuscripcion = 0;
  type = 0;
  usuario: Usuario = new Usuario();

  ngOnInit(): void {
    this._activRoute.paramMap.subscribe(
      (params) => {
        this.urlVal = params.get("id") + "";
        this.idSuscripcion = Number(this.urlVal);
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
                  console.log(this.usuario);
                }, (er) => { console.log("error: ", er) });
            }, (err) => { console.log("error: ", err) });
        }, (error) => { console.log("error: ", error) });
    } else {
      document.location.href = 'http://localhost:4200/**';
    }
  }


  pagar(payment: number) {
    this._suscripcionService.getSuscripcion(this.idSuscripcion)
      .subscribe((suscripcion) => {
        this._pagoService.getPago(payment)
          .subscribe((pago) => {
            //Creamos el Suscriptor
            let newSuscriptor = new Suscriptor(
              -1, this.usuario.nombre,
              this.usuario.primer_apellido, this.usuario.segundo_apellido,
              this.usuario.email, this.usuario.password,
              this.usuario.dni, this.usuario.nacimiento,
              this.usuario.telefono, this.usuario.ciudad,
              this.usuario.direccion, this.usuario.foto,
              this.usuario.mascotas, this.usuario.id,
              2, pago,
              suscripcion, []
            );

            this._suscriptorService.postSuscriptor(newSuscriptor)
              .subscribe((resul) => {
                console.log(resul);
              }, (er) => {
                //Actualizamos el usuario
                let updatedUsuario = new Usuario(
                  this.usuario.id, newSuscriptor.nombre,
                  newSuscriptor.primer_apellido, newSuscriptor.segundo_apellido,
                  newSuscriptor.email, newSuscriptor.password,
                  newSuscriptor.dni, newSuscriptor.nacimiento,
                  newSuscriptor.telefono, newSuscriptor.ciudad,
                  newSuscriptor.direccion, newSuscriptor.foto,
                  2, this.usuario.mascotas
                );

                this._usuarioService.actualizarUsuario(this.usuario.id, updatedUsuario)
                  .subscribe((resul) => {
                    console.log(resul);
                  }, (er) => { 
                    document.location.href = 'http://localhost:4200/perfil';
                   });
              });
          }, (er) => { console.log("error: ", er) });
      }, (err) => { console.log("error: ", err) });
  }

  logout() {
    localStorage.removeItem("token");
    document.location.href = 'http://localhost:4200/';
  }
}