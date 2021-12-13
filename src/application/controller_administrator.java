package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class controller_administrator {

    @FXML
    private VBox drawer;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblUserSurname;

    @FXML
    private JFXButton btnSales;

    @FXML
    private JFXButton btnSales1;

    @FXML
    private JFXButton btnSales2;

    @FXML
    private JFXButton btnSales3;

    @FXML
    private JFXButton btnSales4;

    @FXML
    private Label textTitleAdministrator;

    @FXML
    private Tab tabAddNewUser;

    @FXML
    private AnchorPane tabAddUser;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSurname;

    @FXML
    private Label lblDat;

    @FXML
    private Label lblDni;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblUser;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblEmail2;

    @FXML
    private Label lblPassword2;

    @FXML
    private Label lblRole;

    @FXML
    private TextField fieldEmail2;

    @FXML
    private TextField fieldUser;

    @FXML
    private TextField fieldEmail;

    @FXML
    private DatePicker fieldDat;

    @FXML
    private TextField fieldDNI;

    @FXML
    private TextField fieldSurname;

    @FXML
    private TextField fieldName;

    @FXML
    private ComboBox<String> fieldRole;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private PasswordField fieldPassword2;

    @FXML
    private JFXCheckBox chkAccept;

    @FXML
    private JFXButton btnTerm;

    @FXML
    private Button btnSave;

    @FXML
    private Label txtResult;

    @FXML
    void saveUser(ActionEvent event) {
    	
    	try {
    		
    		boolean notEmptyFields = checkAllFields();
    		if (notEmptyFields) {
        		boolean checkEmail = checkSecondField("email");
        		boolean checkPassword = checkSecondField("password");
        		boolean isAccepted = chkAccept.isSelected();
        		
        		if (checkEmail && checkPassword && isAccepted) {
        			// TODO: guardar en bbdd
        			txtResult.setText("Guardado correctamente.");
        			txtResult.setTextFill(Color.GREEN);
        			/*
        			 * TODO: después estaría guay que apareciera un pop-up diciendo que todo ha ido guay, por ahora, añado una 
        			 * label que lo diga.
        			 */
        		} else {
        			// no está todo correcto, no se guarda
        			txtResult.setText("Se ha producido un error. Revise los campos.");
        			txtResult.setTextFill(Color.RED);
         			/*
         			 * TODO: Aquí pasa igual que antes, por ahora, va por una label
         			 */
        		}
    		} else {
    			// no está todo correcto, no se guarda
    			txtResult.setText("Rellene todos los campos.");
    			txtResult.setTextFill(Color.RED);
     			/*
     			 * TODO: Aquí pasa igual que antes, por ahora, va por una label
     			 */
    		}
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
    }
    
    boolean checkAllFields() {

    	try {
    		
    		if((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "") &&
    				(!fieldSurname.getText().isEmpty() && fieldSurname.getText() != null && fieldName.getText() != "") &&
    				(!fieldDNI.getText().isEmpty() && fieldDNI.getText() != null && fieldDNI.getText() != "") &&
    				(!fieldDat.getValue().toString().isEmpty() && fieldDat.getValue().toString() != null && fieldDat.getValue().toString() != "") &&
    				(!fieldEmail.getText().isEmpty() && fieldEmail.getText() != null && fieldEmail.getText() != "") &&
    				(!fieldEmail2.getText().isEmpty() && fieldEmail2.getText() != null && fieldEmail2.getText() != "") &&
    				(!fieldUser.getText().isEmpty() && fieldUser.getText() != null && fieldUser.getText() != "") &&
    				(!fieldPassword.getText().isEmpty() && fieldPassword.getText() != null && fieldPassword.getText() != "") &&
    				(!fieldPassword2.getText().isEmpty() && fieldPassword2.getText() != null && fieldPassword2.getText() != "") &&
    				(!fieldRole.getValue().isEmpty() && fieldRole.getValue() != null && fieldRole.getValue() != "" && fieldRole.getValue() != "-")) {
    			return true;
    		} else {
    			return false;
    		}
    		
		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkAllFields() - " + e.toString());
			return false;
		}
    }
    
    boolean checkSecondField(String field) {
    	
    	try {
			
    		switch(field) {
    			
    		case "email":
    			if (fieldEmail.getText().equals(fieldEmail2.getText())) {
    				return true;
    			} else {
    				return false;
    			}
    		case "password":
    			if (fieldPassword.getText().equals(fieldPassword2.getText())) {
    				return true;
    			} else {
    				return false;
    			}
			default:
				return false;
    		}
    		
		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkSecondField() - " + e.toString());
			return false;
		}
    	
    }
    
    
    
    @FXML
    void initialize() {
    	
    	try {
    		
    		// Añado items al combo
    		
    		fieldRole.getItems().removeAll(fieldRole.getItems());
    		fieldRole.getItems().addAll("-", "Puesto 1", "Puesto 2", "Puesto 3");
    		fieldRole.getSelectionModel().select("-");
    		
    		// TODO: esto habría que meterlo en el .css
    		// Color drawer
    		
    		drawer.setBackground(new Background(new BackgroundFill(Color.rgb(225, 221, 229), CornerRadii.EMPTY, Insets.EMPTY)));
			
		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - initialize() - " + e.toString());
		}
    	
    }

}
