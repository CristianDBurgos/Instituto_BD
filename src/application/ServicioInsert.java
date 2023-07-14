package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicioInsert {

    private List<String> lista;

    public void insertar(String nombre, String apellido, LocalDate fechaIngreso, String dni) {
        Conexion conexion = new Conexion();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement stm = null;

        try {
            // Establece una conexión con la base de datos
            cn = conexion.conectar();

            // Prepara la sentencia SQL para insertar un nuevo alumno en la tabla 'alumnos'
            ps = cn.prepareStatement("INSERT INTO `instituto2`.`alumnos` (`idAlumno`, `alumnoNombre`, `alumnoApellido`, `alumnoFechaDeIngreso`, `alumnoDNI`) VALUES (DEFAULT, ?, ?, ?, ?);");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setObject(3, fechaIngreso);
            ps.setObject(4, dni);
            
            // Ejecuta la sentencia SQL de inserción
            int retorno = ps.executeUpdate();

            if (retorno > 0) {
                System.out.println("Insert correcto");
            } else {
                System.out.println("Fallo el insert");
            }

            lista = new ArrayList<>();

            // Crea una declaración para ejecutar una consulta SQL
            stm = cn.createStatement();
            
            // Ejecuta la consulta SQL para obtener todos los alumnos de la tabla 'alumnos'
            rs = stm.executeQuery("SELECT * FROM instituto2.Alumnos;");

            while (rs.next()) {
                // Obtiene los datos de cada fila de la tabla 'alumnos'
                int idAlumno = rs.getInt("idAlumno");
                String alumnoNombre = rs.getString("alumnoNombre");
                String alumnoApellido = rs.getString("alumnoApellido");
                LocalDate alumnoFechaIngreso = rs.getObject("alumnoFechaDeIngreso", LocalDate.class);
                String alumnoDNI = rs.getString("alumnoDNI");
                
                // Agrega los datos del alumno a la lista
                lista.add(idAlumno + "-" + alumnoNombre + "-" + alumnoApellido + "-" + alumnoFechaIngreso + "-" + alumnoDNI);

                System.out.println(idAlumno + " - " + alumnoNombre + " - " + alumnoApellido + " - " + alumnoFechaIngreso + "-" + alumnoDNI);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

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

    public List<String> getAlumnosIngresados() {
        return lista;
    }
}