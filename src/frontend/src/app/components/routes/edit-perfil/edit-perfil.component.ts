import { group } from '@angular/animations';
import { formatCurrency } from '@angular/common';
import { parseI18nMeta } from '@angular/compiler/src/render3/view/i18n/meta';
import { Component, Input, OnInit, resolveForwardRef } from '@angular/core';
import { FormGroup, FormControl, FormControlName, Validators, FormBuilder, EmailValidator, MaxLengthValidator, MaxValidator } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { max } from 'rxjs/operators';
import { NgbTooltip } from '@ng-bootstrap/ng-bootstrap';
import { isJSDocThisTag } from 'typescript';

import { AuthService } from '../../auth/services/auth.service';
import { __await } from 'tslib';
import { EmprendimientoService } from '../../auth/services/emprendimiento.service';
import { CardHome } from 'src/app/interfaces/card-home';
import { Emprendimiento } from 'src/app/interfaces/emprendimiento';
import { Contacto } from 'src/app/interfaces/contacto';
import { Historia } from 'src/app/interfaces/historia';
import { Certificaciones } from 'src/app/interfaces/certificaciones';
import { Tecnica } from 'src/app/interfaces/tecnica';
import { Punto } from 'src/app/interfaces/punto';
import { Proveedor } from 'src/app/interfaces/proveedor';
import { Meta } from 'src/app/interfaces/meta';
import { ɵangular_material_src_material_grid_list_grid_list_a } from '@angular/material/grid-list';
import { DomSanitizer } from '@angular/platform-browser';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-edit-perfil',
  templateUrl: './edit-perfil.component.html',
  styleUrls: ['./edit-perfil.component.scss']
})
export class EditPerfilComponent implements OnInit {
  imageError: string;
  isImageSaved: boolean;
  cardImageBase64: string;
  datos_emp: any;
  name = 'World';
public id: number

//lat:number;
//lng:number;
//zoom: number;
//mapTypeId:string;



  generos: Array<string> = ['Femenino', 'Masculino', 'Otros']
  empresas: Array<string> = ['Artesano', 'Productor de agricultura o ganaderia', 'Micro o mediana empresa', 'Dueño de marca / comerciante']
  perteneces: Array<string> = ['Asociación', 'Fundación', 'Cooperativa', 'Organización']
  inverted: Array<string> = ["assets/S_SDG_inverted_WEB-17 (1).png", "assets/S_SDG_inverted_WEB-17 (2).png", "assets/S_SDG_inverted_WEB-17 (3).png", "assets/S_SDG_inverted_WEB-17 (4).png", "assets/S_SDG_inverted_WEB-17 (5).png", "assets/S_SDG_inverted_WEB-17 (6).png", "assets/S_SDG_inverted_WEB-17 (7).png", "assets/S_SDG_inverted_WEB-17 (8).png", "assets/S_SDG_inverted_WEB-17 (9).png", "assets/S_SDG_inverted_WEB-17 (10).png", "assets/S_SDG_inverted_WEB-17 (11).png", "assets/S_SDG_inverted_WEB-17 (12).png", "assets/S_SDG_inverted_WEB-17 (13).png", "assets/S_SDG_inverted_WEB-17 (14).png", "assets/S_SDG_inverted_WEB-17 (15).png", "assets/S_SDG_inverted_WEB-17 (16).png", "assets/S_SDG_inverted_WEB-17 (17).png"]
  tipos:Array<string> =['Tienda', 'Feria', 'Mercado de Artesania', 'Casa', 'Plaza', 'Boutique','Otros']
  selected: Array<{imagen:string, selected:boolean}>
  metasSeleccionadas: Array<string> = []
  public perfilForm: FormGroup
public contactoForm: FormGroup
public certificadoForm: FormGroup
public tecnicaForm: FormGroup
public puntoForm: FormGroup
public proveedorForm: FormGroup
public historiaForm:FormGroup
public metaForm: FormGroup

  public emprendimiento:Emprendimiento
  public contactos: Contacto
  public historias:Historia
  public certificado: Certificaciones
  public tecnica: Tecnica
  public punto: Punto
  public proveedor: Proveedor

  public archivo: any = []
  public foto: any = []
  public certificacion: any = []
  public certificados1: any = []
  public tecnicas: any = []

  public valueRequiredPerfil(value: string) {
    return this.perfilForm.controls[value].hasError('required') && (this.perfilForm.controls[value].dirty || this.perfilForm.controls[value].touched);
  }

