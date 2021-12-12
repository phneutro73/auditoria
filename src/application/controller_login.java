
package application;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Button loginButton;

    @FXML
    private JFXButton forgetButton;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label txtHintPw;

    @FXML
    private Label txtHintUser; 

    @FXML
    void deletePw(MouseEvent event) {
    	txtHintPw.setText("");
    	if (userField.getText().isEmpty()) {
    		txtHintUser.setText("Usuario");
    	}
    }

    @FXML
    void deleteUser(MouseEvent event) {
    	txtHintUser.setText("");
    	if(passwordField.getText().isEmpty()) {
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
    	/*
        assert loginPage != null : "fx:id=\"loginPage\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert forgetButton != null : "fx:id=\"forgetButton\" was not injected: check your FXML file 'login.fxml'.";
        assert userField != null : "fx:id=\"userField\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";
        assert txtHintPw != null : "fx:id=\"txtHintPw\" was not injected: check your FXML file 'login.fxml'.";
        assert txtHintUser != null : "fx:id=\"txtHintUser\" was not injected: check your FXML file 'login.fxml'.";
		*/
    	
    }

}
