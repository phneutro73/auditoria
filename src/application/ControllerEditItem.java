package application;

import java.util.Hashtable;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import db.ConsultationPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.CurrentUser;

public class ControllerEditItem {
	
	int itemId;
	CurrentUser currentUser;

	public ControllerEditItem(int itemId, CurrentUser currentUser) {
		this.itemId = itemId;
		this.currentUser = currentUser;
	}

    @FXML
    private Label subtitle;

    @FXML
    private Label lblBarCode;

    @FXML
    private Label lblName;

    @FXML
    private Label lblType;

    @FXML
    private JFXTextField fieldBarCode;

    @FXML
    private Label lblSize;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblQuantity;

    @FXML
    private Label lblShop;

    @FXML
    private JFXComboBox<String> fieldType;

    @FXML
    private JFXComboBox<String> fieldShop;

    @FXML
    private JFXTextField fieldName;

    @FXML
    private JFXTextField fieldSize;

    @FXML
    private JFXTextField fieldPrice;

    @FXML
    private JFXTextField fieldQuantity;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAccept;

    @FXML
    void initialize() {
    	
    	ConsultationPageConnection consultDB = new ConsultationPageConnection();
    	
    	getTypes(consultDB);
    	
    	getItem(consultDB);
    	
    }
    
    @FXML
    void cancelExit(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
    }

    @FXML
    void updateItem(ActionEvent event) {
    	
    }
    
    void getTypes(ConsultationPageConnection consultDB) {
    	
    	List<String> types = consultDB.listItemTypes();
    	fieldType.getItems().removeAll(fieldType.getItems());
    	fieldType.getItems().addAll(types);
    	fieldType.getSelectionModel().select("-");
    	
    }
    
    void getItem(ConsultationPageConnection consultDB) {
    	
    	Hashtable<String, Object> item = consultDB.getItem(itemId);
    	
    	String name = (String) item.get("name");
    	String barCode = (String) item.get("itemBarCode");
    	String strType = (String) item.get("type");
    	String size = (String) item.get("size");
    	String price = item.get("price").toString();
    	
    	fieldBarCode.setText(barCode);
    	fieldName.setText(name);
    	fieldType.getSelectionModel().select(strType);
    	fieldSize.setText(size);
    	fieldPrice.setText(price);
    	
    }

}
