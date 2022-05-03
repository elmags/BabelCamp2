import { Component, OnInit } from '@angular/core';
import { FormacionService } from 'src/app/service/formacion.service';

@Component({
  selector: 'app-alta-alumno',
  templateUrl: './alta-alumno.component.html',
  styleUrls: ['./alta-alumno.component.css']
})
export class AltaAlumnoComponent implements OnInit {

  error: number | null;

  // variables para los errores posibles
  error1: boolean = false;
  error2: boolean = false;
  error3: boolean = false;
  error5: boolean = false;
  error6: boolean = false;

  // Datos que recogemos
  usuario : string;
  password : string;
  nombre : string;
  email : string;
  edad : number;

  constructor(private _formacionService : FormacionService) { }

  ngOnInit(): void {
  }

  private alta() {
    this.mostrarError();
    if (this.error == 0) {
      this._formacionService.altaAlumno(this.usuario, this.password, this.nombre, this.email, this.edad)
    }
  }

  public administrarErrores() {
    this.alta();
    if (this.error == 1) this.error1 = true;
  }

   private mostrarError() {
    if (this.usuario == "" || this.password == "" || this.nombre == "" || this.email == "") this.error = 1;
    else this.error = 0;
  }

  public vaciar() {
    this.usuario = "";
    this.password = "";
    this.nombre = "";
    this.email = "";
    this.edad = 0;
  }
}
