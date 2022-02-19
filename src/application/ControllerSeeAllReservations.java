package application;

import com.jfoenix.controls.JFXTextField;

import db.ConsultationPageConnection;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.CurrentUser;
import models.ModelAllReservationsTable;
import models.ModelScheduleTable;

public class ControllerSeeAllReservations {
	
	CurrentUser currentUser;
	
	public ControllerSeeAllReservations(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}

	 @FXML
    private AnchorPane parent;

    @FXML
    private Label subtitle;

    @FXML
    private JFXTextField txtCurrentReservationsSearch;

    @FXML
    private TableView<ModelAllReservationsTable> currentReservationsTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> idCurrentReservationsTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> emailCurrentReservationsTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> dniCurrentReservationsTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> shopIdCurrentReservationsTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> barCodeCurrentReservationsTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> quantityCurrentReservationsTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> priceCurrentReservationsTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> dateCurrentReservationsTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> timeCurrentReservationsTable;

    @FXML
    private JFXTextField txtReservationHistoryTable;

    @FXML
    private TableView<ModelAllReservationsTable> reservationHistoryTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> idReservationHistoryTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> emailReservationHistoryTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> dniReservationHistoryTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> shopIdReservationHistoryTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> barCodeReservationHistoryTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> quantityReservationHistoryTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> priceReservationHistoryTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> dateReservationHistoryTable;

    @FXML
    private TableColumn<ModelAllReservationsTable, String> timeReservationHistoryTable;
    
    int idReservationSelected;

    @FXML
    void initialize() {
    	
    	ConsultationPageConnection consultDB = new ConsultationPageConnection();
    	getCurrentReservations(consultDB);
    	getReservationHistory(consultDB);
    }
    
    @FXML
    void selectReservation(MouseEvent event) {

    }
    
    void getCurrentReservations(ConsultationPageConnection consultDB) {
    	
    	ObservableList<ModelAllReservationsTable> obList = consultDB.getAllReservationsTable(false);
    			
    	idCurrentReservationsTable.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
    	emailCurrentReservationsTable.setCellValueFactory(new PropertyValueFactory<>("email"));
    	dniCurrentReservationsTable.setCellValueFactory(new PropertyValueFactory<>("dni"));
    	shopIdCurrentReservationsTable.setCellValueFactory(new PropertyValueFactory<>("shop"));
    	barCodeCurrentReservationsTable.setCellValueFactory(new PropertyValueFactory<>("barCode"));
    	quantityCurrentReservationsTable.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    	priceCurrentReservationsTable.setCellValueFactory(new PropertyValueFactory<>("price"));
    	dateCurrentReservationsTable.setCellValueFactory(new PropertyValueFactory<>("endReservationDate"));
    	timeCurrentReservationsTable.setCellValueFactory(new PropertyValueFactory<>("endReservationTime"));
    	
    	currentReservationsTable.setItems(obList);

		FilteredList<ModelAllReservationsTable> filteredCurrentReservationsData = new FilteredList<>(obList, b -> true);
		txtCurrentReservationsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredCurrentReservationsData.setPredicate(currentReservationsSearchModel -> {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				}
				String searchScheduleKeyword = newValue.toLowerCase();

				if (currentReservationsSearchModel.getStrReservationId() != null
						&& currentReservationsSearchModel.getStrReservationId().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (currentReservationsSearchModel.getEmail() != null
						&& currentReservationsSearchModel.getEmail().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (currentReservationsSearchModel.getDni() != null
						&& currentReservationsSearchModel.getDni().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (currentReservationsSearchModel.getShop() != null
						&& currentReservationsSearchModel.getShop().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (currentReservationsSearchModel.getBarCode() != null
						&& currentReservationsSearchModel.getBarCode().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (currentReservationsSearchModel.getStrEndReservationDate() != null
						&& currentReservationsSearchModel.getStrEndReservationDate().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (currentReservationsSearchModel.getStrEndReservationTime() != null
						&& currentReservationsSearchModel.getStrEndReservationTime().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else {
					return false;
				}
			});
		});

		SortedList<ModelAllReservationsTable> sortedCurrentReservationsData = new SortedList<>(filteredCurrentReservationsData);
		sortedCurrentReservationsData.comparatorProperty().bind(currentReservationsTable.comparatorProperty());
		currentReservationsTable.setItems(sortedCurrentReservationsData);

    }
    
    void getReservationHistory(ConsultationPageConnection consultDB) {
    	
    	ObservableList<ModelAllReservationsTable> obList = consultDB.getAllReservationsTable(true);
		
    	idReservationHistoryTable.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
    	emailReservationHistoryTable.setCellValueFactory(new PropertyValueFactory<>("email"));
    	dniReservationHistoryTable.setCellValueFactory(new PropertyValueFactory<>("dni"));
    	shopIdReservationHistoryTable.setCellValueFactory(new PropertyValueFactory<>("shop"));
    	barCodeReservationHistoryTable.setCellValueFactory(new PropertyValueFactory<>("barCode"));
    	quantityReservationHistoryTable.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    	priceReservationHistoryTable.setCellValueFactory(new PropertyValueFactory<>("price"));
    	dateReservationHistoryTable.setCellValueFactory(new PropertyValueFactory<>("endReservationDate"));
    	timeReservationHistoryTable.setCellValueFactory(new PropertyValueFactory<>("endReservationTime"));
    	
    	reservationHistoryTable.setItems(obList);

		FilteredList<ModelAllReservationsTable> filtedRedreservationHistoryData = new FilteredList<>(obList, b -> true);
		txtReservationHistoryTable.textProperty().addListener((observable, oldValue, newValue) -> {
			filtedRedreservationHistoryData.setPredicate(reservationHistorySearchModel -> {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				}
				String searchScheduleKeyword = newValue.toLowerCase();

				if (reservationHistorySearchModel.getStrReservationId() != null
						&& reservationHistorySearchModel.getStrReservationId().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (reservationHistorySearchModel.getEmail() != null
						&& reservationHistorySearchModel.getEmail().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (reservationHistorySearchModel.getDni() != null
						&& reservationHistorySearchModel.getDni().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (reservationHistorySearchModel.getShop() != null
						&& reservationHistorySearchModel.getShop().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (reservationHistorySearchModel.getBarCode() != null
						&& reservationHistorySearchModel.getBarCode().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (reservationHistorySearchModel.getStrEndReservationDate() != null
						&& reservationHistorySearchModel.getStrEndReservationDate().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else if (reservationHistorySearchModel.getStrEndReservationTime() != null
						&& reservationHistorySearchModel.getStrEndReservationTime().toLowerCase().indexOf(searchScheduleKeyword) > -1) {
					return true;
				} else {
					return false;
				}
			});
		});

		SortedList<ModelAllReservationsTable> sortedReservationHistoryData = new SortedList<>(filtedRedreservationHistoryData);
		sortedReservationHistoryData.comparatorProperty().bind(reservationHistoryTable.comparatorProperty());
		reservationHistoryTable.setItems(sortedReservationHistoryData);
    }

}
