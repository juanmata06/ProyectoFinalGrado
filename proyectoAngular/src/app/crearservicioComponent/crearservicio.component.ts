import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ServicioService } from "../services/servicio.service";
import { Servicio } from "../clases/servicio";
import { TokenService } from '../services/token.service';
import { Token } from '../clases/token';
import { SuscriptorService } from "../services/suscriptor.service";
import { Suscriptor } from "../clases/suscriptor";

@Component({
  selector: 'crearservicio-comp',
  templateUrl: 'crearservicio.component.html',
  styleUrls: ['crearservicio.component.css'],
  providers: [
    ServicioService,
    TokenService,
    SuscriptorService
  ]
})

export class crearservicioComponent implements OnInit {
  nombre = "";
  descripcion = "";
  precio = 0;
  filesToUpload: any;
  imagenes: String = "";
  servicio = new Servicio();
  suscriptor: Suscriptor = new Suscriptor();
  id = 0;

  constructor(
    private _servicio: ServicioService,
    private _activRoute: ActivatedRoute,
    private _tokenService: TokenService,
    private _suscriptorService: SuscriptorService
  ) {}
    foto="";
  handleFileInput(event: Event) {
    const el = event.currentTarget as HTMLInputElement;
    let FileList: FileList | null = el.files;
    this.filesToUpload = FileList;
    if(this.filesToUpload){
        var form = new FormData();
    form.append("file",this.filesToUpload[0]);
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", 'http://localhost/Proyecto/CARPETA_PHP/imagenServicio.php', true);
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
              console.log(result);
              //Guardamos el suscriptor en una variable
              this._suscriptorService.getSuscriptorByIdUsuario(result)
                .subscribe((suscriptor) => {
                  this.suscriptor = suscriptor;
                }, (e) => { console.log("error: ", e) });
            }, (err) => { console.log("error: ", err) });
        }, (error) => { console.log("error: ", error) });
    }
  }


  crearServicio() {
    var foto = document.getElementById("imageUrl")?.textContent;
    var finalFoto = foto?.slice(1,-1);
    console.log(finalFoto);
    this.servicio = new Servicio(
      -1, this.nombre, this.descripcion, this.precio,
      0, true, finalFoto, this.suscriptor.id
    );

    this._servicio.postNuevoServicio(this.servicio).subscribe((result) => {
      console.log(result);
    });
    document.location.href = 'http://localhost:4200/perfil';
  }

}
