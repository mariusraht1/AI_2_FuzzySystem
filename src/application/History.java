package application;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import application.Utilities.OSType;
import application.fuzzy.FuzzyAmount;
import application.fuzzy.FuzzySystem;
import application.product.StoredProduct;
import application.product.Warehouse;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
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

	private List<String[]> development = new ArrayList<String[]>();

	private File file = new File("history.csv");

	public File getFile() {
		return file;
	}

	private History() {
		try {
			file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			file = new File(file.getParentFile().getPath() + "//history.txt");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		initHeader();
	};

	private void initHeader() {
		development.add(new String[] { "Round", "Product", "Stock", "Demand", "Demand Rate", "Fuzz. Demand",
				"New Stock", "New Stock Rate", "Fuzz. New Stock", "Fuzz. Order Amount", "Order Amount Factor",
				"Order Amount" });
	}

	public void clear(ArrayList<XYChart<String, Integer>> charts, ArrayList<Series<String, Integer>> seriesList) {
		development.clear();
		initHeader();

		for (XYChart<String, Integer> xyChart : charts) {
			CategoryAxis categoryAxis = (CategoryAxis) xyChart.getXAxis();
			categoryAxis.getCategories().clear();
		}

		for (Series<String, Integer> serie : seriesList) {
			serie.getData().clear();
		}

		for (StoredProduct storedProduct : Warehouse.getInstance().getStoredProducts()) {
			storedProduct.setNumOfDemand(0);
		}

		add(Main.DefaultStoredProduct.PRODUCT_A.getStoredProduct(), seriesList.get(0), seriesList.get(1));
		add(Main.DefaultStoredProduct.PRODUCT_B.getStoredProduct(), seriesList.get(2), seriesList.get(3));
		add(Main.DefaultStoredProduct.PRODUCT_C.getStoredProduct(), seriesList.get(4), seriesList.get(5));
		add(Main.DefaultStoredProduct.PRODUCT_D.getStoredProduct(), seriesList.get(6), seriesList.get(7));
	}

	public void add(StoredProduct storedProduct, Series<String, Integer> series_stock,
			Series<String, Integer> series_demand) {
		int round = FuzzySystem.getInstance().getRound();
		String productName = storedProduct.getProduct().getName();
		int stock = storedProduct.getNumOfStock();
		int demand = storedProduct.getNumOfDemand();

		development.add(new String[] { String.valueOf(round), productName, String.valueOf(stock),
				String.valueOf(demand), String.valueOf(0.00), FuzzyAmount.NOTHING.toString(), String.valueOf(0),
				String.valueOf(0.00), FuzzyAmount.NOTHING.toString(), FuzzyAmount.NOTHING.toString(),
				String.valueOf(0.00), String.valueOf(0) });

		series_stock.getData().add(new Data<String, Integer>(String.valueOf(round), storedProduct.getNumOfStock()));
		series_demand.getData().add(new Data<String, Integer>(String.valueOf(round), storedProduct.getNumOfDemand()));
	}

	public void add(StoredProduct storedProduct, Series<String, Integer> series_stock,
			Series<String, Integer> series_demand, double demandRate, FuzzyAmount fuzzyDemand, int newStock,
			double newStockRate, FuzzyAmount fuzzyNewStock, FuzzyAmount fuzzyOrderAmount, double orderAmountFactor,
			int orderAmount, int planStock) {
		int round = FuzzySystem.getInstance().getRound();
		String productName = storedProduct.getProduct().getName();
		int stock = storedProduct.getNumOfStock();
		int demand = storedProduct.getNumOfDemand();

		demandRate = Utilities.getInstance().formatDouble("0.00", demandRate);
		newStockRate = Utilities.getInstance().formatDouble("0.00", newStockRate);
		orderAmountFactor = Utilities.getInstance().formatDouble("0.00", orderAmountFactor);

		development.add(new String[] { String.valueOf(round), productName, String.valueOf(stock),
				String.valueOf(demand), String.valueOf(demandRate), fuzzyDemand.toString(), String.valueOf(newStock),
				String.valueOf(newStockRate), fuzzyNewStock.toString(), fuzzyOrderAmount.toString(),
				String.valueOf(orderAmountFactor), String.valueOf(orderAmount) });

		series_stock.getData().add(new Data<String, Integer>(String.valueOf(round), storedProduct.getNumOfStock()));
		series_demand.getData().add(new Data<String, Integer>(String.valueOf(round), storedProduct.getNumOfDemand()));
	}

	public void export() {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			for (String[] x : development) {
				for (int i = 0; i < x.length; i++) {
					stringBuilder.append(x[i]);
					if (i < x.length - 1) {
						stringBuilder.append(";");
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
