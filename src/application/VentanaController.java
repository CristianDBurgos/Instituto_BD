package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.List;

public class VentanaController {

	@FXML
	private DatePicker campo_fecha; // Campo de selección de fecha
	@FXML
	private TextField campo_nombre; // Campo de entrada para el nombre
	@FXML
	private TextField campo_apellido; // Campo de entrada para el apellido
	@FXML
	private TextField campo_dni; // Campo de entrada para el DNI
	@FXML
	private Button btn_guardar; // Botón de guardar
	@FXML
	private ListView<String> mostrar_datos; // ListView para mostrar datos de alumnos en la pestaña 1

	@FXML
	private Button btn_list_alumnos; // Botón para listar alumnos
	@FXML
	private Button btn_delete; // Botón de borrar
	@FXML
	private Button btn_list_cursos; // Botón para listar cursos
	@FXML
	private Button btn_list_docentes; // Botón para listar docentes
	@FXML
	private Button btn_consulta1; // Botón para realizar la consulta 1
	@FXML
	private Button btn_consulta2; // Botón para realizar la consulta 2
	@FXML
	private Button btn_consulta3; // Botón para realizar la consulta 3
	@FXML
	private Button btn_consulta4; // Botón para realizar la consulta 4
	@FXML
	private TextField alumno_DNI; // Campo de texto para realizar consulta mediante dni
	@FXML
	private Button btn_consultar; // Botón para mostrar el resultado de la consulta del alumno por dni
	@FXML
	private ListView<String> resultado_consultas; // ListView para mostrar resultados de consultas en la pestaña 2

	// Llamo al Servicio Insert
	private ServicioInsert servicioInsert = new ServicioInsert();
	// Llamo al Servicio de Consultas
	private ServicioDeConsultasADC servicioConsultas = new ServicioDeConsultasADC();
	// Llamo al Servicio Delete
	private ServicioDelete servicioEliminar = new ServicioDelete();
	// LLamo al Servicio Consultas Extras
	private ServicioConsultasExtras servicioOtrasConsultas = new ServicioConsultasExtras();
	// LLamo al Servicio Consultas Text Field
	ServicioConsultaTextField servicioConsultaPorDNI = new ServicioConsultaTextField();

	// Llamamos a la clase ServiceGuardarButton que habilita el botón de GUARDAR solo si los 4 campos están completos
	@FXML
	public void initialize() {
		ServicioGuardarButton serviceGuardarButton = new ServicioGuardarButton(btn_guardar, campo_fecha, campo_nombre, campo_apellido, campo_dni);
		serviceGuardarButton.initialize();
	}

	@FXML
	void guardar_mostrar_en_listview(ActionEvent event) {
		// Obtener la fecha seleccionada en el DatePicker
		LocalDate fechaIngreso = campo_fecha.getValue();
		// Obtener los datos ingresados en los campos de texto
		String nombre = campo_nombre.getText();
		String apellido = campo_apellido.getText();
		String dni = campo_dni.getText();
		// Llamar al método insertar del servicio Servicio_Insert
		servicioInsert.insertar(nombre, apellido, fechaIngreso, dni);
		// Obtener la lista de datos de alumnos del servicio Servicio_Insert
		List<String> datosAlumnos = servicioInsert.getAlumnosIngresados();
		// Limpiar la lista en el ListView
		mostrar_datos.getItems().clear();
		// Mostrar los nombres de alumnos en el ListView
		mostrar_datos.getItems().addAll(datosAlumnos);
	}
	
	@FXML
	void mostrar_alumnos(ActionEvent event) {
		// Llamar al método setAlumnos del servicio Servicio_de_Consultas
		servicioConsultas.setAlumnos();
		// Obtener la lista de alumnos del servicio Servicio_de_Consultas
		List<String> alumnos = servicioConsultas.getAlumnos();
		// Limpiar la lista en el ListView
		resultado_consultas.getItems().clear();
		// Mostrar los alumnos en el ListView
		resultado_consultas.getItems().addAll(alumnos);
	}

	//Elementos observables
	@FXML
	void action_select(MouseEvent event) {	 
		//Busqueda por contenido
		String selectedIndices2 = resultado_consultas.getSelectionModel().getSelectedItem();
		System.out.println(selectedIndices2);
	}

