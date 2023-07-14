package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioConsultaTextField {
	Conexion conexion = new Conexion();
	Connection cn = null;
	Statement stm = null;
	ResultSet rs = null;

	// Lista para almacenar el resultado de la consulta
	List<String> consultaTextField = new ArrayList<>();

	// #Datos de un alumno filtrado por DNI
	public void setConsultaPorDNI(int alumnoDNI) {
		consultaTextField.clear(); // Limpiar la lista
		try {
			cn = conexion.conectar();
			PreparedStatement stmt = cn.prepareStatement("SELECT A.alumnoNombre, A.alumnoApellido, A.alumnoFechaDeIngreso, C.cursoNombre " +
					"FROM instituto2.Alumnos A " +
					"INNER JOIN Alumno_Curso AC ON A.idAlumno = AC.idAlumno " +
					"INNER JOIN Cursos C ON AC.idCurso = C.idCurso " +
					"WHERE A.alumnoDNI = ?");

			stmt.setInt(1, alumnoDNI); // Establecer el valor del DNI

			rs = stmt.executeQuery();

			consultaTextField.add("ALUMNO || FECHA DE INGRESO || CURSO"); // Agregar el encabezado

			while (rs.next()) {
				String alumnoNombre = rs.getString(1);
				String alumnoApellido = rs.getString(2);
				String alumnoFechaDeIngreso = rs.getString(3);
				String cursoNombre = rs.getString(4);

				consultaTextField.add(alumnoNombre + "  " + alumnoApellido + " - " + alumnoFechaDeIngreso + " - " + cursoNombre);
				System.out.println(alumnoNombre + "  " + alumnoApellido + " - " + alumnoFechaDeIngreso + " - " + cursoNombre);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs!= null) {
					rs.close();
				}

				if (stm != null) {
					stm.close();
				}

				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public List<String> getConsultaPorDni() {
		return consultaTextField;
	}
}
