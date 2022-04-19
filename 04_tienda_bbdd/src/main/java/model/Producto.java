package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor		// Se crea un contructor sin parametros
@AllArgsConstructor		// Se crea un contructor con todos los parametros
@Setter					// Se crean los setters de todos los parametros
@Getter					// Se crean los getters de todos los parametros

public class Producto {
	private int idProducto;
	private String nombre;
	private String seccion;
	private double precio;
	private int stock;
}
