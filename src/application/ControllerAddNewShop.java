package application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.maps.model.AddressComponentType;
import com.google.maps.model.PlaceDetails;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.AutoCompleteAddressField.AddressPrediction;
import db.AdministratorPageConnection;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.ComboBoxAutoComplete;
import org.apache.commons.lang3.StringUtils;

public class ControllerAddNewShop {

	@FXML
	private Label subtitle;

	@FXML
	private GridPane grdAddSchedule;

	@FXML
	private Label lblName;

	@FXML
	private Label lblDirection;

	@FXML
	private Label lblStreet;

	@FXML
	private Label lblCity;

	@FXML
	private Label lblProvince;

	@FXML
	private Label lblCP;

	@FXML
	private Label lblCountry;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXButton btnCancel;

	@FXML
	private JFXButton btnAccept;

	@FXML
	private JFXTextField cmbStreet;

	@FXML
	private JFXTextField cmbProvince;

	@FXML
	private JFXTextField cmbCountry;

	@FXML
	private JFXTextField cmbCity;

	@FXML
	private JFXTextField fieldCP;

	@FXML
	private Label lblNum;

	@FXML
	private JFXTextField fieldNumber;

	@FXML
	void cancelExit(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void saveNewShop(ActionEvent event) {

		try {

			if (checkAllFields()) {

				AdministratorPageConnection adminDB = new AdministratorPageConnection();
				boolean success = adminDB.addShop(fieldName.getText(), cmbStreet.getText(), fieldNumber.getText(),
						cmbCity.getText(), cmbProvince.getText(), fieldCP.getText(), cmbCountry.getText());

				if (success) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Acción completada",
							"Los datos de la tienda se han guardado correctamente.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();
				} else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
							"Se ha producido un error inesperado. Por favor, inténtelo máss tarde.");
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

		cmbCountry.setDisable(true);
		cmbProvince.setDisable(true);
		cmbCity.setDisable(true);
		cmbStreet.setDisable(true);
		fieldCP.setDisable(true);

		AutoCompleteAddressField text = new AutoCompleteAddressField();

		text.getEntryMenu().setOnAction((ActionEvent e) -> {
			((MenuItem) e.getTarget()).addEventHandler(Event.ANY, (Event event2) -> {
				if (text.getLastSelectedObject() != null) {
					text.setText(text.getLastSelectedObject().toString());
					PlaceDetails place = AutoCompleteAddressField
							.getPlace((AddressPrediction) text.getLastSelectedObject());
					if (place != null) {
						fieldCP.setText(AutoCompleteAddressField.getComponentLongName(place.addressComponents,
								AddressComponentType.POSTAL_CODE));
						cmbCity.setText(AutoCompleteAddressField.getComponentLongName(place.addressComponents,
								AddressComponentType.LOCALITY));
						cmbProvince.setText(AutoCompleteAddressField.getComponentLongName(place.addressComponents,
								AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1));
						cmbCountry.setText(AutoCompleteAddressField.getComponentLongName(place.addressComponents,
								AddressComponentType.COUNTRY));
						cmbStreet.setText(AutoCompleteAddressField.getComponentLongName(place.addressComponents,
								AddressComponentType.ROUTE));
						fieldCP.setText(AutoCompleteAddressField.getComponentLongName(place.addressComponents,
								AddressComponentType.POSTAL_CODE));

						cmbCountry.setDisable(false);
						cmbProvince.setDisable(false);
						cmbCity.setDisable(false);
						cmbStreet.setDisable(false);
						fieldCP.setDisable(false);

					} else {
						cmbStreet.setText("");
						fieldCP.setText("");
						cmbCity.setText("");
						cmbProvince.setText("");
						cmbCountry.setText("");
					}
				}
			});
		});

		grdAddSchedule.add(text, 1, 1);
	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null
					&& !fieldName.getText().toString().equals(""))
					&& (!cmbCountry.getText().isEmpty() && cmbCountry.getText() != null
							&& !cmbCountry.getText().toString().equals(""))
					&& (!cmbProvince.getText().isEmpty() && cmbProvince.getText() != null
							&& !cmbProvince.getText().toString().equals(""))
					&& (!cmbCity.getText().isEmpty() && cmbCity.getText() != null
							&& !cmbCity.getText().toString().equals(""))
					&& (!cmbStreet.getText().isEmpty() && cmbStreet.getText() != null
							&& !cmbStreet.getText().toString().equals(""))
					&& (!fieldCP.getText().isEmpty() && fieldCP.getText() != null
							&& !fieldCP.getText().toString().equals(""))
					&& (!fieldNumber.getText().isEmpty() && fieldNumber.getText() != null
							&& !fieldNumber.getText().toString().equals(""))) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkAllFields() - " + e.toString());
			return false;
		}
	}
}
