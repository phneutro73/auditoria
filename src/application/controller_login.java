
package application;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.jfoenix.controls.JFXButton;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import db.LoginPageConnection;

public class controller_login {

	@FXML
	private AnchorPane parent;

	@FXML
	private Button btnLogin;

	@FXML
	private JFXButton btnForget;

	@FXML
	private TextField fieldUser;

	@FXML
	private PasswordField fieldPassword;

	@FXML
	private ImageView imgLogo;

	@FXML
	private Label btnMinimize;

	@FXML
	private Label btnClose;

	@FXML
	private ImageView loadingIcon;

	private double xOffSet = 0;
	private double yOffSet = 0;

	@FXML
	void closeScreen(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void minimizeScreen(MouseEvent event) {
		Main.stage.setIconified(true);
	}

	@FXML
	void forgetFunction(ActionEvent event) {
		// TODO
	}

	@FXML
	void loginFunction(ActionEvent event) throws IOException {
		try {

			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();

			LoginPageConnection loginDB = new LoginPageConnection();

			byte[] salt = loginDB.getSalt(fieldUser.getText());
			byte[] hash = loginDB.getHash(fieldUser.getText());
			byte[] calculatedHash = calculateHash(fieldPassword.getText(), salt);
			System.out.println(salt[salt.length - 1]);
			System.out.println(hash[hash.length - 1]);
			System.out.println(hash[hash.length - 1]);
			System.out.println(calculatedHash[calculatedHash.length - 1]);
			if (Arrays.equals(hash, calculatedHash)) {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/administrator_page.fxml"));
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();

			} else {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(0, 0, "Error",
						"El usuario y/o contraseña son incorrectos.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();

			}
			// SOLO SI EL USUARIO EXISTE Y LA CONTRASEÃ‘A ES CORRECTA

		} catch (Exception e) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
			ControllerAlertDialog control = new ControllerAlertDialog(111, 200, "Error en el inicio de sesión",
					"El usuario y/o contraseña no son correctos, inténtelo de nuevo");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			System.out.println(e.toString());
		}

	}

	byte[] calculateHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();

		return hash;
	}

	@FXML
	void initialize() {

		try {

			makeStageDragable();

		} catch (Exception e) {
			System.out.println("ERROR: controllerLogin.java - initialize() - " + e.toString());
		}
	}

	private void makeStageDragable() {

		parent.setOnMousePressed((event) -> {
			xOffSet = event.getSceneX();
			yOffSet = event.getSceneY();
		});

		parent.setOnMouseDragged((event) -> {
			Main.stage.setX(event.getScreenX() - xOffSet);
			Main.stage.setY(event.getScreenY() - yOffSet);
			Main.stage.setOpacity(0.8f);
		});

		parent.setOnDragDone((event) -> {
			Main.stage.setOpacity(1.0f);
		});

		parent.setOnMouseReleased((event) -> {
			Main.stage.setOpacity(1.0f);
		});

	}

	private void setRotation() {

		loadingIcon.setVisible(true);
		RotateTransition rotateTransition = new RotateTransition(Duration.millis(3000), loadingIcon);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(Animation.INDEFINITE);
		rotateTransition.setInterpolator(Interpolator.LINEAR);
		rotateTransition.play();
	}

}
