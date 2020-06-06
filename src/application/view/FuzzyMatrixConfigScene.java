package application.view;

import application.fuzzy.FuzzyAmount;
import application.fuzzy.FuzzySystem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FuzzyMatrixConfigScene {
	// Stock / Demand
	@FXML
	private GridPane gp_stock_demand;

	private static MainScene mainScene;

	public static void setMainScene(MainScene mainScene) {
		FuzzyMatrixConfigScene.mainScene = mainScene;
	}

	@FXML
	private void initialize() {
		for (int x = 0; x < FuzzyAmount.values().length; x++) { // Stock
			for (int y = 0; y < FuzzyAmount.values().length; y++) { // Demand
				FuzzyAmount fuzzyOrderAmount = FuzzySystem.getInstance().getFuzzyOrderAmount(FuzzyAmount.getByID(x),
						FuzzyAmount.getByID(y));

				ComboBox<FuzzyAmount> comboBox = new ComboBox<FuzzyAmount>(
						FXCollections.observableArrayList(FuzzyAmount.values()));
				comboBox.getSelectionModel().select(fuzzyOrderAmount);
				gp_stock_demand.add(comboBox, x + 2, y + 2);
			}
		}
	}

	@FXML
	private void onAction_btnSetConfig() {
		FuzzyAmount[][] fuzzyMatrix = new FuzzyAmount[FuzzyAmount.values().length][FuzzyAmount.values().length];

		for (Node node : gp_stock_demand.getChildren()) {
			if (node instanceof ComboBox) {
				@SuppressWarnings("unchecked")
				ComboBox<FuzzyAmount> comboBox = (ComboBox<FuzzyAmount>) node;
				int y = GridPane.getRowIndex(comboBox);
				int x = GridPane.getColumnIndex(comboBox);

				fuzzyMatrix[x - 2][y - 2] = comboBox.getValue();
			}
		}

		FuzzySystem.getInstance().setFuzzyMatrix(fuzzyMatrix);

		MainScene.setCanceledFuzzyMatrixConfig(false);
		Stage dialog = (Stage) gp_stock_demand.getScene().getWindow();
		dialog.close();
		
		mainScene.reset();
	}

	@FXML
	private void onAction_btnCancel() {
		MainScene.setCanceledFuzzyMatrixConfig(true);

		Stage dialog = (Stage) gp_stock_demand.getScene().getWindow();
		dialog.close();
	}

	@FXML
	private void onAction_btnReset() {
		initialize();
	}
}
