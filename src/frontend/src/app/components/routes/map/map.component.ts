import { Component, AfterViewInit, Input } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements AfterViewInit {

  lat: number;
  lng: number;

  @Input() set lat_lng([lat, lng]: any) {

    this.lat = lat;
    this.lng = lng;
    
    // this.initMap();
    this.setCenter();

  }

  

  marker: any;
  setCenter() {
    
    if (this.map) {
       
      
      if (!this.marker) {
        this.marker = L.marker([this.lat, this.lng])
        
        this.marker.addTo(this.map);

        
      }else{
        this.marker.setLatLng([this.lat, this.lng])
      }

      this.map.setView(new L.LatLng(this.lat,this.lng),5); //estudiar.
      // var view = this.map.getView();
      // view.setCenter([this.lng, this.lat]);
      // view.setZoom(8);
    }

  }

  private map: any;

  private initMap(): void {
    this.map = L.map('map', {
      center: [this.lat, this.lng],
      zoom: 5
    });
    L.control.scale().addTo(this.map);

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 5,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });
    tiles.addTo(this.map);
  }

  constructor() { }

  ngAfterViewInit(

  ): void {

    this.initMap();
    this.setCenter();

  }

}
