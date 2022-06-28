import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Producto } from "../clases/producto";
import { Proveedor } from "../clases/proveedor";
import { ProductoService } from "../services/producto.service";

@Component({
  selector: 'app-vista-producto-component',
  templateUrl: './vista-producto-component.component.html',
  styleUrls: ['./vista-producto-component.component.css'],
  providers: [ProductoService]
})

export class VistaProductoComponentComponent implements OnInit {

    constructor(
        private _activRoute: ActivatedRoute,
        private _productoService: ProductoService,
    ) { }
    id = "";
    cnt = false;
    producto: Producto = new Producto();
    proveedor: Proveedor = new Proveedor();

    ngOnInit(): void {
        this._activRoute.paramMap.subscribe(
            (params) => {
                this.id = params.get("producto") + "";
            }
        );

        this._productoService.getProductoById(parseInt(this.id)).
            subscribe((resultado) => {
                this.producto = resultado;
                this.proveedor = this.producto.proveedor
            },
                (error) => {
                    console.log(error);
                }
            );
    }
}
