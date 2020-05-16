package application.view;

import application.History;
import application.product.Product;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainScene {
    // TODO:
    // > Configurable total amount of warehouse
    // > Configurable start amount of products
    // > Report function of sold amounts of products
    // > Graph to view development of sold, ordered and stored amounts

    @FXML
    private TextField tf_numOfSteps;
    @FXML
    private TextField tf_maxNumOfStock;
    @FXML
    private TextField tf_numOfStock;
    @FXML
    private ListView<Product> lv_products;
    @FXML
    private LineChart<Integer, Integer> lc_stockAmount;
    @FXML
    private ListView<String> lv_console;

    @FXML
    private void initialize() {

    }

    @FXML
    private void onAction_btnReset() {
	initialize();
    }

    @FXML
    private void onAction_btnPlay() {

    }

    @FXML
    private void onAction_btnExport() {
	History.getInstance().export();
	History.getInstance().showExport();
    }

    @FXML
    private void onAction_btnSetMaxNumOfStock() {

    }

    @FXML
    private void onAction_btnSetNumOfStock() {

    }
}
