import { group } from '@angular/animations';
import { formatCurrency } from '@angular/common';
import { parseI18nMeta } from '@angular/compiler/src/render3/view/i18n/meta';
import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormControlName, Validators, FormBuilder, EmailValidator, MaxLengthValidator, MaxValidator } from '@angular/forms';

import { Router } from '@angular/router';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { max } from 'rxjs/operators';
import { NgbTooltip } from '@ng-bootstrap/ng-bootstrap';
import { isJSDocThisTag } from 'typescript';

import { AuthService } from '../../auth/services/auth.service';
import { __await } from 'tslib';
import { EmprendimientoService } from '../../auth/services/emprendimiento.service';

@Component({
  selector: 'app-creacion-perfil',
  templateUrl: './creacion-perfil.component.html',
  styleUrls: ['./creacion-perfil.component.scss']
})
export class CreacionPerfilComponent implements OnInit {
  imageError: string;
  isImageSaved: boolean;
  cardImageBase64: string;
datos_emp: any;
  name = 'World';

  generos: Array<string> = ['Femenino', 'Masculino', 'Otros']
  empresas: Array<string> = ['Artesano', 'Productor de agricultura o ganaderia', 'Micro o mediana empresa', 'Dueño de marca / comerciante']
  perteneces: Array<string> = ['Asociación', 'Fundación', 'Cooperativa', 'Organización']
inverted: Array<string>=["assets/S_SDG_inverted_WEB-17 (1).png","assets/S_SDG_inverted_WEB-17 (2).png","assets/S_SDG_inverted_WEB-17 (3).png","assets/S_SDG_inverted_WEB-17 (4).png","assets/S_SDG_inverted_WEB-17 (5).png","assets/S_SDG_inverted_WEB-17 (6).png","assets/S_SDG_inverted_WEB-17 (7).png","assets/S_SDG_inverted_WEB-17 (8).png","assets/S_SDG_inverted_WEB-17 (9).png","assets/S_SDG_inverted_WEB-17 (10).png","assets/S_SDG_inverted_WEB-17 (11).png","assets/S_SDG_inverted_WEB-17 (12).png","assets/S_SDG_inverted_WEB-17 (13).png","assets/S_SDG_inverted_WEB-17 (14).png","assets/S_SDG_inverted_WEB-17 (15).png","assets/S_SDG_inverted_WEB-17 (16).png","assets/S_SDG_inverted_WEB-17 (17).png"]
metasSeleccionadas: Array<string>=[]
public perfilForm: FormGroup

public valueRequired(value: string) {
  return this.perfilForm.controls[value].hasError('required') && (this.perfilForm.controls[value].dirty || this.perfilForm.controls[value].touched);
}
public valueMinMax(value: string) {
  return (this.perfilForm.controls[value].hasError('minLength')||this.perfilForm.controls[value].hasError('maxLength') && (this.perfilForm.controls[value].dirty || this.perfilForm.controls[value].touched));
}
public valueMax(value:string){
  return((this.perfilForm.controls[value].value.length > 1600) && (this.perfilForm.controls[value].touched));
}

public valueMin(value:string){
  return((this.perfilForm.controls[value].value.length < 200) && (this.perfilForm.controls[value].touched));
}


  public changeEmpresa() {

    if (this.perfilForm.value.tipo === "Micro o mediana empresa" || this.perfilForm.value.tipo === "Dueño de marca / comerciante") {
      this.perfilForm.controls.pertenece.disable()
    }
    else {
      this.perfilForm.controls.pertenece.enable()
    }
  }
  constructor(private authSvc: AuthService, private router: Router, emprendimientoServices: EmprendimientoService) {
    this.perfilForm = new FormGroup({
      nombre: new FormControl('', [Validators.required]),
      genero: new FormControl('', [Validators.required]),
      slogan: new FormControl('', [Validators.required]),
      registro: new FormControl('', [Validators.required]),

      tipo: new FormControl('', [Validators.required]),
      pertenece: new FormControl('', [Validators.required]),
      exporta: new FormControl('', [Validators.required]),
      count_empleados: new FormControl('',[Validators.required]),
      count_mujeres: new FormControl('',[Validators.required]),
      archivo: new FormControl('', [Validators.required]),
      historia: new FormControl('', Validators.compose([Validators.required, Validators.minLength(10), Validators.maxLength(15)]) ),
    });

   }

  ngOnInit(): void {}
  onCheckImagen($event: any, i: number) {
    if ($event.target.checked) {

      this.metasSeleccionadas.push($event.target.value);

    } else {

      const index = this.metasSeleccionadas.findIndex(x => x === $event.target.value);

      this.metasSeleccionadas.splice(index, 1);

    }
    console.log(this.metasSeleccionadas)
  }

  enviar() {
    this.datos_emp = Object.assign(this.perfilForm.value);
    console.log(this.datos_emp);
  }


  carga_emprendimiento() {
    this.datos_emp = Object.assign(this.perfilForm.value);
    console.log(this.datos_emp)
  }


  async onPerfil() {
    this.perfilForm.markAllAsTouched()

    if (this.perfilForm.valid) {
      const { radio, archivo, pertenece, empresa, frase, genero, nombredelemprendimiento } = this.perfilForm.value
      console.log(radio, archivo, pertenece, empresa, frase, genero, nombredelemprendimiento)
      this.router.navigate(['/init/home'])
    }
  }
  
   
    
   
  
}



