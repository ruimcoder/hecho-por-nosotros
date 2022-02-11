import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CardHome } from 'src/app/interfaces/card-home';
import { CardHomeEmp } from 'src/app/interfaces/card-home-emp';
import { EmprendimientoService } from '../../auth/services/emprendimiento.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, FormGroup, Validators, } from '@angular/forms';
import swal from 'sweetalert2';
import { Punto } from 'src/app/interfaces/punto';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Proveedor } from 'src/app/interfaces/proveedor';
@Component({
  selector: 'app-home-emprendimiento',
  templateUrl: './home-emprendimiento.component.html',
  styleUrls: ['./home-emprendimiento.component.scss']
})
export class HomeEmprendimientoComponent implements OnInit {
  valor: string;
  urlimg: string = '';

  materiales: Array<string> = [];
  selected: Array<{ imagen: string, selected: boolean }>
  public proveedorForm: FormGroup


  @ViewChild('confirmationModal') confirmationModal!: TemplateRef<unknown>;
  router: any;
  public id: number
  public puntoventa: FormGroup
  public punto: Punto;
  public proveedor: Proveedor;
  
  tipos: Array<string> = ['Tienda', 'Feria', 'Mercado de Artesania', 'Casa', 'Plaza', 'Boutique', 'Otros'];

  public valueRequiredPunto(value: string) {
    return this.puntoventa.controls[value].hasError('required') && (this.puntoventa.controls[value].dirty || this.puntoventa.controls[value].touched);
  }

  constructor(
    private emprendimientoService: EmprendimientoService,
    private emprendimientoServices: EmprendimientoService,
    private modalService: NgbModal,
    private activatedRoute: ActivatedRoute) {

    this.listado_materiales();
    this.ver_emprendimientos();
    this.punto_ventas();
    this.get_proveedores();

  }



  ngOnInit(): void {



    this.puntoventa = new FormGroup({
      direccion: new FormControl('', [Validators.required]),
      direccionSec: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      pais: new FormControl('', [Validators.required]),
      ciudad: new FormControl('', [Validators.required]),
      cp: new FormControl('', [Validators.required]),
      tipo: new FormControl('', [Validators.required]),
      web: new FormControl('', [Validators.required]),
      contacto: new FormControl('', [Validators.required]),
    });


    this.proveedorForm = new FormGroup({
      nombre: new FormControl('', [Validators.required]),
      direccion: new FormControl('', [Validators.required]),
      pais: new FormControl('', [Validators.required]),
      ciudad: new FormControl('', [Validators.required]),
      cp: new FormControl('', [Validators.required]),
      materiales: new FormControl('', [Validators.required]),
      especificar: new FormControl('', [Validators.required]),

    });


    this.ver_emprendimientos();


  }

  puntodeventa() {
    this.valor = 'puntodeventa';

  }


  cardsContent: Array<CardHome> = [];

  ver_emprendimientos() {

    this.emprendimientoService.findCardsHome().subscribe((cards: Array<CardHome>) => {
      this.cardsContent = cards;
      console.log('el id de este emprendimiento artesano es:');

      console.log('los cards en artesano son:');
      console.log(this.cardsContent);
    })


  }



  nueva_tienda() {
   this.punto= null;
  }


  puntoc: any;
  public crear_punto(id_emp: any) {
  
    debugger

    // this.puntoc= Object.assign(this.puntoventa.value, {emprendimiento_id: id_emp})
    this.punto = Object.assign(this.punto || {}, this.puntoventa.value, { emprendimiento_id: id_emp });
    console.log(this.punto);

    (this.punto?.id ? this.emprendimientoServices.edit_puntodeventa(this.punto) :
      this.emprendimientoServices.create_puntodeventa(this.punto)).subscribe(response => {
        Swal.fire('¡El Punto de venta fue creado exitosamente!', '', 'success');
       
        console.log('creado')
      })

  }


