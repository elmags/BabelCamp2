import { Component } from '@angular/core';
import { Curso } from './model/Curso';
import { Alumno } from './model/Alumno';
import { FormacionService } from './service/formacion.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor() { }
}
