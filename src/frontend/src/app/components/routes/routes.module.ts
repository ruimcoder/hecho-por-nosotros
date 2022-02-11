import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { UserComponent } from './user/user.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { PerfilComponent } from './perfil/perfil.component';
import {FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { EditPerfilComponent } from './edit-perfil/edit-perfil.component';
import { ListadoComponent } from './listado/listado.component';
import { CreacionPerfilComponent } from './creacion-perfil/creacion-perfil.component';
import { HomeUserComponent } from './home-user/home-user.component';
import { HomeEmprendimientoComponent } from './home-emprendimiento/home-emprendimiento.component';
import { MapaComponent } from './mapa/mapa.component';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatTab, MatTabGroup, MatTabsModule } from '@angular/material/tabs';
import { MapComponent } from './map/map.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { LandingModule } from './landing/landing.module';


@NgModule({
  declarations: [
   
    HomeComponent,
    ContactComponent,
    UserComponent,
    PerfilComponent,
    EditPerfilComponent,
    ListadoComponent,
    CreacionPerfilComponent,
    HomeUserComponent,
    HomeEmprendimientoComponent,
    MapaComponent,
    MapComponent
    
   

  ],
  exports:[MatSelectModule,
    MatOptionModule,
    MatToolbarModule,
     MatInputModule,
 MatFormFieldModule ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule,
    MatSelectModule,
    MatOptionModule,
    MatToolbarModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    NgbTooltipModule,
    MatTableModule,
    MatIconModule,
    FormsModule,
    MatTabsModule,
    MatPaginatorModule

  
  ]
})
export class RoutesModule {
}
