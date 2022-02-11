import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { RestablecerComponent } from './components/auth/restablecer/restablecer.component';
import { LayoutComponent } from './components/layout/layout.component';

import { ContactComponent } from './components/routes/contact/contact.component';
import { CreacionPerfilComponent } from './components/routes/creacion-perfil/creacion-perfil.component';
import { EditPerfilComponent } from './components/routes/edit-perfil/edit-perfil.component';
import { HomeEmprendimientoComponent } from './components/routes/home-emprendimiento/home-emprendimiento.component';
import { HomeUserComponent } from './components/routes/home-user/home-user.component';

import { HomeComponent } from './components/routes/home/home.component';
import { LandingComponent } from './components/routes/landing/landing.component';
import { PerfilComponent } from './components/routes/perfil/perfil.component';




const routes: Routes = [
  {
    path:'', pathMatch: 'full',
    redirectTo:'/login'
  },
  {
    path:'init',
    component:LayoutComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent
        },
      {
        path: 'contact/:id',
        component: ContactComponent
      },
      
      {
        path:'restablecer',
        component: RestablecerComponent
      },
     {
       path:'Perfil/:id',
       component: PerfilComponent
     },
     {
      path:'Edit-Perfil/:id',
      component: EditPerfilComponent
    },
    {
      path:'creacion-perfil',
      component: CreacionPerfilComponent
    },
    {
      path:'home-user',
      component: HomeUserComponent
    },
    {
      path:'home-emp',
      component: HomeEmprendimientoComponent
    },
    {
      path:'home-emprendimiento',
      component: HomeEmprendimientoComponent
    },

    ],
    
  },

  {
    path: 'landing/:id', component: LandingComponent
  },
  {
    path: 'login',
    canActivate: [],
    component: LoginComponent
  },
  {
    path: 'register',
    canActivate: [],
    component: RegisterComponent
  },
  { path: 'forgot-password', 
  loadChildren: () => 
  import('./components/auth/auth/forgot-password/forgot-password.module').then(
  (m) => m.ForgotPasswordModule 
  ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
