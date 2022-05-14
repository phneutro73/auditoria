package application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import db.AdministratorPageConnection;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.ComboBoxAutoComplete;

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
	private JFXTextField fieldDirection;

	@FXML
	private JFXButton btnCancel;

	@FXML
	private JFXButton btnAccept;
	
    @FXML
    private JFXComboBox<String> cmbStreet;

    @FXML
    private JFXComboBox<String> cmbProvince;

    @FXML
    private JFXComboBox<String> cmbCountry;

    @FXML
    private JFXComboBox<String> cmbCity;

    @FXML
    private JFXTextField fieldCP;

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
				boolean success = adminDB.addShop(fieldName.getText(), fieldDirection.getText());

				if (success) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Guardado correcto",
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
							"Se ha producido un error inesperado. Por favor, inténtelo más tarde.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();
				}

				Stage stage = (Stage) btnCancel.getScene().getWindow();
				stage.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		
		ObservableList<String> countries = FXCollections.observableArrayList(
				"España (ES)");
		ObservableList<String> provinces = FXCollections.observableArrayList(
				"-", "Provincia 1", "Provincia 2");
		
		cmbCountry.getItems().removeAll(cmbCountry.getItems());
		cmbCountry.getItems().addAll(countries);
		cmbCountry.setValue(cmbCountry.getItems().get(0));
		cmbCountry.setDisable(true);
		
		cmbProvince.setEditable(true);
		cmbProvince.getItems().removeAll(cmbProvince.getItems());
		cmbProvince.getItems().addAll(provinces);
		cmbProvince.setValue(cmbProvince.getItems().get(0));
		new ComboBoxAutoComplete<String>(cmbProvince);
		
		cmbProvince.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			   
			if (!cmbProvince.getValue().toString().equals("-")) {
				getComboCity();
			} else {
				cmbCity.setDisable(true);
				cmbCity.setValue("-");
			}
			
		});

		cmbCity.getItems().removeAll(cmbCity.getItems());
		cmbCity.getItems().addAll("-");
		cmbCity.getSelectionModel().select("-");
		cmbCity.setDisable(true);

		cmbStreet.getItems().removeAll(cmbStreet.getItems());
		cmbStreet.getItems().addAll("-");
		cmbStreet.getSelectionModel().select("-");
		cmbStreet.setDisable(true);
		
		fieldCP.setText("-");
		fieldCP.setDisable(true);
		
		// TODO hacer que los cmb's sean filtros autocompletables
	}
	

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")
					&& (!fieldDirection.getText().isEmpty() && fieldDirection.getText() != null
							&& fieldDirection.getText() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkAllFields() - " + e.toString());
			return false;
		}
	}
	
	void getComboCity() {
		
		try {

			cmbCity.setDisable(false);
			cmbCity.setEditable(true);
			
			ObservableList<String> cities = FXCollections.observableArrayList(
					"-", "Ciudad 1", "Ciudad 2", "Ciudad 3");
			
			cmbCity.getItems().removeAll(cmbCity.getItems());
			cmbCity.getItems().addAll(cities);
			cmbCity.setValue(cmbCity.getItems().get(0));
			new ComboBoxAutoComplete<String>(cmbCity);
			
			cmbCity.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
				   
				if (cmbCity.getValue() != null) {
					if (!cmbCity.getValue().toString().equals("-")) {
						getComboStreet();
					} else {
						cmbStreet.setDisable(true);
						cmbStreet.setValue("-");
					}
				}
				
				
			}); 
			
		} catch (Exception e) {
			System.out.println("ERROR: getComboCity: " + e.getStackTrace());
		}
		
	}
	
	void getComboStreet() {
		
		try {
			
			cmbStreet.setDisable(false);
			cmbStreet.setEditable(true);

			ObservableList<String> streets = FXCollections.observableArrayList(
					"-", "Calle 1", "Calle 2", "Calle 3", "Calle 4", "Calle 5");
			
			cmbStreet.getItems().removeAll(cmbStreet.getItems());
			cmbStreet.getItems().addAll(streets);
			cmbStreet.setValue(cmbStreet.getItems().get(0));
			new ComboBoxAutoComplete<String>(cmbStreet);
			
			cmbStreet.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
				   
				if (cmbStreet.getValue() != null) {
					if (!cmbStreet.getValue().toString().equals("-")) {
						fieldCP.setText("");
						fieldCP.setDisable(false);
					} else {
						fieldCP.setText("-");
						fieldCP.setDisable(true);
					}
				}
				
				
			}); 
			
		} catch (Exception e) {
			System.out.println("ERROR: getComboStreet: " + e.getStackTrace());
		}
		
	}
}
