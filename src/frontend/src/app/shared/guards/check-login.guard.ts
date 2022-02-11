import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/components/auth/services/auth.service';



@Injectable({
  providedIn: 'root'
})
export class CheckLoginGuard implements CanActivate {
  
  constructor(public authSvc: AuthService, public router: Router) {
  }
  canActivate(
  next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
     {
      localStorage.setItem("hxn-last-url", state.url);
      if (!this.authSvc.isUserLoggedIn()) {
  
        this.router.navigate(['login']);
      } else {
        if (!this.authSvc.userHaveClaims()) {
         // swal('Esperando autorización!', 'Un administrador del sistema le asignará los permisos correspondientes para poder ingresar al sistema.', 'info');
          this.router.navigate(['login']);
        } else {
          let authorities: Array<string> = next.data.authorities;
          if (authorities?.length === 0) {
            return true;
          }
          let havePermissions = false;
          for (let i = 0; i < authorities.length; i++)
            if (this.authSvc.isAuthorized(authorities[i]))
              havePermissions = true;
          if (!havePermissions) {
           // swal('Permiso denegado!', 'No tiene los permisos requeridos para acceder a este sector del sistema.', 'info');
            this.router.navigate(this.authSvc.getHomeByAuthorities());
          } else {
            return true;
          }
          return true;
        }
      }
     }
    }
  }
