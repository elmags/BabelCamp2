import { Component, OnInit } from '@angular/core';
import { Alumno } from 'src/app/model/Alumno';
import { Curso } from 'src/app/model/Curso';
import { FormacionService } from 'src/app/service/formacion.service';

@Component({
  selector: 'app-alumnos-curso',
  templateUrl: './alumnos-curso.component.html',
  styleUrls: ['./alumnos-curso.component.css']
})
export class AlumnosCursoComponent implements OnInit {

  cursos : Curso[];
  _alumnosCurso : Alumno[];
  nombre : string;

  constructor(private _formacionService : FormacionService) { this.mostrarCursos() }

  ngOnInit(): void {
  }

  public mostrarCursos() {
    this._formacionService.cursos().subscribe(data => this.cursos = data);
  }

  public busquedaAC() {
    this._formacionService.alumnosCurso(this.nombre).subscribe(data => this._alumnosCurso = data)
  }
}
