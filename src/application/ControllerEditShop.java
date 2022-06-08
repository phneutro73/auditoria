package application;

import java.time.LocalTime;
import java.util.Hashtable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

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

public class ControllerEditShop {

	int shopId;

	public ControllerEditShop(int shopId) {
		this.shopId = shopId;
	}

	@FXML
	private Label subtitle;

	@FXML
	private GridPane grdAddShop;

	@FXML
	private Label lblName;

	@FXML
	private Label lblDirection;

	@FXML
	private JFXTextField fieldName;

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
	private JFXButton btnCancel;

	@FXML
	private JFXButton btnAccept;

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

				boolean success = adminDB.updateShop(shopId, fieldName.getText(), cmbCountry.getText(),
						cmbProvince.getText(), cmbCity.getText(), cmbStreet.getText(), fieldCP.getText(),
						fieldNumber.getText());

				if (success) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Acción completada",
							"Los datos de la tienda se han guardado correctamente");
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
		subtitle.setText("Modificar tienda");
		AdministratorPageConnection adminDB = new AdministratorPageConnection();
		grdAddShop.getChildren().remove(1);
		getShop(adminDB);
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

	void getShop(AdministratorPageConnection adminDB) {

		try {

			Hashtable<String, String> shop = adminDB.getShop(shopId);

			if (shop.containsKey("shopName")) {
				String shopName = shop.get("shopName");
				fieldName.setText(shopName);
			}
			if (shop.containsKey("shopStreet")) {
				String shopStreet = shop.get("shopStreet");
				cmbStreet.setText(shopStreet);
			}
			if (shop.containsKey("shopNumber")) {
				String shopNumber = shop.get("shopNumber");
				fieldNumber.setText(shopNumber);
			}
			if (shop.containsKey("shopCity")) {
				String shopCity = shop.get("shopCity");
				cmbCity.setText(shopCity);
			}
			if (shop.containsKey("shopProvince")) {
				String shopProvince = shop.get("shopProvince");
				cmbProvince.setText(shopProvince);
			}
			if (shop.containsKey("shopCp")) {
				String shopCp = shop.get("shopCp");
				fieldCP.setText(shopCp);
			}
			if (shop.containsKey("shopCountry")) {
				String shopCountry = shop.get("shopCountry");
				cmbCountry.setText(shopCountry);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
