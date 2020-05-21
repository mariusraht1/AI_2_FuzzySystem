package application.view;

import application.History;
import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainScene {
	// TODO:
	// > Configurable total amount of warehouse
	// > Configurable start amount of products
	// > Report function of sold amounts of products
	// > Graph to view development of sold, ordered and stored amounts

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
	private LineChart<Integer, Integer> lc_stockAmount;
	@FXML
	private ListView<String> lv_console;

	@FXML
	private void initialize() {
		initGUI();
		initEvents();
	}

	private void initGUI() {
		tf_numOfStock_a.setText(String.valueOf(Main.DefaultNumOfStock_A));
		tf_numOfStock_b.setText(String.valueOf(Main.DefaultNumOfStock_B));
		tf_numOfStock_c.setText(String.valueOf(Main.DefaultNumOfStock_C));
		tf_numOfStock_d.setText(String.valueOf(Main.DefaultNumOfStock_D));
		tf_numOfAddStock.setText(String.valueOf(Main.DefaultNumOfAddStock));

		update_lbl_totalMaxStock();
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
		int numOfAddStock = 0;
		try {
			numOfAddStock = Integer.parseInt(tf_numOfAddStock.getText());
		} catch (Exception e) {
		}

		int numOfStock_a = 0;
		try {
			numOfStock_a = Integer.parseInt(tf_numOfStock_a.getText());
		} catch (Exception e) {
		}

		int numOfStock_b = 0;
		try {
			numOfStock_b = Integer.parseInt(tf_numOfStock_b.getText());
		} catch (Exception e) {
		}

		int numOfStock_c = 0;
		try {
			numOfStock_c = Integer.parseInt(tf_numOfStock_c.getText());
		} catch (Exception e) {
		}

		int numOfStock_d = 0;
		try {
			numOfStock_d = Integer.parseInt(tf_numOfStock_d.getText());
		} catch (Exception e) {
		}

		int maxNumOfStock = numOfAddStock + numOfStock_a + numOfStock_b + numOfStock_c + numOfStock_d;
		lbl_totalMaxStock.setText("(" + String.valueOf(maxNumOfStock) + ")");
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
	private void onAction_btnSetInitValues() {
		try {
			int numOfAddStock = Integer.parseInt(tf_numOfAddStock.getText());
			int numOfStock_a = Integer.parseInt(tf_numOfStock_a.getText());
			int numOfStock_b = Integer.parseInt(tf_numOfStock_b.getText());
			int numOfStock_c = Integer.parseInt(tf_numOfStock_c.getText());
			int numOfStock_d = Integer.parseInt(tf_numOfStock_d.getText());

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
				initialize();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void onAction_btnSetNumOfStock() {

	}
}
