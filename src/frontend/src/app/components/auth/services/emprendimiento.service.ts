import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { CardHome } from 'src/app/interfaces/card-home';
import { CardHomeEmp } from 'src/app/interfaces/card-home-emp';
import { Certificaciones } from 'src/app/interfaces/certificaciones';
import { Contacto } from 'src/app/interfaces/contacto';
import { Emprendimiento } from 'src/app/interfaces/emprendimiento';
import { Historia } from 'src/app/interfaces/historia';
import { Meta } from 'src/app/interfaces/meta';
import { Proveedor } from 'src/app/interfaces/proveedor';
import { Punto } from 'src/app/interfaces/punto';
import { Tecnica } from 'src/app/interfaces/tecnica';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmprendimientoService {

  constructor(public http: HttpClient) {
  }
  public create_user_emprendimiento(emprendimiento: any) {
    return this.http.post(environment.apiEndpoint + '/user-emprendedor/', emprendimiento)
  }
  public listado_user_emprendimiento() {
    return this.http.get(environment.apiEndpoint + '/user-emprendedor/')
  }

  public listado_emprendimientos() {
    return this.http.get(environment.apiEndpoint + '/emprendimientos/');
  }


  public get_user_emprendimiento(idUserEmprendimiento: number) {
    return this.http.get(environment.apiEndpoint + '/user-emprendedor/' + idUserEmprendimiento)
  }


  public get_user_cards_ptoventa() {
    return this.http.get(environment.apiEndpoint + '/emprendimientos/cards_puntoventa');
  }

  public get_user_cards_proveedores(){
    return this.http.get(environment.apiEndpoint + '/emprendimientos/cards_proveedores');
  }

  public get_counts(){
    return this.http.get(environment.apiEndpoint + '/emprendimientos/counts');
  }



  public findCardsHome(): Observable<Array<CardHome>> {
    return this.http.get<Array<CardHome>>(environment.apiEndpoint + '/emprendimientos/cards')
  }
  public get_user_puntodeventa(idUserPuntodeventa: number) {
    return this.http.get(environment.apiEndpoint + '/puntodeventa/' + idUserPuntodeventa)
  }
  public get_emprendimiento(idEmprendimiento: number): Observable<Emprendimiento> {
    return this.http.get<Emprendimiento>(environment.apiEndpoint + '/emprendimientos/' + idEmprendimiento)
  }
  public get_contacto(idcontacto: number): Observable<Contacto> {
    return this.http.get<Contacto>(environment.apiEndpoint + '/contactos/' + idcontacto)
  }
  public get_historia(idhistoria: number): Observable<Historia> {
    return this.http.get<Historia>(environment.apiEndpoint + '/informacion/historia/' + idhistoria)
  }
  public get_certificado(idcertificado: number): Observable<Certificaciones> {
    return this.http.get<Certificaciones>(environment.apiEndpoint + '/certificados/' + idcertificado)
  }
  public get_tecnica(idtecnica: number): Observable<Tecnica> {
    return this.http.get<Tecnica>(environment.apiEndpoint + '/informacion/tecnica/' + idtecnica)
  }
  public get_puntodeventa(idpunto: number): Observable<Punto> {
    return this.http.get<Punto>(environment.apiEndpoint + '/puntoventa/' + idpunto)
  }
  public get_proveedor(idproveedor: number): Observable<Proveedor> {
    return this.http.get<Proveedor>(environment.apiEndpoint + '/proveedor/' + idproveedor)
  }
  public get_meta(idmeta: number): Observable<Meta> {
    return this.http.get<Meta>(environment.apiEndpoint + '/emprendimiento-meta/' + idmeta)
  }

  
 
  public lista_materiales() {
    return this.http.get(environment.apiEndpoint + '/materiales/');
  }

  public lista_metas() {
    return this.http.get(environment.apiEndpoint + '/metas/');
  }

  public findCardsHomeE(): Observable<Array<CardHomeEmp>> {
    return this.http.get<Array<CardHomeEmp>>(environment.apiEndpoint + '/emprendimientos/cards_puntoventa')
  }


  public create_emprendimiento(emprendimiento: Emprendimiento) {
    return this.http.post<Emprendimiento>(environment.apiEndpoint + '/emprendimientos', emprendimiento)
  }
  
  public create_contacto(contacto: Contacto) {
    return this.http.post(environment.apiEndpoint + '/contactos', contacto)
  }
  public create_historia(historias: Historia) {
    return this.http.post(environment.apiEndpoint + '/informacion/historia', historias)
  }

  public create_certificaciones(certificacion: Certificaciones) {
    return this.http.post(environment.apiEndpoint + '/certificados/', certificacion)
  }


  public create_tecnica(tecnica: Tecnica) {
    return this.http.post(environment.apiEndpoint + '/informacion/tecnica', tecnica);
  }

  public create_puntodeventa(puntodeventa: Punto) {
    return this.http.post(environment.apiEndpoint + '/puntoventa/', puntodeventa);
  }
  public create_proveedor(proveedor: Proveedor) {
    return this.http.post(environment.apiEndpoint + '/proveedor/', proveedor);
  }


  public create_meta(meta: { metas: Array<number>, id_emprendimiento: number }) {
    return this.http.post(environment.apiEndpoint + '/emprendimiento-meta', meta); //objeto meta
  }





   public create_puntoventaE(puntoventa: CardHomeEmp) {
     return this.http.post(environment.apiEndpoint + '/emprendimientos/cards_puntoventa', puntoventa)
   }

  /************   EDITAR  ************************* */

  public edit_contacto(contacto: Contacto) {
    return this.http.put(environment.apiEndpoint + '/contactos/' + contacto.id, contacto);
  }
  public edit_emprendimiento(emprendimientos: Emprendimiento) {
    return this.http.put<Emprendimiento>(environment.apiEndpoint + '/emprendimientos/' + emprendimientos.id, emprendimientos);
  }
  public edit_historia(historias: Historia) {
    return this.http.put(environment.apiEndpoint + '/informacion/historia/' + historias.id, historias);
  }
  public edit_certificaciones(certificacion: Certificaciones) {

    return this.http.put<Certificaciones>(environment.apiEndpoint + '/certificados/', certificacion); //ESTUDIAR.
  }
  public edit_tecnica(tecnica: Tecnica) {
    return this.http.put(environment.apiEndpoint + '/informacion/tecnica/' + tecnica.id, tecnica);
  }
  public edit_puntodeventa(puntodeventa: Punto) {
    return this.http.put(environment.apiEndpoint + '/puntoventa/' + puntodeventa.id, puntodeventa); //estudiar
  }
  public edit_proveedor(proveedor: Proveedor) {
    return this.http.put(environment.apiEndpoint + '/proveedor/'+ proveedor.id, proveedor);
  }
  public edit_meta(meta: Meta) {
    return this.http.put(environment.apiEndpoint + '/emprendimiento-meta/', meta)
  }



  public edit_publicar(id_emp : any){
    let headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');

    return this.http.put(environment.apiEndpoint + '/emprendimientos/' + id_emp + '/publicado', null , { headers, responseType: 'text' });
  }




  public delete_Emprendimiento(id: any) {
    return this.http.delete(environment.apiEndpoint + '/emprendimientos/' + id);
  }

  
  public delete_punto_de_venta(id: any){
    return this.http.delete(environment.apiEndpoint + '/puntoventa/'+ id)
  }

  public delete_proveedor(id: any){
    return this.http.delete(environment.apiEndpoint + '/proveedor/'+ id)
  }


  public delete_imagen(link: any){
    return this.http.delete(environment.apiEndpoint + '/s3/delete/'+ link)
  }



  /************   EMPRENDIMIENTO ARTESANO  ************************* */

  public create_emprendimiento_user(emprendimiento: any) {


    return this.http.post<Emprendimiento>(environment.apiEndpoint + '/emprendimientos', emprendimiento)


  }

}

