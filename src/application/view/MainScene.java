package application.view;

import java.util.ArrayList;

import application.History;
import application.Log;
import application.Main;
import application.Utilities;
import application.fuzzy.FuzzySystem;
import application.product.Warehouse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainScene {
	@FXML
	private TextField tf_numOfStock_a;
	@FXML
	private TextField tf_numOfStock_b;
	@FXML
	private TextField tf_numOfStock_c;
	@FXML
	private TextField tf_numOfStock_d;
	@FXML
	private TextField tf_numOfAddStock;
	@FXML
	private Label lbl_totalMaxStock;
	@FXML
	private TextField tf_numOfDemand_a;
	@FXML
	private TextField tf_numOfDemand_b;
	@FXML
	private TextField tf_numOfDemand_c;
	@FXML
	private TextField tf_numOfDemand_d;
	@FXML
	private Label lbl_round;
	@FXML
	private LineChart<String, Integer> lc_demand_a;
	@FXML
	private NumberAxis na_y_demand_a;
	@FXML
	private BarChart<String, Integer> bc_stock_a;
	@FXML
	private NumberAxis na_y_stock_a;
	@FXML
	private LineChart<String, Integer> lc_demand_b;
	@FXML
	private NumberAxis na_y_demand_b;
	@FXML
	private BarChart<String, Integer> bc_stock_b;
	@FXML
	private NumberAxis na_y_stock_b;
	@FXML
	private LineChart<String, Integer> lc_demand_c;
	@FXML
	private NumberAxis na_y_demand_c;
	@FXML
	private BarChart<String, Integer> bc_stock_c;
	@FXML
	private NumberAxis na_y_stock_c;
	@FXML
	private LineChart<String, Integer> lc_demand_d;
	@FXML
	private NumberAxis na_y_demand_d;
	@FXML
	private BarChart<String, Integer> bc_stock_d;
	@FXML
	private NumberAxis na_y_stock_d;
	@FXML
	private ListView<String> lv_console;

	private Series<String, Integer> series_stock_a = new Series<String, Integer>();
	private Series<String, Integer> series_demand_a = new Series<String, Integer>();
	private Series<String, Integer> series_stock_b = new Series<String, Integer>();
	private Series<String, Integer> series_demand_b = new Series<String, Integer>();
	private Series<String, Integer> series_stock_c = new Series<String, Integer>();
	private Series<String, Integer> series_demand_c = new Series<String, Integer>();
	private Series<String, Integer> series_stock_d = new Series<String, Integer>();
	private Series<String, Integer> series_demand_d = new Series<String, Integer>();

	private static boolean canceledFuzzyMatrixConfig;

	public static boolean isCanceledFuzzyMatrixConfig() {
		return canceledFuzzyMatrixConfig;
	}

	public static void setCanceledFuzzyMatrixConfig(boolean canceledFuzzyMatrixConfig) {
		MainScene.canceledFuzzyMatrixConfig = canceledFuzzyMatrixConfig;
	}

	@FXML
	private void initialize() {
		Log.getInstance().setOutputControl(lv_console);
		FuzzySystem.getInstance().setRound(0);

		initChart();
		initHistory();
		initGUI();
		initEvents();
	}

	private void initChart() {
		if (!bc_stock_a.getData().contains(series_stock_a)) {
			bc_stock_a.getData().add(series_stock_a);
		}
		na_y_stock_a.setUpperBound(Warehouse.getInstance().getNumOfMaxStock());

		if (!lc_demand_a.getData().contains(series_demand_a)) {
			lc_demand_a.getData().add(series_demand_a);
		}
		na_y_demand_a.setUpperBound(Warehouse.getInstance().getNumOfMaxStock());

		if (!bc_stock_b.getData().contains(series_stock_b)) {
			bc_stock_b.getData().add(series_stock_b);
		}
		na_y_stock_b.setUpperBound(Warehouse.getInstance().getNumOfMaxStock());

		if (!lc_demand_b.getData().contains(series_demand_b)) {
			lc_demand_b.getData().add(series_demand_b);
		}
		na_y_demand_b.setUpperBound(Warehouse.getInstance().getNumOfMaxStock());

		if (!bc_stock_c.getData().contains(series_stock_c)) {
			bc_stock_c.getData().add(series_stock_c);
		}
		na_y_stock_c.setUpperBound(Warehouse.getInstance().getNumOfMaxStock());

		if (!lc_demand_c.getData().contains(series_demand_c)) {
			lc_demand_c.getData().add(series_demand_c);
		}
		na_y_demand_c.setUpperBound(Warehouse.getInstance().getNumOfMaxStock());

		if (!bc_stock_d.getData().contains(series_stock_d)) {
			bc_stock_d.getData().add(series_stock_d);
		}
		na_y_stock_d.setUpperBound(Warehouse.getInstance().getNumOfMaxStock());

		if (!lc_demand_d.getData().contains(series_demand_d)) {
			lc_demand_d.getData().add(series_demand_d);
		}
		na_y_demand_d.setUpperBound(Warehouse.getInstance().getNumOfMaxStock());
	}

	private void initHistory() {
		ArrayList<XYChart<String, Integer>> xyCharts = new ArrayList<XYChart<String, Integer>>();
		xyCharts.add(bc_stock_a);
		xyCharts.add(lc_demand_a);
		xyCharts.add(bc_stock_b);
		xyCharts.add(lc_demand_b);
		xyCharts.add(bc_stock_c);
		xyCharts.add(lc_demand_c);
		xyCharts.add(bc_stock_d);
		xyCharts.add(lc_demand_d);

		ArrayList<Series<String, Integer>> seriesList = new ArrayList<Series<String, Integer>>();
		seriesList.add(series_stock_a);
		seriesList.add(series_demand_a);
		seriesList.add(series_stock_b);
		seriesList.add(series_demand_b);
		seriesList.add(series_stock_c);
		seriesList.add(series_demand_c);
		seriesList.add(series_stock_d);
		seriesList.add(series_demand_d);

		History.getInstance().clear(xyCharts, seriesList);
	}

	private void initGUI() {
		int numOfMaxStock = Warehouse.getInstance().getNumOfMaxStock();
		int numOfStock_a = Warehouse.getInstance().getStoredProducts()
				.getByName(Main.DefaultStoredProduct.PRODUCT_A.getStoredProduct().getProduct().getName())
				.getNumOfStock();
		int numOfStock_b = Warehouse.getInstance().getStoredProducts()
				.getByName(Main.DefaultStoredProduct.PRODUCT_B.getStoredProduct().getProduct().getName())
				.getNumOfStock();
		int numOfStock_c = Warehouse.getInstance().getStoredProducts()
				.getByName(Main.DefaultStoredProduct.PRODUCT_C.getStoredProduct().getProduct().getName())
				.getNumOfStock();
		int numOfStock_d = Warehouse.getInstance().getStoredProducts()
				.getByName(Main.DefaultStoredProduct.PRODUCT_D.getStoredProduct().getProduct().getName())
				.getNumOfStock();
		int numOfAddStock = numOfMaxStock - (numOfStock_a + numOfStock_b + numOfStock_c + numOfStock_d);

		tf_numOfStock_a.setText(String.valueOf(numOfStock_a));
		tf_numOfStock_b.setText(String.valueOf(numOfStock_b));
		tf_numOfStock_c.setText(String.valueOf(numOfStock_c));
		tf_numOfStock_d.setText(String.valueOf(numOfStock_d));
		tf_numOfAddStock.setText(String.valueOf(numOfAddStock));
		lbl_totalMaxStock.setText(String.valueOf(numOfMaxStock));
		lbl_round.setText("Round " + FuzzySystem.getInstance().getRound());
	}

	private void initEvents() {
		tf_numOfStock_a.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				update_lbl_totalMaxStock();
			}
		});
		tf_numOfStock_b.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				update_lbl_totalMaxStock();
			}
		});
		tf_numOfStock_c.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				update_lbl_totalMaxStock();
			}
		});
		tf_numOfStock_d.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				update_lbl_totalMaxStock();
			}
		});
		tf_numOfAddStock.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				update_lbl_totalMaxStock();
			}
		});
	}

	protected void update_lbl_totalMaxStock() {
		int numOfAddStock = Utilities.getInstance().parseInt(tf_numOfAddStock.getText());
		int numOfStock_a = Utilities.getInstance().parseInt(tf_numOfStock_a.getText());
		int numOfStock_b = Utilities.getInstance().parseInt(tf_numOfStock_b.getText());
		int numOfStock_c = Utilities.getInstance().parseInt(tf_numOfStock_c.getText());
		int numOfStock_d = Utilities.getInstance().parseInt(tf_numOfStock_d.getText());
		int numOfMaxStock = numOfAddStock + numOfStock_a + numOfStock_b + numOfStock_c + numOfStock_d;

		lbl_totalMaxStock.setText(String.valueOf(numOfMaxStock));
	}

	@FXML
	private void onAction_btnReset() {
		FuzzySystem.getInstance().initFuzzyMatrix();
		reset();
	}

	public void reset() {
		Main.DefaultStoredProduct.PRODUCT_A.getStoredProduct().setNumOfStock(Main.DefaultNumOfStock_A);
		Main.DefaultStoredProduct.PRODUCT_B.getStoredProduct().setNumOfStock(Main.DefaultNumOfStock_B);
		Main.DefaultStoredProduct.PRODUCT_C.getStoredProduct().setNumOfStock(Main.DefaultNumOfStock_C);
		Main.DefaultStoredProduct.PRODUCT_D.getStoredProduct().setNumOfStock(Main.DefaultNumOfStock_D);

		initialize();
	}

	@FXML
	private void onAction_btnPlay() {
		int numOfDemand_a = Utilities.getInstance().parseInt(tf_numOfDemand_a.getText());
		int numOfDemand_b = Utilities.getInstance().parseInt(tf_numOfDemand_b.getText());
		int numOfDemand_c = Utilities.getInstance().parseInt(tf_numOfDemand_c.getText());
		int numOfDemand_d = Utilities.getInstance().parseInt(tf_numOfDemand_d.getText());

		int numOfStock_a = Warehouse.getInstance().getStoredProducts()
				.getByName(Main.DefaultStoredProduct.PRODUCT_A.getStoredProduct().getProduct().getName())
				.getNumOfStock();
		int numOfStock_b = Warehouse.getInstance().getStoredProducts()
				.getByName(Main.DefaultStoredProduct.PRODUCT_B.getStoredProduct().getProduct().getName())
				.getNumOfStock();
		int numOfStock_c = Warehouse.getInstance().getStoredProducts()
				.getByName(Main.DefaultStoredProduct.PRODUCT_C.getStoredProduct().getProduct().getName())
				.getNumOfStock();
		int numOfStock_d = Warehouse.getInstance().getStoredProducts()
				.getByName(Main.DefaultStoredProduct.PRODUCT_D.getStoredProduct().getProduct().getName())
				.getNumOfStock();

		if (numOfDemand_a < 0) {
			tf_numOfDemand_a.setText("0");
		} else if (numOfDemand_a > numOfStock_a) {
			tf_numOfDemand_a.setText(String.valueOf(numOfStock_a));
		} else if (numOfDemand_b < 0) {
			tf_numOfDemand_b.setText("0");
		} else if (numOfDemand_b > numOfStock_b) {
			tf_numOfDemand_b.setText(String.valueOf(numOfStock_b));
		} else if (numOfDemand_c < 0) {
			tf_numOfDemand_c.setText("0");
		} else if (numOfDemand_c > numOfStock_c) {
			tf_numOfDemand_c.setText(String.valueOf(numOfStock_c));
		} else if (numOfDemand_d < 0) {
			tf_numOfDemand_d.setText("0");
		} else if (numOfDemand_d > numOfStock_d) {
			tf_numOfDemand_d.setText(String.valueOf(numOfStock_d));
		} else {
			FuzzySystem.getInstance().increaseRound();
			lbl_round.setText("Round " + FuzzySystem.getInstance().getRound());

			Log.getInstance().add("******************************************");
			Log.getInstance().add("* Round " + FuzzySystem.getInstance().getRound());
			Log.getInstance().add("******************************************");

			Main.DefaultStoredProduct.PRODUCT_A.getStoredProduct().order(numOfDemand_a, series_stock_a,
					series_demand_a);
			Main.DefaultStoredProduct.PRODUCT_B.getStoredProduct().order(numOfDemand_b, series_stock_b,
					series_demand_b);
			Main.DefaultStoredProduct.PRODUCT_C.getStoredProduct().order(numOfDemand_c, series_stock_c,
					series_demand_c);
			Main.DefaultStoredProduct.PRODUCT_D.getStoredProduct().order(numOfDemand_d, series_stock_d,
					series_demand_d);

			Log.getInstance().add("******************************************");
			Log.getInstance().add("Maxim. stock: " + Warehouse.getInstance().getNumOfMaxStock());
			Log.getInstance().add("Stored stock: " + Warehouse.getInstance().getNumOfStoredStock());
			Log.getInstance().add("Avail. stock: " + Warehouse.getInstance().getNumOfFreeStock());
			Log.getInstance().add("******************************************");
		}
	}

	@FXML
	private void onAction_btnExport() {
		History.getInstance().export();
		History.getInstance().showExport();
	}

	@FXML
	private void onAction_btnSetInitValues() {
		int numOfAddStock = Utilities.getInstance().parseInt(tf_numOfAddStock.getText());
		int numOfStock_a = Utilities.getInstance().parseInt(tf_numOfStock_a.getText());
		int numOfStock_b = Utilities.getInstance().parseInt(tf_numOfStock_b.getText());
		int numOfStock_c = Utilities.getInstance().parseInt(tf_numOfStock_c.getText());
		int numOfStock_d = Utilities.getInstance().parseInt(tf_numOfStock_d.getText());
		int numOfMaxStock = numOfAddStock + numOfStock_a + numOfStock_b + numOfStock_c + numOfStock_d;

		if (numOfAddStock < 0) {
			tf_numOfAddStock.setText(String.valueOf(Main.DefaultNumOfAddStock));
		} else if (numOfStock_a < 0) {
			tf_numOfStock_a.setText(String.valueOf(Main.DefaultNumOfStock_A));
		} else if (numOfStock_b < 0) {
			tf_numOfStock_b.setText(String.valueOf(Main.DefaultNumOfStock_B));
		} else if (numOfStock_c < 0) {
			tf_numOfStock_c.setText(String.valueOf(Main.DefaultNumOfStock_C));
		} else if (numOfStock_d < 0) {
			tf_numOfStock_d.setText(String.valueOf(Main.DefaultNumOfStock_D));
		} else {
			Warehouse.getInstance().setNumOfMaxStock(numOfMaxStock);
			initialize();
		}
	}

	@FXML
	private void onAction_changeFuzzyMatrix() {
		canceledFuzzyMatrixConfig = true;
		openFuzzyMatrixConfigScene(this);
	}

	private void openFuzzyMatrixConfigScene(MainScene mainScene) {
		try {
			final Stage dialog = new Stage();
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(Main.getPrimaryStage());
			dialog.setTitle("Fuzzy-Entscheidungsmatrix");

			FuzzyMatrixConfigScene.setMainScene(mainScene);

			Scene scene = new Scene(
					FXMLLoader.load(Main.class.getResource("/application/view/FuzzyMatrixConfigScene.fxml")));
			dialog.setScene(scene);
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
