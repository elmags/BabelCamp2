import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import { Alumno } from '../model/Alumno';
import { Curso } from '../model/Curso';
import { Matricula } from '../model/Matricula';

@Injectable({
  providedIn: 'root'
})
export class FormacionService {

  // urlA : string = "Alumnos";
  // urlC : string = "Cursos";
  // urlAC : string = "AlumnosCurso";
  // urlCA : string = "CursosAlumno";
  // urlMatricular : string = "Matricular";
  // urlAltaCurso : string = "AltaCurso";
  // urlAltaAlumno : string = "AltaAlumno";
  // urlMatriculas : string = "Matriculas";

  urlA : string = "http://localhost:8080/15_formacion_matriculas/Alumnos";
  urlC : string = "http://localhost:8080/15_formacion_matriculas/Cursos";
  urlAC : string = "http://localhost:8080/15_formacion_matriculas/AlumnosCurso";
  urlCA : string = "http://localhost:8080/15_formacion_matriculas/CursosAlumno";
  urlMatricular : string = "http://localhost:8080/15_formacion_matriculas/Matricular";
  urlAltaCurso : string = "http://localhost:8080/15_formacion_matriculas/AltaCurso";
  urlAltaAlumno : string = "http://localhost:8080/15_formacion_matriculas/AltaAlumno";
  urlMatriculas : string = "http://localhost:8080/15_formacion_matriculas/Matriculas";

  constructor(private _httpClient : HttpClient) { }

  public alumnos() {
    return this._httpClient.get<Alumno[]>(this.urlA);
  }

  public cursos() {
    return this._httpClient.get<Curso[]>(this.urlC);
  }

  public alumnosCurso(nombre : string) {
    return this._httpClient.get<Alumno[]>(this.urlAC, {params : {"nombre" : nombre}});
  }

  public cursosAlumno(usuario : string) {
    return this._httpClient.get<Curso[]>(this.urlCA, {params : {"usuario" : usuario}})
  }

  public matricular(usuario : string, idCurso : number) {
    const headers = new HttpHeaders()
      .set('content-type', 'application/x-www-form-urlencoded')
      .set('Access-Control-Allow-Origin', '*');

    let body = "usuario=" + usuario + "&idCurso=" + idCurso;
    return this._httpClient.post<any>(this.urlMatricular, body, { 'headers': headers }).pipe(catchError(this.manejarError));
  }

  public altaCurso(idCurso : number, nombre : string, duracion : number, fechaInicio : Date, precio : number) {
    const headers = new HttpHeaders()
      .set('content-type', 'application/x-www-form-urlencoded')
      .set('Access-Control-Allow-Origin', '*');

    let body = "idCurso=" + idCurso + "&nombre=" + nombre + "&duracion=" + duracion + "&fechaInicio=" + fechaInicio + "&precio=" + precio;
    return this._httpClient.post<any>(this.urlAltaCurso, body, { 'headers': headers }).pipe(catchError(this.manejarError));
  }

  public altaAlumno(usuario : string, password : string, nombre : string, email : string, edad : number) {
    const headers = new HttpHeaders()
      .set('content-type', 'application/x-www-form-urlencoded')
      .set('Access-Control-Allow-Origin', '*');

    let body = "usuario=" + usuario + "&password=" + password + "&nombre=" + nombre + "&email=" + email + "&edad=" + edad;
    return this._httpClient.post<any>(this.urlAltaAlumno, body, { 'headers': headers }).pipe(catchError(this.manejarError));
  }

  // public mostrarMatriculas(fechaInicio : Date, fechaFin : Date) {
  //   return this._httpClient.get<Matricula[]>(this.urlMatriculas, {params : {"fechaInicio" : fechaInicio, "fechaFin" : fechaFin}});
  // }
  /**
   * Metodo que maneja los posibles errores de las llamadas al servicio rest
   * @param error 
   * @returns 
   */
   private manejarError(e: HttpErrorResponse){
    let mensajeError = ''
    if (e.error instanceof ErrorEvent) {
      mensajeError = 'A ocurrido un error:' + e.error
    } else {
      mensajeError = `El servicio Rest retorno: Status: ${e.status}, ` +
            `Body: ${e.error}`
    }
    console.error(mensajeError)
    return throwError(() => new Error(mensajeError));
  }
}
