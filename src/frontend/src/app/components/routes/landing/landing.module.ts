import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LandingComponent } from './landing.component';
import { FormsModule } from '@angular/forms';
import {IvyCarouselModule} from 'angular-responsive-carousel';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    LandingComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    IvyCarouselModule,
    RouterModule
  ]
})
export class LandingModule { }