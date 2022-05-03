package converters;

import dtos.AlumnoDto;
import dtos.CursoDto;
import model.Alumno;
import model.Curso;

public interface ConversorEntityDto {
	CursoDto cursoToDto(Curso curso);
	Curso dtoToCurso(CursoDto dto);
	AlumnoDto alumnoToDto(Alumno alumno);
	Alumno dtoToAlumno(AlumnoDto dto);
}
