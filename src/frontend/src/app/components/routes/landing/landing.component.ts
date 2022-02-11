import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import L = require('leaflet');
import { forkJoin } from 'rxjs';
import { Emprendimiento } from 'src/app/interfaces/emprendimiento';
import { Meta } from 'src/app/interfaces/meta';
import { EmprendimientoService } from '../../auth/services/emprendimiento.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {

  inverted: Array<string> = ["assets/metas/S_SDG_inverted_WEB-17 (1).png", "assets/metas/S_SDG_inverted_WEB-17 (2).png", "assets/metas/S_SDG_inverted_WEB-17 (3).png", "assets/metas/S_SDG_inverted_WEB-17 (4).png", "assets/metas/S_SDG_inverted_WEB-17 (5).png", "assets/metas/S_SDG_inverted_WEB-17 (6).png", "assets/metas/S_SDG_inverted_WEB-17 (7).png", "assets/metas/S_SDG_inverted_WEB-17 (8).png", "assets/metas/S_SDG_inverted_WEB-17 (9).png", "assets/metas/S_SDG_inverted_WEB-17 (10).png", "assets/metas/S_SDG_inverted_WEB-17 (11).png", "assets/metas/S_SDG_inverted_WEB-17 (12).png", "assets/metas/S_SDG_inverted_WEB-17 (13).png", "assets/metas/S_SDG_inverted_WEB-17 (14).png", "assets/metas/S_SDG_inverted_WEB-17 (15).png", "assets/metas/S_SDG_inverted_WEB-17 (16).png", "assets/metas/S_SDG_inverted_WEB-17 (17).png"]
  id: number;
  data: Emprendimiento;
  porcentaje: number;
  porcentajeM: number;
  metas: any;
  imagenFondo: HTMLElement;
  meta: any = [];
  mymap: any;
  currentSection: string;

  constructor(
    private _emprendService: EmprendimientoService,
    private _route: ActivatedRoute,
  ) { }


  getId() {
    this.id = parseInt(this._route.snapshot.paramMap.get('id')) || 0;
    this._route.params.subscribe(params => {
      this.scrollTo(this.currentSection)
    })
  }
  scrollTo(section: string) {
    document.querySelector('#' + section)
      .scrollIntoView();
  }

  async mapBox() {
    this.mymap = L.map('mapa').setView([this.data.contacto.latitud, this.data.contacto.longitud], 17);
    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
      maxZoom: 25,
      attribution: 'Datos del mapa de &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a>, ' + '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' + 'Imágenes © <a href="https://www.mapbox.com/">Mapbox</a>',
      id: 'mapbox/streets-v11'
    }).addTo(this.mymap);
  }

  findIndiceMeta(meta: Meta) {
    if (this.metas.length) {
      let auth = this.metas.findIndex((m: Meta) =>
        m.id === meta.id
      )
      return auth
    }
  }

  getPorcentaje(data: Emprendimiento) {
    let entero = (data.count_jovenes / data.count_empleados) * 100;
    this.porcentaje = Math.round(entero);
    let enteroM = (data.count_mujeres / data.count_empleados) * 100;
    this.porcentajeM = Math.round(enteroM);
  }


  ngOnInit(): void {
    this.getId();
    forkJoin([
      this._emprendService.lista_metas(),
      this._emprendService.get_emprendimiento(this.id)
    ]).subscribe(([metas, data
    ]) => {
      this.data = data;
      console.log(data)
      this.getPorcentaje(data);
      this.imagenFondo = document.getElementById("imagenFondo");
      this.imagenFondo.style.backgroundImage = 'url("' + this.data?.portada + '")';
      this.metas = metas;
      this.meta = data.metas || [];
      this.meta.sort((a: any, b: any) => a.id - b.id);
      this.meta.forEach((m: any) => {
        m.image = this.inverted[this.findIndiceMeta(m)];
      });
      this.mapBox();
    });
  }

}
