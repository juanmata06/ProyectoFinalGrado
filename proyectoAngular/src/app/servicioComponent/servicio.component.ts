import { HttpParams } from "@angular/common/http";
import { OnInit } from "@angular/core";
import { Component } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Servicio } from "../clases/servicio";
import { Suscriptor } from "../clases/suscriptor";
import { ServicioService } from "../services/servicio.service";
import { SuscriptorService } from "../services/suscriptor.service";


@Component({
    selector: 'servicio-comp',
    templateUrl: 'servicio.component.html',
    styleUrls: ['servicio.component.css'],
    providers: [ServicioService, SuscriptorService]
})

export class servicioComponent implements OnInit {
    constructor(
        private _activRoute: ActivatedRoute,
        private _servicioService: ServicioService,
        private _suscriptorService: SuscriptorService

    ) { }
    servicios: Array<Servicio> = [];
    servicioPedido = "";
    cantidadServicios: number = 0;
    limit = true;
    search = '';

    sendit() {
        if (this.search == '') {
            this.limit = true;
        } else {
            this.limit = false;
        }
        document.location.href = 'http://localhost:4200/servicio/' + this.search;
    }

    ngOnInit(): void {
        this._activRoute.paramMap.subscribe((params) => {
            this.servicioPedido = params.get("tipo") + "";
        });
        if (this.servicioPedido != "null") { //Buscar por nombre:
            this._servicioService.getServiciosByName(this.servicioPedido)
                .subscribe((resultado) => {
                    console.log(resultado)
                    for (let i of resultado) {
                        let servicio = new Servicio(
                            i['id'], i['nombre'], i['descripcion'],
                            i['precio'], i['puntuacion'], i['activo'],
                            i['imagenes'], i['suscriptor']
                        );
                        this.cantidadServicios = ++this.cantidadServicios;
                        this.servicios.push(servicio);
                    }
                }, (error) => {
                    console.log(error);
                });
        } else { //Buscar todos:
            this._servicioService.getAllServicios()
                .subscribe((resultado) => {
                    for (let i of resultado) {
                        let servicio = new Servicio(
                            i['id'], i['nombre'], i['descripcion'],
                            i['precio'], i['puntuacion'], i['activo'],
                            i['imagenes'], i['suscriptor']
                        );
                        this.cantidadServicios = ++this.cantidadServicios;
                        console.log(this.cantidadServicios);
                        this.servicios.push(servicio);
                    }
                    console.log(this.servicios);
                }, (error) => {
                    console.log(error);
                });
        }
    }

}