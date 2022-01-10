package application;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller_LoginAlertDialog {

	@FXML
	private AnchorPane parent;

	@FXML
	private Label btnClose;

	@FXML
	private JFXButton btnAccept;

	private double xOffSet = 0;
	private double yOffSet = 0;

	@FXML
	void closeScreen(MouseEvent event) {
		Stage stage = (Stage) btnAccept.getScene().getWindow();
		stage.close();
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

}
