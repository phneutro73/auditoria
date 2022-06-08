package application;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Hashtable;

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

public class ControllerEditSchedule {

	int scheduleId;

	public ControllerEditSchedule(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	@FXML
	private Label subtitle;

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
					String checkOut = String.valueOf(fieldCheckOut.getValue()) + ":00";
					AdministratorPageConnection adminDB = new AdministratorPageConnection();
					boolean success = adminDB.updateSchedule(scheduleId, fieldName.getText(), checkIn, checkOut);

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
						ControllerAlertDialog control = new ControllerAlertDialog(140, 210, "Error",
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
		subtitle.setText("Modificar horario");
		AdministratorPageConnection adminDB = new AdministratorPageConnection();
		getSchedule(adminDB);
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
			e.printStackTrace();
			return false;
		}
	}

	void getSchedule(AdministratorPageConnection adminDB) {

		Hashtable<String, String> schedule = adminDB.getSchedule(scheduleId);

		if (schedule.containsKey("name")) {
			String name = schedule.get("name");
			fieldName.setText(name);
		}
		if (schedule.containsKey("checkInTime")) {
			String strCheckInTime = schedule.get("checkInTime");
			LocalTime checkIn = LocalTime.parse(strCheckInTime);
			fieldCheckIn.setValue(checkIn);
		}
		if (schedule.containsKey("checkInTime")) {
			String strCheckOutTime = schedule.get("checkOutTime");
			LocalTime checkOut = LocalTime.parse(strCheckOutTime);
			fieldCheckOut.setValue(checkOut);
		}

	}

}
