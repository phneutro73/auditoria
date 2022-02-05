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
	
	public ControllerYesNoAlertDialog(double height, double width, String title, String text, String question, String yesButtonTxt, String noButtonTxt, String yesAction, String[] params) {
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
				height = 194;
			} 
			if (width == 0) {
				width = 600;
			}
			
			dialogTitle.setText(title);
			dialogText.setText(text);

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
			ControllerAlertDialog control = new ControllerAlertDialog(0, 0, "Eliminado correctamente",
					"El usuario ha sido eliminado correctamente correctamente");
			loader.setController(control);
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		}
	}
	
	
}
