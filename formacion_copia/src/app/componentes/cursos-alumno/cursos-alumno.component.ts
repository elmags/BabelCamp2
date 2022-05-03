import { Component, OnInit } from '@angular/core';
import { Alumno } from 'src/app/model/Alumno';
import { Curso } from 'src/app/model/Curso';
import { FormacionService } from 'src/app/service/formacion.service';

@Component({
  selector: 'app-cursos-alumno',
  templateUrl: './cursos-alumno.component.html',
  styleUrls: ['./cursos-alumno.component.css']
})
export class CursosAlumnoComponent implements OnInit {

  alumnos : Alumno[];
  _cursosAlumno : Curso[];
  usuario : string;
  
  constructor(private _formacionService : FormacionService) { this.mostrarAlumnos(); }

  ngOnInit(): void {
  }

  public mostrarAlumnos() {
    this._formacionService.alumnos().subscribe(data => this.alumnos = data);
  }

  public busquedaCA() {
    this._formacionService.cursosAlumno(this.usuario).subscribe(data => this._cursosAlumno = data)
  }
}
