package application;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import db.AddItemsPageConnection;
import db.SalesPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.CurrentUser;

public class ControllerAddItemsPage {

	double height;
	double width;
	CurrentUser currentUser;

	ControllerAddItemsPage(double height, double width, CurrentUser currentUser) {
		this.height = height;
		this.width = width;
		this.currentUser = currentUser;
	}

	@FXML
	private AnchorPane parent;

	@FXML
	private VBox drawer;

	@FXML
	private HBox btnMenu;

	@FXML
	private HBox btnSales;

	@FXML
	private HBox btnConsultation;

	@FXML
	private HBox btnAddItem;

	@FXML
	private HBox btnStatistics;

	@FXML
	private HBox btnAdministrator;

	@FXML
	private VBox body;

	@FXML
	private JFXTextField fieldBarCode;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXComboBox<String> fieldType;

	@FXML
	private JFXComboBox<String> fieldShop;

	@FXML
	private JFXTextField fieldSize;

	@FXML
	private JFXTextField fieldPrice;

	@FXML
	private JFXTextField fieldQuantity;

	@FXML
	private JFXButton btnAccept;

	@FXML
	private AnchorPane logOutButton;

	boolean isExpanded;

	@FXML
	void initialize() {

		isExpanded = false;

		if (currentUser.getRoleId() != 1) {
			btnAdministrator.setVisible(false);
		}

		AddItemsPageConnection addItemsDB = new AddItemsPageConnection();

		fieldBarCode.setText("");
		fieldName.setText("");
		fieldSize.setText("");
		fieldPrice.setText("");
		fieldQuantity.setText("");

		List<String> itemTypes = addItemsDB.listItemTypes();
		fieldType.getItems().removeAll(fieldType.getItems());
		fieldType.getItems().addAll(itemTypes);
		fieldType.getSelectionModel().select("-");

		List<String> shop = addItemsDB.listShops();
		fieldShop.getItems().removeAll(fieldShop.getItems());
		fieldShop.getItems().addAll(shop);
		fieldShop.getSelectionModel().select("-");
	}

