package application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ServicioGuardarButton {
	private final Button guardarButton;
	private final DatePicker campo_fecha;
	private final TextField campo_nombre;
	private final TextField campo_apellido;
	private final TextField campo_dni;

	public ServicioGuardarButton(Button guardarButton, DatePicker campo_fecha, TextField campo_nombre, TextField campo_apellido, TextField campo_dni) {
		this.guardarButton = guardarButton;
		this.campo_fecha = campo_fecha;
		this.campo_nombre = campo_nombre;
		this.campo_apellido = campo_apellido;
		this.campo_dni = campo_dni;
	}

	public void initialize() {
		// Crea un BooleanExpression que verifica si todos los campos están completos
		BooleanExpression camposCompletos = Bindings.createBooleanBinding(() ->
		campo_fecha.getValue() != null &&
		!campo_nombre.getText().isEmpty() &&
		!campo_apellido.getText().isEmpty() &&
		!campo_dni.getText().isEmpty(),
		campo_fecha.valueProperty(), campo_nombre.textProperty(), campo_apellido.textProperty(), campo_dni.textProperty());

		// Vincula la propiedad "disable" del botón de Guardar a la expresión camposCompletos
		guardarButton.disableProperty().bind(camposCompletos.not());
	}
}
