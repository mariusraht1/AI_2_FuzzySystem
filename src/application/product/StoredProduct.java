package application.product;

import application.Log;
import application.Utilities;
import application.fuzzy.FuzzyAmount;
import application.fuzzy.FuzzySystem;

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

	public void order(int numOfDemand) {
		int oldStockAmount = getNumOfStock();

		setNumOfDemand(numOfDemand);
		int demandAmount = numOfDemand;

		decreaseNumOfStock(numOfDemand);
		int newStockAmount = getNumOfStock();

		double stockRate = Utilities.getInstance().divide(newStockAmount, oldStockAmount);
		FuzzyAmount fuzzyStockAmount = FuzzyAmount.getByRate(stockRate);

		double demandRate = Utilities.getInstance().divide(demandAmount, oldStockAmount);
		FuzzyAmount fuzzyDemandAmount = FuzzyAmount.getByRate(demandRate);

		Log.getInstance().add("==> " + getProduct().getName());
		Log.getInstance().add("Demand amount: " + fuzzyDemandAmount.toString() + " (" + demandAmount + ")");
		Log.getInstance().add("Stock amount: " + fuzzyStockAmount.toString() + " (" + newStockAmount + ")");

		FuzzyAmount fuzzyOrderAmount = FuzzySystem.getInstance().getFuzzyOrderAmount(fuzzyDemandAmount,
				fuzzyStockAmount);

		int orderAmount = 0;
		if (!fuzzyOrderAmount.equals(FuzzyAmount.NOTHING)) {
			// sqrt( Demand * (1 + Median of fuzzyOrderAmount) ^ 2 )	=> Suppress negative results
			orderAmount = (int) Math
					.sqrt(Math.pow(newStockAmount - (getNumOfDemand() * (1 + fuzzyOrderAmount.getMedian())), 2));

			if (Warehouse.getInstance().getNumOfFreeStock() < orderAmount) {
				orderAmount = Warehouse.getInstance().getNumOfFreeStock();
			}

			increaseNumOfStock(orderAmount);
		}

		Log.getInstance().add("Order amount: " + fuzzyOrderAmount.toString() + " (" + orderAmount + ")");
		Log.getInstance().add("New stock amount: " + getNumOfStock());
	}
}
