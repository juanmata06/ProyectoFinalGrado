import { HttpParams } from "@angular/common/http";
import { OnInit } from "@angular/core";
import { Component } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Mascota } from "../clases/mascota";
import { mascotaService } from "../services/mascota.service";


@Component({
  selector: 'app-listado-mascotas',
  templateUrl: './listado-mascotas.component.html',
  styleUrls: ['./listado-mascotas.component.css'],
  providers: [mascotaService]
})

export class ListadoMascotasComponent implements OnInit {
  constructor(
    private _activRoute: ActivatedRoute,
    private _mascotaService: mascotaService
  ) { }

  mascotas: Array<Mascota> = [];
  tipoPedido = "";
  cantidadMascotas: number = 0;
  limit = true;
  search = '';

  sendit() {
    if (this.search == '') {
      this.limit = true;
    } else {
      this.limit = false;
    }
    document.location.href = 'http://localhost:4200/mascotas/' + this.search;
  }

  ngOnInit(): void {
    this._activRoute.paramMap.subscribe((params) => {
      this.tipoPedido = params.get("tipo") + "";
    });
    if (this.tipoPedido != "null") { //Buscar por nombre:
      this._mascotaService.getMascotasByTipo(this.tipoPedido)
        .subscribe((resultado) => {
          console.log(resultado)
          for (let i of resultado) {
            let mascota = new Mascota(
              i['id'], i['nombre'], i['tipo'],
              i['descripcion'], i['precio'], i['puntuacion'], i['activo'],
              i['imagenes'], i['usuario'], i['nombre_mascota']
            );
            this.cantidadMascotas = ++this.cantidadMascotas;
            this.mascotas.push(mascota);
          }
          console.log(this.mascotas);
        }, (error) => {
          console.log(error);
        });
    } else { //Buscar todos:
      this._mascotaService.getAllMascotas()
        .subscribe((resultado) => {
          for (let i of resultado) {
            let mascota = new Mascota(
              i['id'], i['nombre'], i['tipo'],
              i['descripcion'], i['precio'], i['puntuacion'], i['activo'],
              i['imagenes'], i['usuario'], i['nombre_mascota']
            );
            this.cantidadMascotas = ++this.cantidadMascotas;
            this.mascotas.push(mascota);
          }
          console.log(this.mascotas);
        }, (error) => {
          console.log(error);
        });
    }
  }

}
