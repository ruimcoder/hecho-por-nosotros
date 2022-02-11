import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CardHome } from 'src/app/interfaces/card-home';
import { Emprendimiento } from 'src/app/interfaces/emprendimiento';
import { EmprendimientoService } from '../../auth/services/emprendimiento.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit{
  urlimg:string='';
  cardsContent: Array<CardHome>=[];
  public loading: boolean;
  id: number;
  
  constructor( private emprendimientoService:EmprendimientoService) { 
    this.conteo();
     this.muestra_emprendimientos();

  }

  

  ngOnInit(): void {
    this.loading=true;
    this.emprendimientoService.findCardsHome().subscribe((cards:Array<CardHome>)=>{
      this.cardsContent = cards;
   
      this.loading=false
      
    })
   
     
     
    }

    contador:any;
    conteo(){
      this.emprendimientoService.get_counts().subscribe((response:any)=>{
         this.contador=response;
       
      })
    }

    valor : Emprendimiento;
    public muestra_emprendimientos(){
      this.emprendimientoService.listado_emprendimientos().subscribe((response:any)=>{
          this.valor = response;
        
         
      })
    }



  }



