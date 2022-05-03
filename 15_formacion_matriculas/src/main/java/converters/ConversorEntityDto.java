package converters;

import dtos.AlumnoDto;
import dtos.CursoDto;
import dtos.MatriculaDto;
import model.Alumno;
import model.Curso;
import model.Matricula;

public interface ConversorEntityDto {
	CursoDto cursoToDto(Curso curso);
	Curso dtoToCurso(CursoDto dto);
	AlumnoDto alumnoToDto(Alumno alumno);
	Alumno dtoToAlumno(AlumnoDto dto);
	MatriculaDto matriculaToDto(Matricula matricula);
	Matricula dtoToMatricula(MatriculaDto dto);
}
