package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity							
@Table(name = "cursos")

/* Entidad Curso */
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCurso;
	private String nombre;
	private int duracion;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	private double precio;
	
	@OneToMany(mappedBy = "curso")
	private List<Matricula> matriculas;

	public Curso(String nombre, int duracion, Date fechaInicio, double precio) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.fechaInicio = fechaInicio;
		this.precio = precio;
	}

	public Curso(int idCurso, String nombre, int duracion, Date fechaInicio, double precio) {
		super();
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.duracion = duracion;
		this.fechaInicio = fechaInicio;
		this.precio = precio;
	}
}