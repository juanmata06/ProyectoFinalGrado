import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { loginComponent } from './loginComponent/login.component';
import { mainComponent } from './mainComponent/main.component';
import { PageNotFoundComponent } from './PageNotFoundComponent/pagenotfound.component';
import { servicioComponent } from './servicioComponent/servicio.component';
import { crearcuentaComponent } from './crearcuentaComponent/crearcuenta.component';
import { vistaservicioComponent } from './vistaservicioComponent/vistaservicio.component';
import { vistamascotaComponent } from './vistamascotaComponent/vistamascota.component';
import { perfilComponent } from './perfilComponent/perfil.component';
import { SubscriptionsComponentComponent } from './subscriptionsComponent/subscriptions.component';
import { crearservicioComponent } from './crearservicioComponent/crearservicio.component';
import { crearmascotaComponent } from './crearmascotaComponent/crearmascota.component';
import { footerComponent } from './footerComponent/footer.component';
import { nosotrosComponent } from './nosotrosComponent/nosotros.component';
import { funcionamientoComponent } from './funcionamientoComponent/funcionamiento.component';
import { preguntasComponent } from './preguntasComponent/soporte.component';
import { condicionesComponent } from './condicionesComponent/condiciones.component';
import { cookiesComponent } from './cookiesComponent/cookies.component';
import { ListadoProductosComponent } from './listado-productos/listado-productos.component';
import { ListadoMascotasComponent } from './listado-mascotas/listado-mascotas.component';
import { VistaProductoComponentComponent } from './vista-producto-component/vista-producto-component.component';
import { ComentarServicio } from './comentarServicio/ComentarServicio';
import { ComentarMascota } from './comentarMascota/ComentarMascota';

@NgModule({
  declarations: [
    AppComponent,
    loginComponent,
    mainComponent,
    servicioComponent,
    crearcuentaComponent,
    vistaservicioComponent,
    vistamascotaComponent,
    perfilComponent,
    PageNotFoundComponent,
    crearservicioComponent,
    crearmascotaComponent,
    footerComponent,
    nosotrosComponent,
    funcionamientoComponent,
    preguntasComponent,
    condicionesComponent,
    cookiesComponent,
    SubscriptionsComponentComponent,
    ListadoProductosComponent,
    ListadoMascotasComponent,
    VistaProductoComponentComponent,
    ComentarServicio,
    ComentarMascota
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }