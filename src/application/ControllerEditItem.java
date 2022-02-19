package application;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import db.AddItemsPageConnection;
import db.ConsultationPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.CurrentUser;

public class ControllerEditItem {

	int itemId;
	CurrentUser currentUser;

	public ControllerEditItem(int itemId, CurrentUser currentUser) {
		this.itemId = itemId;
		this.currentUser = currentUser;
	}

	@FXML
	private Label subtitle;

	@FXML
	private Label lblBarCode;

	@FXML
	private Label lblName;

	@FXML
	private Label lblType;

	@FXML
	private JFXTextField fieldBarCode;

	@FXML
	private Label lblSize;

	@FXML
	private Label lblPrice;

	@FXML
	private JFXComboBox<String> fieldType;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXTextField fieldSize;

	@FXML
	private JFXTextField fieldPrice;

	@FXML
	private JFXButton btnCancel;

	@FXML
	private JFXButton btnAccept;

	String oldBarCode;

	@FXML
	void initialize() {

		ConsultationPageConnection consultDB = new ConsultationPageConnection();

		getTypes(consultDB);
		getItem(consultDB);

	}

	@FXML
	void cancelExit(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void updateItem(ActionEvent event) throws IOException {

		if (checkAllFields()) {
			if (checkNumeric()) {

				ConsultationPageConnection consultDB = new ConsultationPageConnection();

				List<Hashtable<String, Object>> itemWithBarCode = consultDB.getItemsWithBarCode(fieldBarCode.getText());

				if (itemWithBarCode.size() <= 0) {
					boolean success = consultDB.updateItem(itemId, fieldBarCode.getText(), fieldName.getText(),
							fieldType.getSelectionModel().getSelectedItem(), fieldSize.getText(),
							Double.parseDouble(fieldPrice.getText()));

					if (success) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
						ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Guardado correcto",
								"Los datos del arículo se han guardado correctamente");
						loader.setController(control);
						Parent root = loader.load();

						Stage stage = new Stage();
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setScene(new Scene(root));
						stage.show();
					} else {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
						ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
								"Se ha producido un error. Por favor, inténtelo más tarde.");
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
					if (oldBarCode.equals(fieldBarCode.getText())) {
						boolean success = consultDB.updateItem(itemId, fieldBarCode.getText(), fieldName.getText(),
								fieldType.getSelectionModel().getSelectedItem(), fieldSize.getText(),
								Double.parseDouble(fieldPrice.getText()));

						if (success) {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
							ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Guardado correcto",
									"Los datos del arículo se han guardado correctamente");
							loader.setController(control);
							Parent root = loader.load();

							Stage stage = new Stage();
							stage.initStyle(StageStyle.UNDECORATED);
							stage.setScene(new Scene(root));
							stage.show();
						} else {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
							ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
									"Se ha producido un error. Por favor, inténtelo más tarde.");
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
						ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
								"Código de barras no válido. Ya existe otro artículo con él.");
						loader.setController(control);
						Parent root = loader.load();

						Stage stage = new Stage();
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setScene(new Scene(root));
						stage.show();
					}
				}

			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
						"Recuerde que el precio es un campo numérico.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();
			}
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(140, 220, "Error",
					"Es necesario que rellene todos los campos.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		}

	}

	void getTypes(ConsultationPageConnection consultDB) {

		List<String> types = consultDB.listItemTypes();
		fieldType.getItems().removeAll(fieldType.getItems());
		fieldType.getItems().addAll(types);
		fieldType.getSelectionModel().select("-");

	}

	void getItem(ConsultationPageConnection consultDB) {

		Hashtable<String, Object> item = consultDB.getItem(itemId);

		String name = (String) item.get("name");
		oldBarCode = (String) item.get("itemBarCode");
		String strType = (String) item.get("type");
		String size = (String) item.get("size");
		String price = item.get("price").toString();

		fieldBarCode.setText(oldBarCode);
		fieldName.setText(name);
		fieldType.getSelectionModel().select(strType);
		fieldSize.setText(size);
		fieldPrice.setText(price);

	}

	boolean checkAllFields() {
		if ((!fieldBarCode.getText().isEmpty() && fieldBarCode.getText() != null && fieldBarCode.getText() != "")
				&& (!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")
				&& (fieldType.getValue().toString() != "-")
				&& (!fieldSize.getText().isEmpty() && fieldSize.getText() != null && fieldSize.getText() != "")
				&& (!fieldPrice.getText().isEmpty() && fieldPrice.getText() != null && fieldPrice.getText() != "")) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkNumeric() {

		try {
			Double.parseDouble(fieldPrice.getText());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
