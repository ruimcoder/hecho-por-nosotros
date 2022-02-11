import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { LayoutComponent } from './layout.component';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';





@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    FooterComponent,
    SidenavComponent
  ],
  exports: [
    LayoutComponent,
    HeaderComponent,
    FooterComponent,
    SidenavComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule
   
  ],
})
export class LayoutModule { }
