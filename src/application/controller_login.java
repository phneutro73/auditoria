
package application;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    	if(fieldPassword.getText().isEmpty()) {
    		txtHintPw.setText("Contraseña");
    	}
    }
    
    @FXML
    void forgetFunction(ActionEvent event) {

    }

    @FXML
    void loginFunction(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	
    }

}
