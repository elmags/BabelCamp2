package converters;

import org.springframework.stereotype.Component;

import dtos.AlumnoDto;
import dtos.CursoDto;
import dtos.MatriculaDto;
import model.Alumno;
import model.Curso;
import model.Matricula;
import model.MatriculaPK;

@Component
public class ConversorEntityDtoImpl implements ConversorEntityDto {

	@Override
	public CursoDto cursoToDto(Curso curso) {
		return new CursoDto(curso.getIdCurso(), curso.getNombre(), curso.getDuracion(), curso.getFechaInicio(), curso.getPrecio());
	}

	@Override
	public Curso dtoToCurso(CursoDto dto) {
		return new Curso(dto.getIdCurso(), dto.getNombre(), dto.getDuracion(), dto.getFechaInicio(), dto.getPrecio());
	}

	@Override
	public AlumnoDto alumnoToDto(Alumno alumno) {
		return new AlumnoDto(alumno.getUsuario(), alumno.getPassword(), alumno.getNombre(), alumno.getEmail(), alumno.getEdad());
	}

	@Override
	public Alumno dtoToAlumno(AlumnoDto dto) {
		return new Alumno(dto.getUsuario(), dto.getPassword(), dto.getNombre(), dto.getEmail(), dto.getEdad());
	}

	@Override
	public MatriculaDto matriculaToDto(Matricula matricula) {
		return new MatriculaDto(matricula.getNota(), alumnoToDto(matricula.getAlumno()), cursoToDto(matricula.getCurso()));
	}

	@Override
	public Matricula dtoToMatricula(MatriculaDto dto) {
		return new Matricula(new MatriculaPK(dto.getAlumnoDto().getUsuario(), dto.getCursoDto().getIdCurso()), dto.getNota());
	}
}