  public valueRequiredHistoria(value: string) {
    return this.historiaForm.controls[value].hasError('required') && (this.historiaForm.controls[value].dirty || this.historiaForm.controls[value].touched);
  }
  public valueRequiredCertificado(value: string) {
    return this.certificadoForm.controls[value].hasError('required') && (this.certificadoForm.controls[value].dirty || this.certificadoForm.controls[value].touched);
  }
   public valueRequiredTecnica(value: string) {
    return this.tecnicaForm.controls[value].hasError('required') && (this.tecnicaForm.controls[value].dirty || this.tecnicaForm.controls[value].touched);
  }
   public valueRequiredPunto(value: string) {
    return this.puntoForm.controls[value].hasError('required') && (this.puntoForm.controls[value].dirty || this.puntoForm.controls[value].touched);
  }
   public valueRequiredProveedor(value: string) {
    return this.proveedorForm.controls[value].hasError('required') && (this.proveedorForm.controls[value].dirty || this.proveedorForm.controls[value].touched);
  }
   public valueRequiredMeta(value: string) {
    return this.metaForm.controls[value].hasError('required') && (this.metaForm.controls[value].dirty || this.metaForm.controls[value].touched);
  }
  public valueMinMax(value: string) {
    return (this.historiaForm.controls[value].hasError('minLength') || this.historiaForm.controls[value].hasError('maxLength') && (this.historiaForm.controls[value].dirty || this.historiaForm.controls[value].touched));
  }
  public valueMax(value: string) {
    return ((this.historiaForm.controls[value].value.length > 1600) && (this.historiaForm.controls[value].touched));
  }

  public valueMin(value: string) {
    return ((this.historiaForm.controls[value].value.length < 200) && (this.historiaForm.controls[value].touched));
  }

  public valueMaxi(value: string) {
    return ((this.tecnicaForm.controls[value].value.length > 1600) && (this.tecnicaForm.controls[value].touched));
  }

  public valueMini(value: string) {
    return ((this.tecnicaForm.controls[value].value.length < 200) && (this.tecnicaForm.controls[value].touched));
  }

  public changeEmpresa() {

    if (this.perfilForm.value.tipo === "Micro o mediana empresa" || this.perfilForm.value.tipo === "Dueño de marca / comerciante") {
      this.perfilForm.controls.pertenece.disable()
    }
    else {
      this.perfilForm.controls.pertenece.enable()
    }
  }
bandera: string;
valor: string;

  urlimg:string='';
  cardsContent: Array<CardHome>=[];

  constructor(private authSvc: AuthService, private router: Router, private emprendimientoServices: EmprendimientoService, private sanitizer: DomSanitizer, private activatedRoute: ActivatedRoute) {
    




    this.perfilForm = new FormGroup({
      nombre: new FormControl('', [Validators.required]),
      genero: new FormControl('', [Validators.required]),
      slogan: new FormControl('', [Validators.required]),
      registro: new FormControl('', [Validators.required]),

      tipo: new FormControl('', [Validators.required]),
      pertenece: new FormControl('', [Validators.required]),
      exporta: new FormControl('', [Validators.required]),
      count_empleados: new FormControl('', [Validators.required]),
      count_mujeres: new FormControl('', [Validators.required]),
      count_jovenes:new FormControl('', [Validators.required]),
      count_colaboracion: new FormControl('', [Validators.required]),
      count_jovenes_colaboracion: new FormControl('', [Validators.required]),
      count_mujeres_colaboracion: new FormControl('', [Validators.required]),
      
    });

    this.contactoForm = new FormGroup({
      direccion: new FormControl('', [Validators.required]),
      pais: new FormControl('', [Validators.required]),
      telefono: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      instagram: new FormControl('', [Validators.required]),
      facebook: new FormControl('', [Validators.required]),
       });

       this.historiaForm = new FormGroup({
        texto: new FormControl('', [Validators.required,Validators.minLength(200),Validators.maxLength(1300)]),
        url_video: new FormControl('', [Validators.required]),
        
        });

        this.certificadoForm = new FormGroup({
         
          });
          
          this.tecnicaForm = new FormGroup({
            texto: new FormControl('', [Validators.required]),
            url_video: new FormControl('', [Validators.required]),
            
            });

            this.puntoForm = new FormGroup({
              direccion:new FormControl('', [Validators.required]),
              direccionSec:new FormControl('', [Validators.required]),
              nombre:new FormControl('', [Validators.required]),
            
              ciudad:new FormControl('', [Validators.required]),
              cp:new FormControl('', [Validators.required]),
              tipo:new FormControl('', [Validators.required]),
              web:new FormControl('', [Validators.required]),
              contacto:new FormControl('', [Validators.required]),
              });

              this.proveedorForm = new FormGroup({
                nombre: new FormControl('', [Validators.required]),
                direccion: new FormControl('', [Validators.required]),
                pais: new FormControl('', [Validators.required]),
                ciudad: new FormControl('', [Validators.required]),
                cp:new FormControl('', [Validators.required]),
                materiales:new FormControl('', [Validators.required]),
              });

              this.metaForm = new FormGroup({
                id_material:new FormControl('', [Validators.required]),
                id_proveedor:new FormControl('', [Validators.required]),
                especificar:new FormControl('', [Validators.required]),
              });

    this.oculta()
  }


  ngOnInit(): void{ 
    

//this.lat=40
//this.lng=1
//this.zoom=1
//this.mapTypeId='hybrid'

  }


    


