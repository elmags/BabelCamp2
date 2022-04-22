package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor				// Se crea un contructor sin parametros
@AllArgsConstructor				// Se crea un contructor con todos los parametros
@Setter							// Se crean los setters de todos los parametros
@Getter							// Se crean los getters de todos los parametros
@Entity							// Indica que es una entidad
@Table(name = "productos")		// Nombre de la tabla a la que está asociada esta entidad

public class Producto {
	
	@Id		// Indica que idProducto es la clave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Indica que es una clave primaria auto-generada
	private int idProducto;
	private String nombre;
	@Column(name = "seccion") 	// Indica que el atributo seccion está asociado a la columna seccion
	private String seccion;
	private double precio;
	private int stock;
}
