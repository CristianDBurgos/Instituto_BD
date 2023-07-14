package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicioDelete {

	private List<String> lista;

	public void eliminar(int idAlumno) {
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps = null;
		Statement stm = null;

		try {
			// Establece una conexión con la base de datos
			cn = conexion.conectar();

			// Prepara la sentencia SQL para eliminar un alumno de la tabla 'alumnos'
			ps = cn.prepareStatement("DELETE FROM `instituto2`.`alumnos` WHERE `idAlumno` = ?;");
			ps.setInt(1, idAlumno);

			// Ejecuta la sentencia SQL de eliminación
			int retorno = ps.executeUpdate();

			if (retorno > 0) {
				System.out.println("Eliminación correcta");
			} else {
				System.out.println("Fallo la eliminación");
			}

			lista = new ArrayList<>();

			// Crea una declaración para ejecutar una consulta SQL
			stm = cn.createStatement();

			// Ejecuta la consulta SQL para obtener todos los alumnos de la tabla 'alumnos'
			ResultSet rs = stm.executeQuery("SELECT * FROM instituto2.Alumnos;");

			while (rs.next()) {
				// Obtiene los datos de cada fila de la tabla 'alumnos'
				int idAlumnoResult = rs.getInt("idAlumno");
				String alumnoNombre = rs.getString("alumnoNombre");
				String alumnoApellido = rs.getString("alumnoApellido");
				LocalDate alumnoFechaIngreso = rs.getObject("alumnoFechaDeIngreso", LocalDate.class);
				String alumnoDNI = rs.getString("alumnoDNI");

				// Agrega los datos del alumno a la lista
				lista.add(alumnoNombre + " " + alumnoApellido + " " + alumnoFechaIngreso + " " + alumnoDNI);

				System.out.println(idAlumnoResult + " - " + alumnoNombre + " - " + alumnoApellido + " - " + alumnoFechaIngreso + " - " + alumnoDNI);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public List<String> getListadoActualizado() {
		return lista;
	}
}
