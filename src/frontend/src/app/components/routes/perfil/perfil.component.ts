import { group } from '@angular/animations';
import { formatCurrency } from '@angular/common';
import { parseI18nMeta } from '@angular/compiler/src/render3/view/i18n/meta';
import { Component, Input, OnInit, resolveForwardRef } from '@angular/core';
import { FormGroup, FormControl, FormControlName, Validators, FormBuilder, EmailValidator, MaxLengthValidator, MaxValidator, FormArray } from '@angular/forms';

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
import Swal from 'sweetalert2';
import { TransitionCheckState } from '@angular/material/checkbox';
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import { Observable, throwError } from 'rxjs';


@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss'],
  providers: [AuthService]
})

export class PerfilComponent implements OnInit {
  imageError: string;
  isImageSaved: boolean;
  cardImageBase64: string;
  datos_emp: any;
  name = 'World';
  public id: number;

  checked = true;



  //lat:number;
  //lng:number;
  //zoom: number;
  //mapTypeId:string;


  admin: Boolean= false;
  generos: Array<string> = ['Femenino', 'Masculino', 'Otros']
  empresas: Array<string> = ['Artesano', 'Productor de agricultura o ganaderia', 'Micro o mediana empresa', 'Dueño de marca / comerciante']
  perteneces: Array<string> = ['Asociación', 'Fundación', 'Cooperativa', 'Organización']
  public loading: boolean;
  
  inverted: Array<any> = ["assets/S_SDG_inverted_WEB-17 (1).png", "assets/S_SDG_inverted_WEB-17 (2).png", "assets/S_SDG_inverted_WEB-17 (3).png", "assets/S_SDG_inverted_WEB-17 (4).png", "assets/S_SDG_inverted_WEB-17 (5).png", "assets/S_SDG_inverted_WEB-17 (6).png", "assets/S_SDG_inverted_WEB-17 (7).png", "assets/S_SDG_inverted_WEB-17 (8).png", "assets/S_SDG_inverted_WEB-17 (9).png", "assets/S_SDG_inverted_WEB-17 (10).png", "assets/S_SDG_inverted_WEB-17 (11).png", "assets/S_SDG_inverted_WEB-17 (12).png", "assets/S_SDG_inverted_WEB-17 (13).png", "assets/S_SDG_inverted_WEB-17 (14).png", "assets/S_SDG_inverted_WEB-17 (15).png", "assets/S_SDG_inverted_WEB-17 (16).png", "assets/S_SDG_inverted_WEB-17 (17).png"]


  tipos: Array<string> = ['Tienda', 'Feria', 'Mercado de Artesania', 'Casa', 'Plaza', 'Boutique', 'Otros']
  materiales: Array<string> = []
  selected: Array<{ imagen: string, selected: boolean }>
  metasSeleccionadas: Array<string> = [];
  public perfilForm: FormGroup
  public contactoForm: FormGroup
  public latitudc: any;
  public longitudc: any;
  public certificadoForm: FormGroup
  public tecnicaForm: FormGroup
  public puntoFormArray: FormArray
  public puntosForm: FormGroup
  public proveedorForm: FormGroup
  public historiaForm: FormGroup
  public metaForm: FormGroup

  public emprendimiento: Emprendimiento
  public contactos: Contacto
  public historias: Historia
  public certificado: any

  public tecnica: Tecnica
  public punto: Array<Punto>
  
  public proveedor: Array<Proveedor>
  public meta: any;
  public archivo: any = [];
  public foto: any = [];
  public archivoPortada: any = [];
  public fotoTecnica: any = [];
  public fotocertificado: any = [];

