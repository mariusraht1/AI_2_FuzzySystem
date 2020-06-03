package application;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import application.Utilities.OSType;
import application.fuzzy.FuzzySystem;
import application.product.StoredProduct;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class History {
	private static History instance;

	public static History getInstance() {
		if (instance == null) {
			instance = new History();
		}

		return instance;
	}

	private List<int[]> development = new ArrayList<int[]>();

	private File file = new File("history.txt");

	public File getFile() {
		return file;
	}

	private History() {
	};

	public void clear(Series<String, Integer> series_stock_a, Series<String, Integer> series_demand_a) {
		development.clear();
		series_stock_a.getData().clear();
		series_demand_a.getData().clear();
	}

	public void add(StoredProduct storedProduct, Series<String, Integer> series_stock_a,
			Series<String, Integer> series_demand_a) {
		int round = FuzzySystem.getInstance().getRound();

		development.add(new int[] { round, storedProduct.getNumOfStock(), storedProduct.getNumOfDemand() });

		series_stock_a.getData().add(new Data<String, Integer>(String.valueOf(round), storedProduct.getNumOfStock()));
		series_demand_a.getData().add(new Data<String, Integer>(String.valueOf(round), storedProduct.getNumOfDemand()));
	}

	public void export() {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			for (int[] x : development) {
				for (int i = 0; i < x.length; i++) {
					stringBuilder.append(x[i]);
					if (i < x.length - 1) {
						stringBuilder.append(",");
					}
				}
				stringBuilder.append("\n");
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.append(stringBuilder);
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showExport() {
		try {
			if (Utilities.getInstance().getOperatingSystemType().equals(OSType.Windows)) {
				Runtime.getRuntime().exec("explorer.exe /select, " + file);
			} else {
				Desktop.getDesktop().open(History.getInstance().getFile());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
