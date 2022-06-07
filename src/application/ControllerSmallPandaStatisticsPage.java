package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ControllerSmallPandaStatisticsPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane parent;

    @FXML
    private Label subtitle;

    @FXML
    private Label lblMonthSales;

    @FXML
    private Label lblTotalSales;

    @FXML
    private Label lblMonthEarnings;

    @FXML
    private Label lblTotalEarnings;

    @FXML
    private Label lblWorkersNum;

    @FXML
    private Label lblItemsSold;

    @FXML
    private Label lblItemsStock;

    @FXML
    private Label lblItemsSoldToday;

    @FXML
    private LineChart<String, Double> lineChartDailyEarnigns;

    @FXML
    private LineChart<String, Double> lineChartDailySales;

    // TODO: Hacer modelo tabla
    @FXML
    private TableView<?> shopsEarningsTable;

    @FXML
    private TableColumn<?, ?> colShopsShopsEarningsTable;

    @FXML
    private TableColumn<?, ?> colEarningssShopsEarningsTable;

    @FXML
    void initialize() {
        assert parent != null : "fx:id=\"parent\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert subtitle != null : "fx:id=\"subtitle\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lblMonthSales != null : "fx:id=\"lblMonthSales\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lblTotalSales != null : "fx:id=\"lblTotalSales\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lblMonthEarnings != null : "fx:id=\"lblMonthEarnings\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lblTotalEarnings != null : "fx:id=\"lblTotalEarnings\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lblWorkersNum != null : "fx:id=\"lblWorkersNum\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lblItemsSold != null : "fx:id=\"lblItemsSold\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lblItemsStock != null : "fx:id=\"lblItemsStock\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lblItemsSoldToday != null : "fx:id=\"lblItemsSoldToday\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lineChartDailyEarnigns != null : "fx:id=\"lineChartDailyEarnigns\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert lineChartDailySales != null : "fx:id=\"lineChartDailySales\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert shopsEarningsTable != null : "fx:id=\"shopsEarningsTable\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert colShopsShopsEarningsTable != null : "fx:id=\"colShopsShopsEarningsTable\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";
        assert colEarningssShopsEarningsTable != null : "fx:id=\"colEarningssShopsEarningsTable\" was not injected: check your FXML file 'SmallPandaStatisticsPage.fxml'.";

    }
}
