import { Component, AfterViewInit } from '@angular/core';
import * as L from 'leaflet';
@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.scss']
})
export class MapaComponent implements AfterViewInit {

  private map: any;
  marker : any;
  private initMap(): void {
    this.map = L.map('map', {
      center: [-34.61315,-58.37723],
      zoom: 7
    });
   this.map.on(
    "click", (e:any) => {
    //  console.log(e.latlng.lat); // get the coordinates
      if(this.marker) this.map.removeLayer(this.marker);
      console.log(this.marker?.['_latlng']);
      // guardo en una variable coordenada = this.marker?.['_latlng']
      this.marker = L.marker([e.latlng.lat, e.latlng.lng]).addTo(this.map); // add the marker onclick
    }
   )
    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 5,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright%22%3EOpenStreetMap</a>'
    });
    tiles.addTo(this.map);
  }

  constructor() { }

  ngAfterViewInit(

    ): void {

      this.initMap();
      // this.makeCapitalMarkers();
    }

}
