package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.ModelItemTable;

public class ControllerConsultationPage {
	
	double height;
	double width;
	int currentUser;
	
	public ControllerConsultationPage(double height, double width, int currentUser) {
		this.height = height;
		this.width = width;
		this.currentUser = currentUser;
	}

    @FXML
    private VBox drawer;

    @FXML
    private HBox btnMenu;

    @FXML
    private HBox btnSales;

    @FXML
    private HBox btnConsultation;

    @FXML
    private HBox btnAddItem;

    @FXML
    private HBox btnStatistics;

    @FXML
    private HBox btnAdministrator;

    @FXML
    private VBox body;
    
    @FXML
    private VBox vBoxButtons;

    @FXML
    private JFXTextField txtItemSearch;

    @FXML
    private TableView<ModelItemTable> itemTable;

    @FXML
    private TableColumn<ModelItemTable, String> idItemTable;

    @FXML
    private TableColumn<ModelItemTable, String> cbItemTable;

    @FXML
    private TableColumn<ModelItemTable, String> nameItemTable;

    @FXML
    private TableColumn<ModelItemTable, String> typeItemTable;

    @FXML
    private TableColumn<ModelItemTable, String> sizeItemTable;

    @FXML
    private TableColumn<ModelItemTable, String> quantityItemTable;

    @FXML
    private TableColumn<ModelItemTable, String> inShopItemTable;

    @FXML
    private TableColumn<ModelItemTable, String> reservationItemTable;

    @FXML
    private TableColumn<ModelItemTable, String> priceItemTable;

    @FXML
    private JFXButton btnEditItem;

    @FXML
    private JFXButton btnDeleteItem;

    @FXML
    private JFXButton btnDetailsItem;
    
    boolean isExpanded;
    int idItem;
    
    @FXML
    void initialize() {
    	
    	getItems();
    	
    	isExpanded = false;
    	txtItemSearch.setFocusTraversable(false);
    	itemTable.setFocusTraversable(false);
    	
    	btnEditItem.setDisable(true);
    	btnDeleteItem.setDisable(true);
    	btnDetailsItem.setDisable(true);
    	
    }
    
    @FXML
    void deleteItem(MouseEvent event) {

    }

    @FXML
    void detailsItem(MouseEvent event) {

    }

    @FXML
    void editItem(MouseEvent event) {

    }

    @FXML
    void expandMenu(MouseEvent event) {
    	if (isExpanded) {
			drawer.setPrefWidth(60);
			isExpanded = false;
			
			vBoxButtons.setMinWidth(108);
			vBoxButtons.setMaxWidth(108);
			vBoxButtons.prefWidth(108);
		} else {
			drawer.setPrefWidth(190);
			isExpanded = true;

			vBoxButtons.setMinWidth(108);
			vBoxButtons.setMaxWidth(108);
			vBoxButtons.prefWidth(108);
		}
    }

    @FXML
    void goToAddItems(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddItemsPage.fxml"));
		ControllerAddItemsPage control = new ControllerAddItemsPage(0, 0, 0);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void goToAdministratorPage(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/administrator_page.fxml"));
		controller_administrator control = new controller_administrator(0, 0, 0);
		loader.setController(control);
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void hideMenu(MouseEvent event) {
    	if (isExpanded) {
    		drawer.setPrefWidth(60);
    		isExpanded = false;

			vBoxButtons.setMinWidth(108);
			vBoxButtons.setMaxWidth(108);
			vBoxButtons.prefWidth(108);
    		
    	}
    }

    @FXML
    void itemSelected(MouseEvent event) {
    	hideMenu(event);
    }
    
    void getItems() {
    	
    }

}
