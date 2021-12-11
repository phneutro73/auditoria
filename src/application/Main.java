package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/login.fxml"));
			controller_login control = new controller_login();
			loader.setController(control);
			Parent root = loader.load();
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root, 400, 400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setScene(scene);
			primaryStage.setScene(new Scene(root));
			//primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
