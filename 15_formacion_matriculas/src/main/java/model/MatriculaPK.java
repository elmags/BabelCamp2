package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
//@EqualsAndHashCode		Genera métodos equals y hash code
@Embeddable
public class MatriculaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String usuario;
	private int idCurso;
	
	@Override
	public int hashCode() {
		return Objects.hash(idCurso, usuario);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatriculaPK other = (MatriculaPK) obj;
		return idCurso == other.idCurso && Objects.equals(usuario, other.usuario);
	}	
}