	@FXML
	void delete_alumno() {
		String alumnoSeleccionado = resultado_consultas.getSelectionModel().getSelectedItem();

		if (alumnoSeleccionado != null) {
			// Obtener el ID del alumno seleccionado
			int idAlumno = obtenerIDAlumno(alumnoSeleccionado);

			servicioEliminar.eliminar(idAlumno);

			// Obtener la lista de datos de alumnos del servicio Servicio_Insert
			List<String> datosAlumnos = servicioEliminar.getListadoActualizado();
			// Limpiar la lista en el ListView
			resultado_consultas.getItems().clear();
			// Mostrar los nombres de alumnos en el ListView
			resultado_consultas.getItems().addAll(datosAlumnos);
		}
	}
	// Método para obtener el ID del alumno seleccionado en el ListView
	private int obtenerIDAlumno(String alumnoSeleccionado) {
		// Analiza el String del alumno seleccionado para obtener el ID
		String[] partes = alumnoSeleccionado.split("-");
		int idAlumno = Integer.parseInt(partes[0].trim());

		return idAlumno;
	}

	@FXML
	void mostrar_docentes(ActionEvent event) {
		// Llamar al método setDocentes del servicio Servicio_de_Consultas
		servicioConsultas.setDocentes();
		// Obtener la lista de docentes del servicio Servicio_de_Consultas
		List<String> docentes = servicioConsultas.getDocentes();
		// Limpiar la lista en el ListView
		resultado_consultas.getItems().clear();
		// Mostrar los docentes en el ListView
		resultado_consultas.getItems().addAll(docentes);
	}

	@FXML
	void mostrar_cursos(ActionEvent event) {
		// Llamar al método setCursos del servicio Servicio_de_Consultas
		servicioConsultas.setCursos();
		// Obtener la lista de cursos del servicio Servicio_de_Consultas
		List<String> cursos = servicioConsultas.getCursos();
		// Limpiar la lista en el ListView
		resultado_consultas.getItems().clear();
		// Mostrar los cursos en el ListView
		resultado_consultas.getItems().addAll(cursos);
	}

	@FXML
	void mostrar_consulta1(ActionEvent event) {
		// Llamar al método setConsulta1 del servicio ServicioConsultasExtras
		servicioOtrasConsultas.setConsulta1();
		// #Listado de alumnos del curso Programación
		List<String> consulta1 = servicioOtrasConsultas.getConsulta1();
		// Limpiar la lista en el ListView
		resultado_consultas.getItems().clear();
		// Mostrar los alumnos en el ListView
		resultado_consultas.getItems().addAll(consulta1);
	}

	@FXML
	void mostrar_consulta2(ActionEvent event) {
		// Llamar al método setConsulta2 del servicio ServicioConsultasExtras
		servicioOtrasConsultas.setConsulta2();
		// #Cantidad de cursos del alumno Id 13
		List<String> consulta2 = servicioOtrasConsultas.getConsulta2();
		// Limpiar la lista en el ListView
		resultado_consultas.getItems().clear();
		// Mostrar los alumnos en el ListView
		resultado_consultas.getItems().addAll(consulta2);
	}

	@FXML
	void mostrar_consulta3(ActionEvent event) {
		// Llamar al método setConsulta3 del servicio ServicioConsultasExtras
		servicioOtrasConsultas.setConsulta3();
		// #Cursos del alumno con Id 15
		List<String> consulta3 = servicioOtrasConsultas.getConsulta3();
		// Limpiar la lista en el ListView
		resultado_consultas.getItems().clear();
		// Mostrar los alumnos en el ListView
		resultado_consultas.getItems().addAll(consulta3);
	}

	@FXML
	void mostrar_consulta4(ActionEvent event) {
		// Llamar al método setConsulta4 del servicio ServicioConsultasExtras
		servicioOtrasConsultas.setConsulta4();
		// #Curso del docente con Id 4
		List<String> consulta4 = servicioOtrasConsultas.getConsulta4();
		// Limpiar la lista en el ListView
		resultado_consultas.getItems().clear();
		// Mostrar los alumnos en el ListView
		resultado_consultas.getItems().addAll(consulta4);
	}

	@FXML
	void consultar_alumno(ActionEvent event) {
		// Obtener el valor del campo de texto DNI
		int dni = Integer.parseInt(alumno_DNI.getText());
		// Llamar al método setConsultaPorDNI del servicio ServicioConsultasTextField
		servicioConsultaPorDNI.setConsultaPorDNI(dni);
		// Obtener los resultados de la consulta y mostrarlos en el ListView
		List<String> resultados = servicioConsultaPorDNI.getConsultaPorDni();
		// Limpiar la lista en el ListView
		resultado_consultas.getItems().clear();
		// Mostrar el alumno en el ListView
		resultado_consultas.getItems().addAll(resultados);
	}

}