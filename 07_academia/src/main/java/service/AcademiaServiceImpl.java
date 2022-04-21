package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Alumno;

@Service
public class AcademiaServiceImpl implements AcademiaService {

	@Autowired
	JdbcTemplate template;
	
	@Override
	public String alta(Alumno alumno) {
		if(buscarAux(alumno) == null) {
			String sql = "INSERT INTO alumnos (nombre, curso, nota) VALUES (?, ?, ?)";
			template.update(sql, alumno.getNombre(), alumno.getCurso(), alumno.getNota());
			return "alta";
		} else return "existe";
	}

	@Override
	public List<Alumno> buscar(String curso) {
		String sql = "SELECT * FROM alumnos WHERE curso = ?";
		return template.query(sql, (rs, f) -> new Alumno(rs.getInt("idAlumno"), rs.getString("nombre"),
				rs.getString("curso"), rs.getDouble("nota")), curso);
	}
	
	@Override
	public List<String> cursos() {
		String sql = "SELECT distinct(curso) FROM alumnos";
		return template.query(sql, (rs, f) -> rs.getString("curso"));
	}
	
	private Alumno buscarAux(Alumno alumno) {
		String sql = "SELECT * FROM alumnos WHERE nombre = ?";
		List<Alumno> alumnos = template.query(sql, (rs, f) -> new Alumno(rs.getInt("idAlumno"), rs.getString("nombre"),
				rs.getString("curso"), rs.getDouble("nota")), alumno.getNombre());
		return alumnos.isEmpty() ? null : alumnos.get(0);
	}
}
