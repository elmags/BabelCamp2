package dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MatriculaDto {
	private double nota;
	@JsonAlias(value = "alumno")
//	@JsonProperty(value = "alumno") 	// Si no funciona JsonAlias
	private AlumnoDto alumnoDto;
	@JsonAlias(value = "curso")
	private CursoDto cursoDto;
}
