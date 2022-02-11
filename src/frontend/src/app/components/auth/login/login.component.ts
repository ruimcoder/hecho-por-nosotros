import { group } from '@angular/animations';
import { formatCurrency } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormControlName, Validators, FormBuilder, EmailValidator } from '@angular/forms';
import { Router } from '@angular/router';
import { isJSDocThisTag } from 'typescript';

import { AuthService, } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [AuthService],
})
export class LoginComponent implements OnInit {
  
  success:boolean=false;
  failure:boolean=false;
  googleFailure:boolean=false;
  public loginForm:FormGroup
 
  public valueRequired(value: string) {
    return this.loginForm.controls[value].hasError('required') && (this.loginForm.controls[value].dirty || this.loginForm.controls[value].touched);
  }

  constructor(private authSvc: AuthService, private router: Router) {
    this.loginForm = new FormGroup({
      email: new FormControl('',[Validators.required, Validators.email]),
      password: new FormControl('',[Validators.required, Validators.minLength(6)]),
    });
  }

  async onGoogleLogin() {

    const user = await this.authSvc.loginGoogle();
    
    if( ! localStorage.getItem('hxn-workload-user')){
      this.googleFailure= true;
    }
    
    }
  
  
  ngOnInit(): void { }
  async onLogin() {
    this.loginForm.markAllAsTouched()
    if (this.loginForm.valid){
    const { email, password,} = this.loginForm.value
    // console.log(email, password)
    const user = await this.authSvc.onLogin(email,password); 
      if (user != null) {
        this.success = true;
        setTimeout(() => {
         // this.router.navigate(['/init/home']);
           this.router.navigate(this.authSvc.getHomeByAuthorities());
        }, 3000)
      }
      else{
        this.failure=true;
        setTimeout(()=>{
          this.failure=false;
        },2000)
      }
    
  }
  }
}