  guardaMaterial: any
  /**************************************************************************** */
  public crear_proveedor(id_emp: any) {
   
    this.proveedor = Object.assign(this.proveedor || {}, this.proveedorForm.value, { emprendimiento_id: id_emp });
    this.proveedor.materiales = [];
    this.listaMateriales.forEach((materia: any) => {
      if (materia.selected) {
        this.proveedor.materiales.push(materia.id);
      }
    })
    console.log(this.proveedor);

    (this.proveedor.id? this.emprendimientoService.edit_proveedor(this.proveedor) :
    this.emprendimientoServices.create_proveedor(this.proveedor)).subscribe(response => {
      Swal.fire('¡El Proveedor fue creado exitosamente!', '', 'success');
      console.log('creado');
    })
  }

  listaMateriales: any;
  public listado_materiales() {
    this.emprendimientoServices.lista_materiales().subscribe(response => {
      console.log('la listita de materiales es:');
      this.listaMateriales = response;
      this.listaMateriales.forEach((material: any) => {
        material.selected = false;
      })
      console.log(this.listaMateriales);
    })
  }






  listar_puntos: any;
  listado_puntodeventa: Array<any> = [];
 
  punto_ventas() {
    this.emprendimientoServices.get_user_cards_ptoventa().subscribe((response: any) => {
      this.listar_puntos = response;
      this.listado_puntodeventa = response[0].puntos_de_venta;
      
      console.log('hola soy el punto de venta');
      console.log(this.listado_puntodeventa);
      console.log('id');
      console.log(this.listar_puntos);
    })
  }



agregar_proveedor(){
  this.reset_materiales();
   this.proveedor= null;
}
  
  iterar: Array<any> = [];
  listar_proveedor: any;
  listado_proveedores: Array<any> = [];
  get_proveedores() {
    this.emprendimientoServices.get_user_cards_proveedores().subscribe((response: any) => {

      console.log('hola soy el listar proveedor');
      console.log(this.listar_proveedor);
     

   this.listado_proveedores = response[0].proveedores;

      this.iterar = response[0].proveedores[0].materiales;
      console.log('hola soy el proveedor');
      console.log(this.listado_proveedores);
      console.log('hola soy el ARREGLO');
      console.log(this.iterar);

    })
  }

  editar_punto(puntoventas: any) {
    debugger
    this.puntoventa.patchValue(puntoventas);
    this.punto = puntoventas;

  }

  eliminar_punto(id_puntoventa: any) {

    debugger

    Swal.fire({
      title: '¿Estas seguro que desea eliminar este Punto de Venta?',
      text: "¡No podras revertir esto!",
      icon: 'warning',
      showDenyButton: true,
      confirmButtonText: `¡Sí, eliminar!`,
      denyButtonText: `Cancelar`,
    }).then((result) => {

      if (result.isConfirmed)

        this.emprendimientoService.delete_punto_de_venta(id_puntoventa).subscribe(resp => {

          Swal.fire('¡Punto de Venta Eliminado!', '', 'success');


        },
          error => {

            Swal.fire('¡Hubo un error!', '', 'error');
          })
    },

    )




  }


  reset_materiales(){
    this.listaMateriales.forEach(( m:any )=>{
      m.selected=false;
    })
  }


editar_proveedor(proveedor: any){
  this.reset_materiales();
  let variable_material: any; //siempre se asigna los tipos despues en TS. en todos lados.
  this.proveedorForm.patchValue(proveedor);
  this.proveedor= proveedor;
  
  this.proveedor.materiales.forEach((m:any) =>{
    debugger
    variable_material = this.listaMateriales.find((material: any) => m.id === material.id)
    if(variable_material)
    variable_material.selected= true;
  })
}

eliminar_proveedor(id_prov : any){

  debugger
  Swal.fire({
    title: '¿Estas seguro que desea eliminar este Proveedor?',
    text: "¡No podras revertir esto!",
    icon: 'warning',
    showDenyButton: true,
    confirmButtonText: `¡Sí, eliminar!`,
    denyButtonText: `Cancelar`,
  }).then((result) => {

    if (result.isConfirmed)

      this.emprendimientoService.delete_proveedor(id_prov).subscribe(resp => {

        Swal.fire('¡Proveedor Eliminado!', '', 'success');


      },
        error => {

          Swal.fire('¡Hubo un error!', '', 'error');
        })
  },

  )

   
}






}



