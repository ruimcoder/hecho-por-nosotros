import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl, ValidationErrors, ValidatorFn, } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  providers: [AuthService],
})
export class RegisterComponent implements OnInit {
  public RegisterForm:FormGroup
  success:boolean=false;
  failure:boolean=false;
  public valueRequired(value: string) {
    return this.RegisterForm.controls[value].hasError('required') && (this.RegisterForm.controls[value].dirty || this.RegisterForm.controls[value].touched);
  }
  
  constructor(public Svc: AuthService, private router: Router) {
    this.RegisterForm = new FormGroup({
      email: new FormControl('',[Validators.required, Validators.email]),
      Password: new FormControl('',[Validators.required, Validators.minLength(6)]),
      Username:new FormControl('',Validators.required),
      Repeatpassword:new FormControl('',[Validators.required, Validators.minLength(6)]),
    },{validators:this.checkPasswords});
  }
  checkPasswords: ValidatorFn = (group: AbstractControl): ValidationErrors | null => {
    let pass = group.get('Password').value;
    let confirmPass = group.get('Repeatpassword').value;
    return pass === confirmPass ? null : { notSame: true }
  }

  ngOnInit(): void { }

  async onRegister() {
    this.RegisterForm.markAllAsTouched()
    if (this.RegisterForm.valid){
    const { email, Password,} = this.RegisterForm.value;
    console.log(email);
    console.log(Password);
    //console.log(email, password);
    const user = await this.Svc.onRegister (email,Password); 
    debugger
    if(user!=null)
    {
      this.success=true;
      setTimeout(() => {
        this.router.navigate(['/login']);
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