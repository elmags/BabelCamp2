import { Alumno } from "./Alumno";
import { Curso } from "./Curso";
import { MatriculaPK } from "./MatriculaPK";

export class Matricula {
    pk : MatriculaPK;
	nota : number;
    alumno : Alumno;
    curso : Curso;
}