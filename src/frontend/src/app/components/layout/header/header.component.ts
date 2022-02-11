import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/services/auth.service';
import { UsersService } from '../../auth/services/users/users.service';
import firebase from 'firebase/app';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(
    public auths: AuthService,
    public auth: AngularFireAuth,
    public user: UsersService,
    public routes: Router
  ) {

    // this.user_point();
  }

  ngOnInit(): void {

  }

  // user_point() {

  //   firebase.auth().currentUser?.getIdTokenResult()
  //     .then((idTokenResult) => {

  //       this.user.findAll(idTokenResult.token).subscribe(
  //         (responseUser: any) => {

  //           this.user = responseUser;

  //         }
  //       ),
  //         (error: any) => {
  //           console.log(error);
  //           /*siempre crear error, para el endpoint */
  //         }
  //     })

  // }


  cerrar_secion(){
    
    this.auths.logout();
   }

}
