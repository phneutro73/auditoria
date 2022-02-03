package application;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerAlertDialog {

	double height;
	double width;
	String title;
	String text;

	public ControllerAlertDialog(double height, double width, String title, String text) {

		super();

		if (height == 0) {
			this.height = 111;
		} else {
			this.height = height;
		}
		if (width == 0) {
			this.width = 200;
		} else {
			this.width = width;
		}

		this.title = title;
		this.text = text;
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
	private JFXButton btnAccept;

	private double xOffSet = 0;
	private double yOffSet = 0;

	@FXML
	void closeScreen(MouseEvent event) {
		Stage stage = (Stage) btnAccept.getScene().getWindow();
		stage.close();
	}

	@FXML
	void initialize() {

		try {

			dialogTitle.setText(title);
			dialogText.setText(text);

			parent.setPrefSize(width, height);
			parent.setMinSize(width, height);
			parent.setMaxSize(width, height);

		} catch (Exception e) {
			System.out.println("ERROR: CoontrollerAlertDialog.java - initialize() - " + e.toString());
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

}
