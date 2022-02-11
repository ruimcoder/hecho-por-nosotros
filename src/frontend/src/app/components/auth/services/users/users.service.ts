import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class UsersService {

  apiUrl = environment.apiEndpoint;

  constructor(public http: HttpClient) {
  }

  findAll(token:string){
    return this.http.get( this.apiUrl + '/users');
  }

  find(uid:string){
    return this.http.get(this.apiUrl + '/users/' + uid);
  }

  setClaims(uid:string, claims:any){
    return this.http.post(this.apiUrl + '/users/claims/' + uid, claims);
  }


}
