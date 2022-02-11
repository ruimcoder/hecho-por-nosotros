import { Injectable } from '@angular/core';
import { AngularFireAuth } from "@angular/fire/auth";
import { UsersService, } from './users/users.service';
import firebaseapp from 'firebase/app';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';



@Injectable({
  providedIn: 'root'
})
export class AuthService {



  constructor(
    public afAuth: AngularFireAuth,
    public userService: UsersService,
    private router: Router) {
      
    this.afAuth.authState.subscribe(userResponse => {
      if (userResponse) {
        localStorage.setItem('hxn-workload-user', JSON.stringify(userResponse));
      } else {
        localStorage.setItem('hxn-workload-user', null);
      }
    })
  }

  async resetPassword(email: string): Promise<void> {
    try {
      return this.afAuth.sendPasswordResetEmail(email);
    }
    catch (error) {
      console.log(error);
    }
  }

  async loginGoogle() {

    return this.afAuth.signInWithPopup(new firebaseapp.auth.GoogleAuthProvider).then((userresponse: any) => {

      firebaseapp.auth().currentUser?.getIdTokenResult().then((idTokenResult) => {
        if (userresponse.user) {
          localStorage.setItem('hxn-workload-user', JSON.stringify(userresponse.user));
        }
        else {
          localStorage.setItem('hxn-workload-user', '');
        }

        this.userService.find(userresponse.user.uid).subscribe((user: any) => {

          if (user.custom_claims && JSON.stringify(user.custom_claims) != '{}')

            localStorage.setItem('hxn-workload-user-claims', JSON.stringify(user.custom_claims));
          else
            localStorage.setItem('hxn-workload-user-claims', "");

          if (!this.userHaveClaims()) {
            //sweetAlert('Esperando autorización!', 'Un administrador del sistema le asignará los permisos correspondientes para poder ingresar al sistema.', 'info');
            this.router.navigate(['login']);
          } else {
            if (localStorage.getItem("hxn-workload-last-url") && localStorage.getItem("hxn-workload-last-url") != "") {
              this.router.navigate(localStorage.getItem("hxn-workload-last-url").split("/"));
            } else {

              this.router.navigate(this.getHomeByAuthorities()); //modificacion
            }
          }

        }, error => { })

        console.log(idTokenResult.token);
      })

    })
      .catch(
        (error: any) => {
          //swal('Error de autenticación!', 'Ocurrió un error al iniciar sesión.', 'error');
          console.log(error);
        })
  }





  isAuthorized(authority: string) {
    if (!this.userHaveClaims())
      return false;

    let authorities = JSON.parse(localStorage.getItem("hxn_authorization")).authorities;

    return authorities.indexOf(authority) != -1
  }

  isUserLoggedIn() {
    return JSON.parse(localStorage.getItem('hxn_authentication'));
  }

  userHaveAuthorities() {
    return JSON.parse(localStorage.getItem('hxn_authorization'));
  }
  getHomeByAuthorities() {
    let autorities = JSON.parse(localStorage.getItem('hxn-workload-user-claims') || '{}');

    if(autorities["ROLE_SUPER"])
    return ['/init/home'];

    else{
    return['/init/home-emp'];
    }

  }

  result: any;
  async onLogin(Email: string, password: string) {
    
    try {
      this.result = await this.afAuth.signInWithEmailAndPassword(
        Email,
        password
      );

      
        firebaseapp.auth().currentUser?.getIdTokenResult().then((idTokenResult) => {
          if (this.result.user) {
            localStorage.setItem('hxn-workload-user', JSON.stringify(this.result.user));
          }
          else {
            localStorage.setItem('hxn-workload-user', '');
          }
  
          this.userService.find(this.result.user.uid).subscribe((user: any) => {
  
            if (user.custom_claims && JSON.stringify(user.custom_claims) != '{}')
  
              localStorage.setItem('hxn-workload-user-claims', JSON.stringify(user.custom_claims));
            else
              localStorage.setItem('hxn-workload-user-claims', "");
  
            if (!this.userHaveClaims()) {
              //sweetAlert('Esperando autorización!', 'Un administrador del sistema le asignará los permisos correspondientes para poder ingresar al sistema.', 'info');
              this.router.navigate(['login']);
            } else {
              if (localStorage.getItem("hxn-workload-last-url") && localStorage.getItem("hxn-workload-last-url") != "") {
                this.router.navigate(localStorage.getItem("hxn-workload-last-url").split("/"));
              } else {
  
                this.router.navigate(this.getHomeByAuthorities());
              }
            }
  
          }, error => { })
  
          // console.log(idTokenResult.token);
        })
  
    

    }
    catch (error) {
      console.log(error);
    }
    return this.result;
  }


  async onRegister(email: string, password: string) {

    try {
      this.result = await this.afAuth.createUserWithEmailAndPassword(
        email,
        password,
      );
      return this.result;
    }
    catch (error) {
      console.log(error);
      return null
    }
  }

  // async logout() {
  //   try {
  //     await this.afAuth.signOut()
  //   }
  //   catch (error) {
  //     console.log(error);
  //   }
  // }

  async logout() {

    return await this.afAuth.signOut().then(
      () => {
      
        localStorage.removeItem('hxn-workload-user');
        localStorage.removeItem('hxn-workload-user-claims');
        this.router.navigate(['login']);
      }
    );
  }



  getCurrentUser() {
    return this.afAuth.authState.pipe(first()).toPromise
  }
  userHaveClaims() {
    return JSON.parse(localStorage.getItem('hxn-workload-user-claims') || '{}');
  }
}