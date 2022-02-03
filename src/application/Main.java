package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	public static Stage stage = null;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/login.fxml"));
			controller_login control = new controller_login();
			loader.setController(control);
			Parent root = loader.load();
			primaryStage.setScene(new Scene(root));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.sizeToScene();
			this.stage = primaryStage;
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
