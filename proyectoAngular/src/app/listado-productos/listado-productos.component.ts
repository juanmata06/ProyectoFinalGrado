import { HttpParams } from "@angular/common/http";
import { OnInit } from "@angular/core";
import { Component } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Producto } from "../clases/producto";
import { Proveedor } from "../clases/proveedor";
import { Suscriptor } from "../clases/suscriptor";
import { ProductoService } from "../services/producto.service";

@Component({
  selector: 'app-listado-productos',
  templateUrl: './listado-productos.component.html',
  styleUrls: ['./listado-productos.component.css'],
  providers: [ProductoService]
})

export class ListadoProductosComponent implements OnInit {
  constructor(
    private _activRoute: ActivatedRoute,
    private _productoService: ProductoService
  ) { }
  productos: Array<Producto> = [];
  productoPedido = "";
  cantidadProductos: number = 0;
  limit = true;
  search = '';
  proveedor: Proveedor = new Proveedor();
  sendit() {
    if (this.search == '') {
      this.limit = true;
    } else {
      this.limit = false;
    }
    document.location.href = 'http://localhost:4200/productos/' + this.search;
  }

  ngOnInit(): void {

    console.log(localStorage);

    this._activRoute.paramMap.subscribe((params) => {
      this.productoPedido = params.get("tipo") + "";
    });
    if (this.productoPedido != "null") { //Buscar por nombre:
      this._productoService.getProductosByName(this.productoPedido)
        .subscribe((resultado) => {
          console.log(resultado)
          for (let i of resultado) {
            let producto = new Producto(
              i['id'], i['nombre'], i['descripcion'],
              i['precio'], i['puntuacion'], i['activo'],
              i['imagenes'], this.proveedor
            );
            this.cantidadProductos = ++this.cantidadProductos;
            this.productos.push(producto);
          }
          console.log(this.productos);
        }, (error) => {
          console.log(error);
        });
    } else { //Buscar todos:
      this._productoService.getAllProductos()
        .subscribe((resultado) => {
          for (let i of resultado) {
            let producto = new Producto(
              i['id'], i['nombre'], i['descripcion'],
              i['precio'], i['puntuacion'], i['activo'],
              i['imagenes'], this.proveedor
            );
            this.cantidadProductos = ++this.cantidadProductos;
            this.productos.push(producto);
          }
          console.log(this.productos);
        }, (error) => {
          console.log(error);
        });
    }
  }

}