package application.product;

import application.History;
import application.Log;
import application.fuzzy.FuzzyAmount;
import application.fuzzy.FuzzySystem;
import javafx.scene.chart.XYChart.Series;
import library.MathManager;

public class StoredProduct {
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	private int numOfStock;

	public int getNumOfStock() {
		return numOfStock;
	}

	public void setNumOfStock(int numOfStock) {
		this.numOfStock = numOfStock;
	}

	public void increaseNumOfStock(int increase) {
		this.numOfStock += increase;
	}

	public void decreaseNumOfStock(int decrease) {
		this.numOfStock -= decrease;
	}

	private int numOfDemand;

	public int getNumOfDemand() {
		return numOfDemand;
	}

	public void setNumOfDemand(int numOfDemand) {
		this.numOfDemand = numOfDemand;
	}

	private FuzzyAmount fuzzyStockAmount;

	public FuzzyAmount getFuzzyStockAmount() {
		return fuzzyStockAmount;
	}

	public void setFuzzyStockAmount(FuzzyAmount fuzzyStockAmount) {
		this.fuzzyStockAmount = fuzzyStockAmount;
	}

	public StoredProduct(Product product, int amount) {
		this.product = product;
		this.numOfStock = amount;
	}

	public void order(int numOfDemand, Series<String, Integer> series_stock, Series<String, Integer> series_demand) {
		int oldStockAmount = getNumOfStock();

		setNumOfDemand(numOfDemand);
		int demandAmount = numOfDemand;

		decreaseNumOfStock(numOfDemand);
		int newStockAmount = getNumOfStock();

		double orderFactor = 0;
		double stockRate = MathManager.getInstance().divide(newStockAmount, oldStockAmount);
		FuzzyAmount fuzzyStockAmount = FuzzyAmount.getByRate(stockRate);

		double demandRate = MathManager.getInstance().divide(demandAmount, oldStockAmount);
		FuzzyAmount fuzzyDemandAmount = FuzzyAmount.getByRate(demandRate);

		Log.getInstance().add("==> " + getProduct().getName());
		Log.getInstance().add("Demand amount: " + fuzzyDemandAmount.toString() + " (" + demandAmount + ")");
		Log.getInstance().add("Stock amount: " + fuzzyStockAmount.toString() + " (" + newStockAmount + ")");

		FuzzyAmount fuzzyOrderAmount = FuzzySystem.getInstance().getFuzzyOrderAmount(fuzzyStockAmount,
				fuzzyDemandAmount);

		int orderAmount = 0;
		if (!fuzzyOrderAmount.equals(FuzzyAmount.NOTHING)) {
			// sqrt( Demand * (1 + Median of fuzzyOrderAmount) ^ 2 ) => Suppress negative
			// results
			orderFactor = 1 + fuzzyOrderAmount.getMedian();
			orderAmount = (int) Math.sqrt(Math.pow(newStockAmount - (getNumOfDemand() * orderFactor), 2));

			if (Warehouse.getInstance().getNumOfFreeStock() < orderAmount) {
				orderAmount = Warehouse.getInstance().getNumOfFreeStock();
			}

			increaseNumOfStock(orderAmount);
		}

		Log.getInstance().add("Order amount: " + fuzzyOrderAmount.toString() + " (" + orderAmount + ")");
		Log.getInstance().add("New stock amount: " + getNumOfStock());

		History.getInstance().add(this, series_stock, series_demand, demandRate, fuzzyDemandAmount, newStockAmount,
				stockRate, fuzzyStockAmount, fuzzyOrderAmount, orderFactor, orderAmount, newStockAmount);
	}
}