  public sugerencias: any = [];
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
  public valueRequiredPunto(puntoForm: FormGroup, value: string) {
    return puntoForm.controls[value].hasError('required') && (puntoForm.controls[value].dirty || puntoForm.controls[value].touched);
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
      this.perfilForm.controls.pertenece.reset()
      this.perfilForm.controls.pertenece.disable()
    }
    else {
      this.perfilForm.controls.pertenece.enable()
    }
  }
  bandera: string;
  valor: string;

  urlimg: string = '';
  cardsContent: Array<CardHome> = [];
  public provider = new OpenStreetMapProvider();

  constructor(private authSvc: AuthService, private router: Router, private emprendimientoServices: EmprendimientoService, private sanitizer: DomSanitizer, private activatedRoute: ActivatedRoute,
    private formbuilder: FormBuilder,

  ) {

  

    this.id = parseInt(this.activatedRoute.snapshot.paramMap.get('id')) || 0;
    this.oculta();
    this.listado_materiales();
    this.listado_metas();
    this.getHomeByAuthorities();
  }


  ngOnInit(): void {
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
      count_jovenes: new FormControl('', [Validators.required]),
      count_colaboracion: new FormControl('', [Validators.required]),
      count_jovenes_colaboracion: new FormControl('', [Validators.required]),
      count_mujeres_colaboracion: new FormControl('', [Validators.required]),

      asia: new FormControl(false, [Validators.required]),
      africa: new FormControl(false, [Validators.required]),
      norteamerica: new FormControl(false, [Validators.required]),
      sudamerica: new FormControl(false, [Validators.required]),
      antartida: new FormControl(false, [Validators.required]),
      europa: new FormControl(false, [Validators.required]),
      oceania: new FormControl(false, [Validators.required])

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
      texto: new FormControl('', [Validators.required, Validators.minLength(200), Validators.maxLength(1300)]),
      url_video: new FormControl('', [Validators.required]),

    });

    this.certificadoForm = new FormGroup({

    });

    this.tecnicaForm = new FormGroup({
      texto: new FormControl('', [Validators.required]),
      url_video: new FormControl('', [Validators.required]),

    });


    this.puntosForm = this.formbuilder.group({

      puntosFormArray: this.formbuilder.array([])

    })


    this.proveedorForm = this.formbuilder.group({
      proveedoresFormArray: this.formbuilder.array([]),
     

    })



    this.metaForm = new FormGroup({

      comentario_meta: new FormControl('', [Validators.required])
    });

    this.getHomeByAuthorities();


  }

  getHomeByAuthorities() {
    let autorities = JSON.parse(localStorage.getItem('hxn-workload-user-claims') || '{}');

    if (autorities["ROLE_SUPER"]) {
      this.admin = true;
     
    
    }

    else
    {
      console.log('el usuario no es admin!')
    }
    
  

  }

 




  create_puntoform() {

    let puntoForm = this.formbuilder.group({
     
      direccionSec: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      pais: new FormControl('', [Validators.required]),
      ciudad: new FormControl('', [Validators.required]),
      cp: new FormControl('', [Validators.required]),
      tipo: new FormControl('', [Validators.required]),
      web: new FormControl('', [Validators.required]),
      contacto: new FormControl('', [Validators.required]),
    })

    this.puntosFormArray.push(puntoForm)

  }


  get puntosFormArray() {
    return this.puntosForm.controls.puntosFormArray as FormArray;
  }



  /*************************************************** */
  create_proveedor() {
    let proveedorForm = this.formbuilder.group({
      nombre: new FormControl('', [Validators.required]),
      direccion: new FormControl('', [Validators.required]),
      pais: new FormControl('', [Validators.required]),
      ciudad: new FormControl('', [Validators.required]),
      cp: new FormControl('', [Validators.required]),
      especificar: new FormControl('', [Validators.required]),
    })

    this.proveedoresFormArray.push(proveedorForm)
  }
  get proveedoresFormArray() {
    return this.proveedorForm.controls.proveedoresFormArray as FormArray;
  }
  /************************************************************* */

  toggle(): void {
    console.log(this.checked);
    // do more stuff
  }

  ciudad: string;
  async ciudadChange() { //async espera busqueda.
    

    this.sugerencias = await this.provider.search({ query: this.ciudad });
    console.log(this.sugerencias);


  }


  lat: number = 10; lng: number = 8;
  selectSugerencia(sugerencia: any) {


    this.ciudad = sugerencia.label;
    this.lat = sugerencia.y;
    this.lng = sugerencia.x;

  }





  findDatosPerfil() {
    this.emprendimientoServices.get_emprendimiento(this.id).subscribe((emprendimiento: Emprendimiento) => {
      this.emprendimiento = emprendimiento;
      this.contactos = this.emprendimiento.contacto;
      this.historias = this.emprendimiento.historia;
      this.certificado = this.emprendimiento.certificados || [];
      this.tecnica = this.emprendimiento.tecnica;
      this.punto = this.emprendimiento.puntosDeVenta;
      this.proveedor = this.emprendimiento.proveedores;
      this.meta= this.emprendimiento.metas;

      this.perfilForm.patchValue(this.emprendimiento);
      this.metaForm.controls.comentario_meta.patchValue(this.emprendimiento.comentario_meta);

      if (this.contactos?.id) {
        this.contactoForm.patchValue(this.contactos)
        this.latitudc = this.emprendimiento.contacto.latitud;
        this.longitudc = this.emprendimiento.contacto.longitud;
      }
      if (this.historias?.id) {
        this.historiaForm.patchValue(this.historias);
        this.foto = this.historias.imagenes.map(i => { return { image: i, type: "foto" } });
        this.historiaForm.controls.url_video.patchValue((this.historias as any).urlVideo);
      }

      this.fotocertificado = (this.certificado as any).map((i: any) => { return { image: i, type: "certificado" } });

      if (this.tecnica?.id) {
        this.tecnicaForm.patchValue(this.tecnica);
        this.fotoTecnica = this.tecnica.imagenes.map(i => { return { image: i, type: "foto" } });
        this.tecnicaForm.controls.url_video.patchValue((this.tecnica as any).urlVideo);
      }

      this.create_puntoform();
       
      
      if (this.punto && this.punto.length) {

        this.punto.forEach((p, i: number) => {

          this.create_puntoform();

          this.puntosFormArray.at(i + 1).patchValue(p);

        })


      }

      this.create_proveedor();
      if (this.proveedor && this.proveedor.length) {
        this.proveedor.forEach((p, i: number) => {
          this.create_proveedor();
          this.proveedoresFormArray.at(i + 1).patchValue(p);
          p.materiales = p.materiales || [];
          p.materiales = this.listaMateriales.map((m: any) => {

            let material = p.materiales.find((n: any) => m.id === n.id)
           
            return Object.assign({},m,{selected: material ? true : false });
          })
       
        })
      }
      


      this.archivo.push({ image: this.emprendimiento.logo })
      this.changeEmpresa();
      this.archivoPortada.push({ image: this.emprendimiento.portada })


       
      if (this.meta && this.meta.length) {
        let variable_meta;
        
     
        this.meta.forEach((m:any)=>{
          variable_meta = this.listaMetas.find((meta:any)=> m.id === meta.id)
          if(variable_meta){
            variable_meta.selected=true;

          }
       
        })

      }

    })

  }


  findDatosmeta() {
    this.emprendimientoServices.get_meta(this.id).subscribe((meta: Meta) => {
      this.meta = meta;
      this.inverted.forEach((imagenMeta: Meta) => {

      })
      this.metaForm.patchValue(this.meta);

      

    })
  }




  /************************ ARCHIVO LOGO EMPRENDIMIENTO   ********************************** */
  capturaFile(event: any) {
    const archivocapturado = event.target.files[0]

    this.extraerBase64(archivocapturado).then((imagen: any) => {
      console.log(imagen)
      this.archivo = [];
      this.archivo.push(imagen)
    })
  }

  extraerBase64 = async ($event: any) => new Promise((resolve, reject) => {
    try {
      const unsafeImg = window.URL.createObjectURL($event);
      const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
      const reader = new FileReader();
      reader.readAsDataURL($event);
      reader.onload = () => {
        resolve({
          type: "logo",
          image: reader.result

        }

        );
      };
      reader.onerror = error => {
        resolveForwardRef({
          type: "logo",
          image: null

        });
      };

    }
    catch (e) {
      return null;
    }
  })

  /******************************************************************* */

  capturaPortada(event: any) {
    const archivocapturado = event.target.files[0]

    this.extraerBases64(archivocapturado).then((imagen: any) => {
      console.log(imagen)
      this.archivoPortada = [];
      this.archivoPortada.push(imagen)
    })
  }

  extraerBases64 = async ($event: any) => new Promise((resolve, reject) => {
    try {
      const unsafeImg = window.URL.createObjectURL($event);
      const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
      const reader = new FileReader();
      reader.readAsDataURL($event);
      reader.onload = () => {
        resolve({
          type: "portada",
          image: reader.result

        }

        );
      };
      reader.onerror = error => {
        resolveForwardRef({
          type: "portada",
          image: null

        });
      };

    }
    catch (e) {
      return null;
    }
  })


  capturaHistoria(event: any, i: number) {
    const archivocapturado = event.target.files[i];
    this.extraeBase64(archivocapturado).then((imagen: any) => {

      this.foto.push(imagen); //no poner nombre de arreglo estudiar json.
      console.log(imagen);
    })
  }

  extraeBase64 = async ($event: any) => new Promise((resolve, reject) => {
    try {
      const unsafeImg = window.URL.createObjectURL($event);
      const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
      const reader = new FileReader();
      reader.readAsDataURL($event);
      reader.onload = () => {
        resolve({
          type: "foto",
          image: reader.result

        }

        );
      };
      reader.onerror = error => {
        resolveForwardRef({
          type: "foto",
          image: null

        });
      };

    }
    catch (e) {
      return null;
    }
  })

  borrar_imagen(im: any) {

    let aux = im.slice(55);
   

    Swal.fire({
      title: '¿Estas seguro que desea eliminar esta Imagen?',
      text: "¡No podras revertir esto!",
      icon: 'warning',
      showDenyButton: true,
      confirmButtonText: `¡Sí, eliminar!`,
      denyButtonText: `Cancelar`,
    }).then((result) => {

      if (result.isConfirmed)

        this.emprendimientoServices.delete_imagen(aux).subscribe(resp => {

          Swal.fire('¡Imagen Eliminada!', '', 'success');


        },
          error => {

            Swal.fire('¡Imagen Eliminada!', '', 'success');
          })
    },

    )



  }




  capturaCertificado(event: any, i: number) {

    const archivocapturado = event.target.files[0];
    this.extraebase64(archivocapturado).then((imagen: any) => {
      console.log(imagen);
     
      if (this.fotocertificado.length > i) {

        this.fotocertificado.splice(i, 1, imagen);
      }//funcion que reemplaza al primer lugar.
      else {
        this.fotocertificado.push(imagen);
      }

    })
  }

  extraebase64 = async ($event: any) => new Promise((resolve, reject) => {
    try {
      const unsafeImg = window.URL.createObjectURL($event);
      const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
      const reader = new FileReader();
      reader.readAsDataURL($event);
      reader.onload = () => {
        resolve({
          type: "certificado",
          image: reader.result

        }

        );
      };
      reader.onerror = error => {
        resolveForwardRef({
          type: "certificado",
          image: null

        });
      };

    }
    catch (e) {
      return null;
    }
  })



  capturaTecnica(event: any, i: number) {
    const archivocapturado = event.target.files[i];
    this.extraBases64(archivocapturado).then((imagen: any) => {
      console.log(imagen)
      this.fotoTecnica.push(imagen)
    })
  }

  extraBases64 = async ($event: any) => new Promise((resolve, reject) => {
    try {
      const unsafeImg = window.URL.createObjectURL($event);
      const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
      const reader = new FileReader();
      reader.readAsDataURL($event);
      reader.onload = () => {
        resolve({
          type: "foto",
          image: reader.result

        }

        );
      };
      reader.onerror = error => {
        resolveForwardRef({
          type: "foto",
          image: null

        });
      };

    }
    catch (e) {
      return null;
    }
  })

  oculta() {
    this.valor = 'emprendimiento';

  }
  contacto() {
    this.valor = 'contacto';

  }
  historia() {
    this.valor = 'historia';

  }
  certificacionesobtenidas() {
    this.valor = 'certificacionesobtenidas';

  }
  tecnicadetrabajo() {
    this.valor = 'tecnicadetrabajo';

  }
  puntodeventa() {
    this.valor = 'puntodeventa';

  }
  nombredelproveedor() {
    this.valor = 'nombredelproveedor';

  }
  metadedesarrollo() {
    this.valor = 'metadedesarrollo';
  }

  onCheckImagen($event: any, i: number) {
    if ($event.target.checked) {

      this.metasSeleccionadas.push($event.target.value);

    } else {

      const index = this.metasSeleccionadas.findIndex(x => x === $event.target.value);

      this.metasSeleccionadas.splice(index, 1);

    }

  }

  public submitPerfil() {
    this.loading=true;
    
    if (this.perfilForm.valid) {
      this.emprendimiento = Object.assign(this.emprendimiento || {}, this.perfilForm.value, { comentario_meta: 'hola', publicado: false });
      if (this.archivo.length)
        this.emprendimiento.logo = this.archivo[0];
      this.emprendimiento.logo.type = "logo"; //estudiar

      if (this.archivoPortada.length)
        this.emprendimiento.portada = this.archivoPortada[0];
      this.emprendimiento.portada.type = "portada"; //estudiar

      console.log(this.emprendimiento);
      
      (this.id ? this.emprendimientoServices.edit_emprendimiento(this.emprendimiento) :
        this.emprendimientoServices.create_emprendimiento(this.emprendimiento)).subscribe((response: Emprendimiento) => {
          Swal.fire('¡El Emprendimiento fue creado exitosamente!', '', 'success');
          this.loading=false;
          console.log(response);
          setTimeout(() => {
            this.router.navigate(["/init/Perfil", response.id]).then(() => {
              window.location.reload();
            });
          }, 0);

        })

    }

  }


  public submitContacto() {
  
   this.loading=true;
   
    if (this.contactoForm.valid) {
      this.contactos = Object.assign(this.contactos || {}, this.contactoForm.value, { emprendimiento_id: this.id, latitud: this.lat, longitud: this.lng });
       
      

     

      (this.contactos?.id ? this.emprendimientoServices.edit_contacto(this.contactos) :
        this.emprendimientoServices.create_contacto(this.contactos)).subscribe(response => {
          Swal.fire('¡El Contacto fue creado exitosamente!', '', 'success');
          this.loading=false;
          console.log(this.contactos)
        })
    }
  }

  public submitHistoria() {
   this.loading= true;
    if (this.historiaForm.valid) {
      this.historias = Object.assign(this.historias || {}, this.historiaForm.value, { emprendimiento_id: this.id, tipo: "historia" }); //estudiar
      if (this.foto.length)
        this.historias.imagenes = this.foto;

      (this.historias?.id ? this.emprendimientoServices.edit_historia(this.historias) :
        this.emprendimientoServices.create_historia(this.historias)).subscribe(response => {
          Swal.fire('¡La Historia fue creada exitosamente!', '', 'success');
          this.loading=false;
          console.log(this.historias);
          console.log('creado');
        })
    }
  }




  public submitCertificado() {
    this.loading=true;
    if (this.fotocertificado.length) {
      this.certificado = Object.assign({}, { emprendimiento_id: this.id });
      this.certificado.emprendimiento_id = this.id;
      this.certificado.certificados = this.fotocertificado;
      console.log('veamos que tiene certificados');
      console.log(this.certificado);
      // this.certificado.certificados[0].type = "certificado";
      this.emprendimientoServices.edit_certificaciones(this.certificado).subscribe(response => {
        console.log(response);
        Swal.fire('¡El Certificado fue creado exitosamente!', '', 'success');
        this.loading=false;
        console.log('creado');
      })

    }

  }

  public submitTecnica() {
  this.loading=true;
    if (this.tecnicaForm.valid) {
      this.tecnica = Object.assign(this.tecnica || {}, this.tecnicaForm.value, { emprendimiento_id: this.id, tipo: "tecnica" });
      console.log(this.tecnica);
      if (this.fotoTecnica.length)
        this.tecnica.imagenes = this.fotoTecnica;

      (this.tecnica.id ? this.emprendimientoServices.edit_tecnica(this.tecnica) :
        this.emprendimientoServices.create_tecnica(this.tecnica)).subscribe(response => {
          Swal.fire('¡La tecnica fue creada exitosamente!', '', 'success');
          this.loading=false;

          console.log('creado')
        })
    }
  }


  public submitPunto(puntoForm: FormGroup, i: number) {
    this.loading=true;
   

    if (puntoForm.valid) {
      let punto = Object.assign(i > -1 ? this.punto[i] || {} : {} , puntoForm.value, { emprendimiento_id: this.id });
      console.log(this.punto);


      (punto?.id ? this.emprendimientoServices.edit_puntodeventa(punto) :
        this.emprendimientoServices.create_puntodeventa(punto)).subscribe(response => {
          Swal.fire('¡El Punto de venta fue creado exitosamente!', '', 'success');
          this.loading=false;
          console.log('creado')
        })
    }
  }

  guardaMaterial: any
  /**************************************************************************** */
  public submitProveedor(proveedorForm: FormGroup, i: number) {
    this.loading=true;
  
    let proveedor = Object.assign(i > -1 ? this.proveedor[i] || {} : {}, this.proveedorForm.value.proveedoresFormArray[i+1], { emprendimiento_id: this.id });
    let materiales:any = [];
   
    proveedor.materiales = proveedor.materiales || this.listaMateriales;
    proveedor.materiales.forEach((materia: any) => {
      if (materia.selected) {
        materiales.push(materia.id);
      }

    })
   proveedor.materiales = materiales;
 
    console.log(this.proveedor);
   (proveedor?.id ? this.emprendimientoServices.edit_proveedor(proveedor) :
     
    this.emprendimientoServices.create_proveedor(proveedor)).subscribe(response => {
      // Swal.fire('¡El Proveedor fue '+ proveedor?.id ? '¡Editado!': 'creado' + 'exitosamente!', '', 'success');
      Swal.fire('¡El Proveedor fue creado exitosamente!', '', 'success');
      this.loading=false;
      console.log('creado');
    })
  }


  listaMateriales: any;
  public listado_materiales() {

    this.emprendimientoServices.lista_materiales().subscribe(response => {
      

      if (this.id) {

        this.findDatosPerfil();
  
      }
  

      this.listaMateriales = response;
      this.listaMateriales.forEach((material: any) => {
        material.selected = false;
      })
    


    })


  }


 public publicar(){
   
        this.emprendimientoServices.edit_publicar(this.id).subscribe(response =>{
           
          Swal.fire('¡El Emprendimiento fue publicado!', '', 'success');
    
        })
    
      }

public submitMeta() {
  this.loading=true;
    let variable_meta: any;
    let meta : any; //estudiar
    // let metas: Array<number> = [];
    
    meta = Object.assign({} , this.metaForm.value, { id_emprendimiento: this.id });
    meta.metas = [];

    this.listaMetas.forEach((m: any) => { //recorre arreglo inverted d imagen
      if (m.selected) {
        meta.metas.push(m.id); //pusheo el indice.
      }

    })



    this.emprendimientoServices.edit_meta(meta).subscribe(response => {
        Swal.fire('¡La meta  fue creada exitosamente!', '', 'success');
        this.loading=false;
        console.log('creado');
      })
    }

  listaMetas: any;
  public listado_metas() {
    this.emprendimientoServices.lista_metas().subscribe(response => {
    
      this.listaMetas = response;

      this.listaMetas.forEach((metas: any) => {
        metas.selected = false;
      })
     
    })
  }








}





