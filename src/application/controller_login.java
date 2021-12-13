
package application;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class controller_login {

	@FXML
	private AnchorPane loginPage;

	@FXML
	private Button btnLogin;

	@FXML
	private JFXButton btnForget;

	@FXML
	private TextField fieldUser;

	@FXML
	private PasswordField fieldPassword;

	@FXML
	private Label txtHintPw;

	@FXML
	private Label txtHintUser;

	@FXML
	void deletePw(MouseEvent event) {
		txtHintPw.setText("");
		if (fieldUser.getText().isEmpty()) {
			txtHintUser.setText("Usuario");
		}
	}

	@FXML
	void deleteUser(MouseEvent event) {
		txtHintUser.setText("");
		if (fieldPassword.getText().isEmpty()) {
			txtHintPw.setText("Contraseña");
		}
	}

	@FXML
	void forgetFunction(ActionEvent event) {

	}

	@FXML
	void loginFunction(ActionEvent event) {
		try {

			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/administrator_page.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setMaximized(true);
			stage.show();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@FXML
	void initialize() {

	}

}
