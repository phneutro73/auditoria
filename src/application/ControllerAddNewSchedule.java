package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import db.AdministratorPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerAddNewSchedule {

	@FXML
	private GridPane grdAddSchedule;

	@FXML
	private Label lblName;

	@FXML
	private Label lblCheckIn;

	@FXML
	private Label lblCheckOut;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXTimePicker fieldCheckIn;

	@FXML
	private JFXTimePicker fieldCheckOut;

	@FXML
	private JFXButton btnCancel;

	@FXML
	private JFXButton btnAccept;

	@FXML
	void cancelExit(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void saveNewSchedule(ActionEvent event) {

		try {

			if (checkAllFields()) {
				if (isValidTime()) {

					String checkIn = String.valueOf(fieldCheckIn.getValue()) + ":00";
					if (checkIn.length() < 8) {
						checkIn = "0" + checkIn;
					}
					String checkOut = String.valueOf(fieldCheckOut.getValue()) + ":00";
					if (checkOut.length() < 8) {
						checkOut = "0" + checkOut;
					}

					AdministratorPageConnection adminDB = new AdministratorPageConnection();
					boolean success = adminDB.addSchedule(fieldName.getText(), checkIn, checkOut);

					if (success) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
						ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Acción completada",
								"Los datos del horario se han guardado correctamente");
						loader.setController(control);
						Parent root = loader.load();

						Stage stage = new Stage();
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setScene(new Scene(root));
						stage.show();
					} else {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
						ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
								"Se ha producido un error. Por favor, inténtelo de nuevo.");
						loader.setController(control);
						Parent root = loader.load();

						Stage stage = new Stage();
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setScene(new Scene(root));
						stage.show();
					}

					Stage stage = (Stage) btnCancel.getScene().getWindow();
					stage.close();

				} else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(140, 230, "Error",
							"Los horarios introducidos no son válidos: la hora de entrada es posterior a la de salida.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();
				}
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
						"Es necesario que rellene todos los campos.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void initialize() {

	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")
					&& (!fieldCheckIn.getValue().toString().isEmpty() && fieldCheckIn.getValue() != null
							&& fieldCheckIn.getValue().toString() != "")
					&& (!fieldCheckOut.getValue().toString().isEmpty() && fieldCheckOut.getValue() != null
							&& fieldCheckOut.getValue().toString() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkAllFields() - " + e.toString());
			return false;
		}
	}

	boolean isValidTime() {

		try {
			if (!fieldCheckOut.getValue().isBefore(fieldCheckIn.getValue())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