 capturaFile(event:any) {
   const archivocapturado= event.target.files[0]
   this.extraerBase64(archivocapturado).then((imagen:any) =>{
console.log(imagen)
this.archivo.push(imagen)
    })
}

  extraerBase64 = async ($event: any) => new Promise((resolve, reject) =>{
   try{
      const unsafeImg = window.URL.createObjectURL($event);
      const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
      const reader = new FileReader();
      reader.readAsDataURL($event);
      reader.onload = ()=> {
        resolve({
          type: "logo",
          image: reader.result
         
        }
        
        );
      };
      reader.onerror = error =>{
        resolveForwardRef({   
          type: "logo",
          image:null
      
        });
      };

    }
    catch (e){
      return null;
    }
  })


  capturaHistoria(event:any, i:number) {
    const archivocapturado= event.target.files[i]
   this.extraeBase64(archivocapturado).then((imagen:any) =>{
 this.foto.push(imagen)
console.log(imagen)
    })
}

  extraeBase64 = async ($event: any) => new Promise((resolve, reject) =>{
   try{
      const unsafeImg = window.URL.createObjectURL($event);
      const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
      const reader = new FileReader();
      reader.readAsDataURL($event);
      reader.onload = ()=> {
        resolve({
          type: "foto",
          image: reader.result
         
        }
        
        );
      };
      reader.onerror = error =>{
        resolveForwardRef({   
          type: "foto",
          image:null
      
        });
      };

    }
    catch (e){
      return null;
    }
  })

  capturaCertificado(event:any,i:number) {
    debugger
    const archivocapturado= event.target.files[i]
    this.Base64(archivocapturado).then((imagen:any) =>{
 console.log(imagen)
 debugger
 this.certificacion.push(imagen)
     })
 }
 
   Base64 = async ($event: any) => new Promise((resolve, reject) =>{
    try{
       const unsafeImg = window.URL.createObjectURL($event);
       const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
       const reader = new FileReader();
       reader.readAsDataURL($event);
       reader.onload = ()=> {
         resolve({
           type: "certificado",
           image: reader.result
          
         }
         
         );
       };
       reader.onerror = error =>{
         resolveForwardRef({   
           type: "certificado",
           image:null
       
         });
       };
 
     }
     catch (e){
       return null;
     }
   })
  
   

   capturaTecnica(event:any, i:number) {
    const archivocapturado= event.target.files[i]
    this.extraBases64(archivocapturado).then((imagen:any) =>{
 console.log(imagen)
 this.tecnicas.push(imagen)
     })
 }
 
   extraBases64 = async ($event: any) => new Promise((resolve, reject) =>{
    try{
       const unsafeImg = window.URL.createObjectURL($event);
       const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
       const reader = new FileReader();
       reader.readAsDataURL($event);
       reader.onload = ()=> {
         resolve({
           type: "foto",
           image: reader.result
          
         }
         
         );
       };
       reader.onerror = error =>{
         resolveForwardRef({   
           type: "foto",
           image:null
       
         });
       };
 
     }
     catch (e){
       return null;
     }
   })

  oculta(){
    this.valor= 'emprendimiento';
 
  }
 contacto(){
    this.valor= 'contacto';
   
  }
 historia(){
    this.valor= 'historia';
   
  }
  certificacionesobtenidas(){
    this.valor= 'certificacionesobtenidas';
  
  }
  tecnicadetrabajo(){
    this.valor= 'tecnicadetrabajo';
    
  }
  puntodeventa(){
    this.valor= 'puntodeventa';
  
  }
  nombredelproveedor(){
    this.valor= 'nombredelproveedor';
   
  }
 metadedesarrollo(){
    this.valor=  'metadedesarrollo';
  }

  onCheckImagen($event: any, i: number) {
    if ($event.target.checked) {

      this.metasSeleccionadas.push($event.target.value);

    } else {

      const index = this.metasSeleccionadas.findIndex(x => x === $event.target.value);

      this.metasSeleccionadas.splice(index, 1);

    }
    console.log(this.metasSeleccionadas)
  }



 

  public submitPerfil() {
  
    if(this.perfilForm.valid){
      this.emprendimiento = Object.assign(this.perfilForm.value, {emprendimiento_id:this.id,comentario_meta:'hola',publicado:false});
      if(this.archivo.length)
      this.emprendimiento.logo = this.archivo[0]
    this.emprendimientoServices.edit_emprendimiento(this.emprendimiento).subscribe((response:Emprendimiento)=>{
     console.log('')  
      this.router.navigate(["/init/Perfil"]) 
    }) 
    }
    }

    public submitContacto() {
    
      if(this.contactoForm.valid){
        this.contactos = Object.assign(this.contactoForm.value,{emprendimiento_id:this.id});
      this.emprendimientoServices.edit_contacto(this.contactos).subscribe(response=>{
       console.log('creado')   
      }) 
      }
      }

    
}






