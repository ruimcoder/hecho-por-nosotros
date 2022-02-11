import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { RestablecerComponent } from './restablecer.component'; 
import { ReactiveFormsModule } from '@angular/forms';

const routes: Routes = [{ path: '', component: RestablecerComponent}];

@NgModule({
  declarations: [
    RestablecerComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ReactiveFormsModule, 
  ]
})
export class RestablecerModule { }


