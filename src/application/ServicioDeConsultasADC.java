package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import java.util.ArrayList;

public class ServicioDeConsultasADC {
    Conexion conexion = new Conexion();
    Connection cn = null;
    Statement stm = null;
    ResultSet rs = null;
    
    // Listas para almacenar los resultados de cada consulta
    List<String> listaAlumnos = new ArrayList<>();
    List<String> listaDocentes = new ArrayList<>();
    List<String> listaCursos = new ArrayList<>();
    
    // Consulta de la lista de Alumnos
    public void setAlumnos() {
    	listaAlumnos.clear(); // Limpiar la lista de alumnos
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM instituto2.Alumnos");
            
            listaAlumnos.add("ID || NOMBRE || APELLIDO || FECHA DE REGISTRO || DNI"); // Agregar el encabezado

            while (rs.next()) {
                int idAlumno = rs.getInt(1);
                String alumnoNombre = rs.getString(2);
                String alumnoApellido = rs.getString(3);
                String alumnoFechaDeIngreso = rs.getString(4);
                String alumnoDNI = rs.getString(5);
                listaAlumnos.add(idAlumno + " - " + alumnoNombre + "  " + alumnoApellido + " - " + alumnoFechaDeIngreso + " - " + alumnoDNI);
                System.out.println(idAlumno + " - " + alumnoNombre + "  " + alumnoApellido + " - " + alumnoFechaDeIngreso + " - " + alumnoDNI);
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

    public List<String> getAlumnos() {
        return listaAlumnos;
    }

    // Consulta de la lista de Docentes
    public void setDocentes() {
    	listaDocentes.clear(); // Limpiar la lista de docentes
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM instituto2.Docentes");
            
            listaDocentes.add("ID || NOMBRE || APELLIDO || FECHA DE REGISTRO  || DNI"); // Agregar el encabezado

            while (rs.next()) {
                int idDocente = rs.getInt("idDocente");
                String docenteNombre = rs.getString("docenteNombre");
                String docenteApellido = rs.getString("docenteApellido");
                String docenteFechaDeRegistro = rs.getString("docenteFechaDeRegistro");
                String docenteDNI = rs.getString("docenteDNI");
                listaDocentes.add(idDocente + " - " + docenteNombre + "  " + docenteApellido + " - " + docenteFechaDeRegistro + " - " + docenteDNI);
                System.out.println(idDocente + " - " + docenteNombre + "  " + docenteApellido + " - " + docenteFechaDeRegistro + " - " + docenteDNI);
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

    public List<String> getDocentes() {
        return listaDocentes;
    }

    // Consulta de la lista de Cursos
    public void setCursos() {
    	listaCursos.clear(); // Limpiar la lista de cursos
        try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM instituto2.Cursos");
            
            listaCursos.add("ID || CURSO"); // Agregar el encabezado

            while (rs.next()) {
                int idCurso = rs.getInt(1);
                String cursoNombre = rs.getString(2);
                listaCursos.add(idCurso + " - " + cursoNombre);
                System.out.println(idCurso + " - " + cursoNombre);
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

    public List<String> getCursos() {
        return listaCursos;
    }
}
