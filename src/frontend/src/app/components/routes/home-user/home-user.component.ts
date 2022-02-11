import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { RouterModule } from '@angular/router';
import { CardHome } from 'src/app/interfaces/card-home';
import { EmprendimientoService } from '../../auth/services/emprendimiento.service';
import { MatTableModule } from '@angular/material/table';
import Swal from "sweetalert2";
import { Emprendimiento } from 'src/app/interfaces/emprendimiento';

export interface Data {
  logo: string,
  nombre: string,
  ubicacion: string,
  email: string,
  tipoempresa: string,
  publicado: string,
  accion: string
 
}


@Component({
  selector: 'app-home-user',
  templateUrl: './home-user.component.html',
  styleUrls: ['./home-user.component.scss']
})
export class HomeUserComponent implements OnInit {
  urlimg:string='';
  cardsContent: Array<CardHome>=[];

  displayedColumns: string[] = ['logo','nombre', 'ubicacion', 'email', 'tipoempresa','accion'];
  dataSource: MatTableDataSource<Data>;

  listado_Emprendedores: any;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  
  constructor(
    private emprendimientoService:EmprendimientoService
  //  private userEmprendimiento: UserEmprendedorService
  
    ) {

      this.ver_emprendimientos();
      this.conteo();
      this.muestra_emprendimientos();//invocar siempre cuando quiero interpolar en html.

     }

  ngOnInit(): void {

   this.emprendimientoService.findCardsHome().subscribe((cards: any)=>{
     this.cardsContent = cards;
    })


     this.ver_emprendimientos();

     this.emprendimientoService.findCardsHome().subscribe((response: any) => {

      // console.log('holaa');
      // console.log(response);

      this.listado_Emprendedores = response;
      this.dataSource = new MatTableDataSource<Data>(response);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
     

    }, (error: any) => {
      console.log(error);
      /*siempre crear error, para el endpoint */
    }

    );
  }

  ver_emprendimientos() {

    this.emprendimientoService.findCardsHome().subscribe((response: any) => {

     

      this.listado_Emprendedores = response;
      this.dataSource = new MatTableDataSource<Data>(response);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
     

    }, (error: any) => {
      console.log(error);
      /*siempre crear error, para el endpoint */
    }

    );

  }


  valor : Emprendimiento;
  public muestra_emprendimientos(){
    this.emprendimientoService.listado_emprendimientos().subscribe((response:any)=>{
        this.valor = response;
    
    })
  }

  eliminarEmprendimiento(id_emp: any){
    Swal.fire({
      title: '¿Estas seguro que desea eliminar este Emprendimiento?',
      text: "¡No podras revertir esto!",
      icon: 'warning',
      showDenyButton: true,
      confirmButtonText: `¡Sí, eliminar!`,
      denyButtonText: `Cancelar`,
    }).then((result) => {

      if (result.isConfirmed)

        this.emprendimientoService.delete_Emprendimiento(id_emp).subscribe(resp => {
    
          Swal.fire('¡Emprendimiento Eliminado!', '', 'success');

        },
          error => {
     
            Swal.fire('¡Hubo un error!', '', 'error');
          })
    },

    )
  }



  applyFilter(event: Event) {
    const filterVlue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterVlue.trim().toLocaleLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  
  contador:any;
  conteo(){
    this.emprendimientoService.get_counts().subscribe((response:any)=>{
       this.contador=response;
      
    })
  }


}

