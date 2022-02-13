package application;

import java.io.IOException;
import java.util.function.Function;

import com.jfoenix.controls.JFXButton;

import db.AdministratorPageConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerYesNoAlertDialog {

	double height;
	double width;
	String title;
	String text;
	String question;
	String yesButtonTxt;
	String noButtonTxt;
	String yesAction;
	String[] params;

	public ControllerYesNoAlertDialog(double height, double width, String title, String text, String question,
			String yesButtonTxt, String noButtonTxt, String yesAction, String[] params) {
		super();
		this.height = height;
		this.width = width;
		this.title = title;
		this.text = text;
		this.yesButtonTxt = yesButtonTxt;
		this.noButtonTxt = noButtonTxt;
		this.question = question;
		this.yesAction = yesAction;
		this.params = params;
	}

	@FXML
	private AnchorPane parent;

	@FXML
	private Label dialogTitle;

	@FXML
	private Label btnClose;

	@FXML
	private Label dialogText;

	@FXML
	private Label dialogQuestion;

	@FXML
	private JFXButton btnYes;

	@FXML
	private JFXButton btnNo;

	@FXML
	void closeScreen(MouseEvent event) {
		Stage stage = (Stage) btnNo.getScene().getWindow();
		stage.close();
	}

	@FXML
	void yesButtonPressed(MouseEvent event) throws IOException {

		switch (yesAction) {
		case "adminDeleteUser":
			adminDeleteUser();
			break;
		case "adminDeleteSchedule":
			adminDeleteSchedule();
			break;
		case "adminDeleteRole":
			adminDeleteRole();
			break;
		case "adminDeleteShop":
			adminDeleteShop();
			break;
		case "adminDeleteItemType":
			adminDeleteItemType();
			break;
		default:
			break;
		}

		Stage stage = (Stage) btnNo.getScene().getWindow();
		stage.close();
	}

	@FXML
	void initialize() {

		try {

			if (height == 0) {
				height = 173;
			}
			if (width == 0) {
				width = 280;
			}

			dialogTitle.setText(title);
			dialogText.setText(text);
			dialogQuestion.setText(question);
			btnYes.setText(yesButtonTxt);
			btnNo.setText(noButtonTxt);

			parent.setPrefSize(width, height);
			parent.setMinSize(width, height);
			parent.setMaxSize(width, height);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	void adminDeleteUser() throws IOException {

		boolean success = true;

		try {

			AdministratorPageConnection adminDB = new AdministratorPageConnection();
			adminDB.deleteUser(Integer.parseInt(params[0]));
			success = true;

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		if (success) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Eliminado correctamente",
					"El usuario ha sido eliminado correctamente.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
					"Ha habido un error. El usuario no ha sido eliminado.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		}
	}

	void adminDeleteSchedule() throws IOException {

		boolean success = true;

		try {

			AdministratorPageConnection adminDB = new AdministratorPageConnection();
			adminDB.deleteSchedule(Integer.parseInt(params[0]));
			success = true;

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		if (success) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Eliminado correctamente",
					"El horario ha sido eliminado correctamente.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
					"Ha habido un error, el horario no ha sido eliminado.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		}
	}

	void adminDeleteRole() throws IOException {

		boolean success = true;

		try {

			AdministratorPageConnection adminDB = new AdministratorPageConnection();
			adminDB.deleteRole(Integer.parseInt(params[0]));
			success = true;

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		if (success) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Eliminado correctamente",
					"El puesto ha sido eliminado correctamente.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
					"Ha habido un error, el puesto no ha sido eliminado.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		}
	}

	void adminDeleteShop() throws IOException {
		boolean success = true;

		try {

			AdministratorPageConnection adminDB = new AdministratorPageConnection();
			adminDB.deleteShop(Integer.parseInt(params[0]));
			success = true;

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		if (success) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Eliminado correctamente",
					"La tienda ha sido eliminada correctamente.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
					"Ha habido un error, la tienda no ha sido eliminada.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		}
	}

	void adminDeleteItemType() throws IOException {
		
		boolean success = true;

		try {

			AdministratorPageConnection adminDB = new AdministratorPageConnection();
			adminDB.deleteItemType(Integer.parseInt(params[0]));
			success = true;

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		if (success) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Eliminado correctamente",
					"El tipo de prenda ha sido eliminado correctamente.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(120, 210, "Error",
					"Ha habido un error, el tipo de prenda no ha sido eliminado.");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		}
	}
}
