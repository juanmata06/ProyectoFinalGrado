import { OnInit } from "@angular/core";
import { Component } from '@angular/core';
import { Mascota } from "../clases/mascota";
import { Producto } from "../clases/producto";
import { Servicio } from "../clases/servicio";
import { mascotaService } from "../services/mascota.service";
import { ProductoService } from "../services/producto.service";
import { ServicioService } from "../services/servicio.service";
import { TokenService } from "../services/token.service";
import { UsuarioService } from '../services/usuario.service';
import { Usuario } from '../clases/usuario';
import { Token } from '../clases/token';
@Component({
  selector: 'main-comp',
  templateUrl: 'main.component.html',
  styleUrls: ['main.component.css'],
  providers: [
    TokenService, ProductoService, ServicioService,
    mascotaService, UsuarioService
  ]
})



export class mainComponent implements OnInit {
  constructor(
    private _tokenService: TokenService,
    private _productoService: ProductoService,
    private _servicioService: ServicioService,
    private _mascotaService: mascotaService,
    private _usuarioService: UsuarioService
  ) {

  }

  productos: Array<Producto> = [];
  servicios: Array<Servicio> = [];
  mascotas: Array<Mascota> = [];
  mybutton: any;
  token = false;
  usuario: Usuario = new Usuario;

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
            .subscribe(
              (result) => {
                this.token = true;
                //Guardamos el usuario en una variable
                this._usuarioService.getUsuarioById(result)
                  .subscribe((usuario) => {
                    this.usuario = usuario;
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

    this._productoService.getTop5Productos()
      .subscribe(async (resultado) => {
        this.productos = resultado;
      }, (error) => {
        console.log("Error: " + error);
      })

    this._servicioService.getTop5Servicios()
      .subscribe(async (resultado) => {
        this.servicios = resultado;
      }, (error) => {
        console.log("Error: " + error);
      })

    this._mascotaService.getTop5Mascotas()
      .subscribe(async (resultado) => {
        this.mascotas = resultado;
      }, (error) => {
        console.log("Error: " + error);
      })

    this.mybutton = document.getElementById("btn-back-to-top");
    this.mybutton.addEventListener("click", backToTop);
    window.onscroll = function () {
      scrollFunction();
    };
    function scrollFunction(this: any) {
      if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        this.mybutton.style.display = "block";
      } else {
        this.mybutton.style.display = "none";
      }
    }
    function backToTop() {
      document.body.scrollTop = 0;
      document.documentElement.scrollTop = 0;
    }
  }

}