import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { ForgotPasswordComponent } from './forgot-password.component';

const routes: Routes = [{ path: '', component: ForgotPasswordComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes), ReactiveFormsModule, ForgotPasswordRoutingModule],
  exports: [RouterModule]
})
export class ForgotPasswordRoutingModule { }
