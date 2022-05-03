package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity							
@Table(name = "matriculas")
public class Matricula {
	
	@EmbeddedId
	private MatriculaPK pk;
	private double nota;

	@ManyToOne()
	@JoinColumn(name = "usuario", referencedColumnName = "usuario", insertable = false, updatable = false)
	private Alumno alumno;
	
	@ManyToOne()
	@JoinColumn(name = "idCurso", referencedColumnName = "idCurso", insertable = false, updatable = false)
	private Curso curso;
	
	public Matricula(MatriculaPK pk, double nota) {
		super();
		this.pk = pk;
		this.nota = nota;
	}

	public Matricula(double nota, Alumno alumno, Curso curso) {
		super();
		this.nota = nota;
		this.alumno = alumno;
		this.curso = curso;
	}
}