	@FXML
	void goToAdministratorPage(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/administrator_page.fxml"));
		controller_administrator control = new controller_administrator(0, 0, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void goToConsultationPage(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ConsultationPage.fxml"));
		ControllerConsultationPage control = new ControllerConsultationPage(0, 0, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void goToSalesPage(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SalesPage.fxml"));
		ControllerSalesPage control = new ControllerSalesPage(0, 0, currentUser);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void goToStatisticsPage(MouseEvent event) throws IOException {
		// TODO hay que añadir la página de estadísticas del administrador
		if (currentUser.getRoleId() != 1) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StatisticsShopAssistantPage.fxml"));
			ControllerStatisticsShopAssistantPage control = new ControllerStatisticsShopAssistantPage(0, 0,
					currentUser);
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StatisticsAdministratorPage.fxml"));
			ControllerStatisticsAdministratorPage control = new ControllerStatisticsAdministratorPage(0, 0,
					currentUser);
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		}
	}

	@FXML
	void saveNewItem(ActionEvent event) throws IOException {

		if (checkAllFields()) {
			if (checkNumeric()) {
				AddItemsPageConnection addItemsDB = new AddItemsPageConnection();
				Hashtable<String, Object> itemWithBarCode = addItemsDB.getItemWithBarCode(fieldBarCode.getText());

				if (itemWithBarCode.isEmpty()) {
					// ese código de barras no está en el sistema. Añadimos directamente
					boolean success = addItemsDB.addNewItem(fieldBarCode.getText(), fieldName.getText(),
							fieldType.getSelectionModel().getSelectedItem(), fieldSize.getText(),
							Double.parseDouble(fieldPrice.getText()), Integer.parseInt(fieldQuantity.getText()),
							fieldShop.getSelectionModel().getSelectedItem());

					if (success) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
						ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
								"El artículo se ha guardado correctamente.");
						loader.setController(control);
						Parent root = loader.load();

						Stage stage = new Stage();
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setScene(new Scene(root));
						stage.show();

						initialize();
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

						initialize();
					}
				} else {
					// El código de barras introducido está en la base de datos. Analizamos si se
					// está ampliando stock o si es un error.

					String price = itemWithBarCode.get("price").toString();

					if (price.indexOf(".") == -1) {
						price = price + ".";
					}
					if (fieldPrice.getText().indexOf(".") == -1) {
						fieldPrice.setText(fieldPrice.getText() + ".");
					}

					while (price.substring(price.indexOf(".") + 1).length() < 2) {
						price = price + "0";
					}
					while (fieldPrice.getText().substring(fieldPrice.getText().indexOf(".") + 1).length() < 2) {
						fieldPrice.setText(fieldPrice.getText() + "0");
					}
					int typeId = addItemsDB.getTypeId(fieldType.getSelectionModel().getSelectedItem());

					if (fieldBarCode.getText().equals(itemWithBarCode.get("barCode"))
							&& fieldName.getText().equals(itemWithBarCode.get("name"))
							&& typeId == Integer.parseInt(itemWithBarCode.get("typeId").toString())
							&& fieldSize.getText().equals(itemWithBarCode.get("size"))
							&& fieldPrice.getText().equals(price)) {

						// Es el mismo artículo
						// Preguntamos si se quiere añadir más stock
						String[] params = { String.valueOf(itemWithBarCode.get("id")),
								fieldShop.getSelectionModel().getSelectedItem(),
								String.valueOf(fieldQuantity.getText()) };

						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("/application/YesNoAlertDialog.fxml"));
						ControllerYesNoAlertDialog control = new ControllerYesNoAlertDialog(165, 275, "Atención",
								"Este artículo ya está registrado en la base de datos.",
								"¿Está seguro de que quiere añadir " + fieldQuantity.getText() + " más?", "SÍ", "NO",
								"addItemsUpdateQuantity", params);
						loader.setController(control);
						Parent root = loader.load();

						Stage stage = new Stage();
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setScene(new Scene(root));
						stage.showAndWait();

						initialize();

					} else {
						// Son artículos distintos. Mostramos un mensaje de error
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
						ControllerAlertDialog control = new ControllerAlertDialog(140, 220, "Error",
								"El código de barras introducido está asignado a otro artículo diferente. Es necesario que añada uno distinto.");
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
						"Recuerde que el precio y la cantidad son campos numéricos.");
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

	@FXML
	void expandMenu(MouseEvent event) {

		if (isExpanded) {
			drawer.setPrefWidth(60);
			isExpanded = false;
		} else {
			drawer.setPrefWidth(190);
			isExpanded = true;
		}

	}

	@FXML
	void hideMenu(MouseEvent event) {
		if (isExpanded) {
			drawer.setPrefWidth(60);
			isExpanded = false;

		}
	}

	@FXML
	void logOut(MouseEvent event) throws IOException {
		SalesPageConnection salesDB = new SalesPageConnection();

		boolean unfinishedSales = salesDB.unfinishedTicket(currentUser.getId());

		if (unfinishedSales) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
					"Tiene una venta en curso. Es necesario que la finalice o cancele.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/login.fxml"));
			controller_login control = new controller_login();
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		}
	}

	boolean checkAllFields() {
		if ((!fieldBarCode.getText().isEmpty() && fieldBarCode.getText() != null && fieldBarCode.getText() != "")
				&& (!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")
				&& (fieldType.getValue().toString() != "-")
				&& (!fieldSize.getText().isEmpty() && fieldSize.getText() != null && fieldSize.getText() != "")
				&& (!fieldPrice.getText().isEmpty() && fieldPrice.getText() != null && fieldPrice.getText() != "")
				&& (!fieldQuantity.getText().isEmpty() && fieldQuantity.getText() != null
						&& fieldQuantity.getText() != "")
				&& (fieldShop.getValue().toString() != "-")) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkNumeric() {

		try {
			Double.parseDouble(fieldPrice.getText());
			Integer.parseInt(fieldQuantity.getText());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
