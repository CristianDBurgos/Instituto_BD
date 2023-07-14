package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioConsultasExtras {
	Conexion conexion = new Conexion();
	Connection cn = null;
	Statement stm = null;
	ResultSet rs = null;

	// Listas para almacenar los resultados de cada consulta
	List<String> consulta1 = new ArrayList<>();
	List<String> consulta2 = new ArrayList<>();
	List<String> consulta3 = new ArrayList<>();
	List<String> consulta4 = new ArrayList<>();

	// #Listado de alumnos del curso Programación
	public void setConsulta1() {
		consulta1.clear(); // Limpiar la lista de alumnos
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT A.idAlumno, A.alumnoNombre, A.alumnoApellido\r\n"
					+ "FROM instituto2.Alumnos A\r\n"
					+ "INNER JOIN Alumno_Curso AC ON A.idAlumno = AC.idAlumno\r\n"
					+ "WHERE AC.idCurso = 1;");

			consulta1.add("ID || ALUMNO"); // Agregar el encabezado

			while (rs.next()) {
				int idAlumno = rs.getInt(1);
				String alumnoNombre = rs.getString(2);
				String alumnoApellido = rs.getString(3);
				consulta1.add(idAlumno + "-" + alumnoNombre + "-" + alumnoApellido);
				System.out.println(idAlumno + "-" + alumnoNombre + "-" + alumnoApellido);
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

	public List<String> getConsulta1() {
		return consulta1;
	}

	// #Cantidad de cursos del alumno Id 13
	public void setConsulta2() {
		consulta2.clear(); // Limpiar la lista de alumnos
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT A.alumnoNombre, A.alumnoApellido, COUNT(*) AS cantidad_cursos\r\n"
					+ "FROM instituto2.Alumno_Curso AC\r\n"
					+ "INNER JOIN Alumnos A ON A.idAlumno = AC.idAlumno\r\n"
					+ "WHERE AC.idAlumno = 13;");

			consulta2.add("ALUMNO || CANTIDAD DE CURSOS"); // Agregar el encabezado

			while (rs.next()) {
				String alumnoNombre = rs.getString(1);
				String alumnoApellido = rs.getString(2);
				int cantidad_cursos = rs.getInt(3);
				consulta2.add(alumnoNombre + "  " + alumnoApellido + " - " + cantidad_cursos);
				System.out.println(alumnoNombre + "  " + alumnoApellido + " - " + cantidad_cursos);
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

	public List<String> getConsulta2() {
		return consulta2;
	}

	// #Cursos del alumno con Id 15
	public void setConsulta3() {
		consulta3.clear(); // Limpiar la lista de alumnos
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT C.idCurso, C.cursoNombre, A.alumnoApellido\r\n"
					+ "FROM instituto2.Cursos C\r\n"
					+ "INNER JOIN Alumno_Curso AC ON C.idCurso = AC.idCurso\r\n"
					+ "INNER JOIN Alumnos A ON AC.idAlumno = A.idAlumno\r\n"
					+ "WHERE AC.idAlumno = 15;");

			consulta3.add("ID CURSO || CURSO || ALUMNO"); // Agregar el encabezado

			while (rs.next()) {
				int idCurso = rs.getInt(1);
				String cursoNombre = rs.getString(2);
				String alumnoApellido = rs.getString(3);

				consulta3.add(idCurso + " - " + cursoNombre + " - " + alumnoApellido);
				System.out.println(idCurso + " - " + cursoNombre + " - " + alumnoApellido);
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

	public List<String> getConsulta3() {
		return consulta3;
	}

	// #Curso del docente con DNI 95959595
	public void setConsulta4() {
		consulta4.clear(); // Limpiar la lista de alumnos
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT C.idCurso, C.cursoNombre, D.docenteApellido\r\n"
					+ "FROM instituto2.Cursos C\r\n"
					+ "INNER JOIN Docente_Curso DC ON C.idCurso = DC.idCurso\r\n"
					+ "INNER JOIN Docentes D ON DC.idDocente = D.idDocente\r\n"
					+ "WHERE D.docenteDNI = '95959595';");

			consulta4.add("ID CURSO || CURSO || DOCENTE"); // Agregar el encabezado

			while (rs.next()) {
				int idCurso = rs.getInt(1);
				String cursoNombre = rs.getString(2);
				String docenteApellido = rs.getString(3);

				consulta4.add(idCurso + " - " + cursoNombre + " - " + docenteApellido);
				System.out.println(idCurso + " - " + cursoNombre + " - " + docenteApellido);
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

	public List<String> getConsulta4() {
		return consulta4;
	}
}
