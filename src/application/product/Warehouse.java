package application.product;

import application.Log;
import application.Main;
import application.Utilities;
import application.fuzzy.FuzzyAmount;

public class Warehouse {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int maxNumOfStock;

	public int getMaxNumOfStock() {
		return maxNumOfStock;
	}

	public void setMaxNumOfStock(int maxNumOfStock) {
		this.maxNumOfStock = maxNumOfStock;
	}

	private StoredProductList storedProducts;

	public StoredProductList getStoredProducts() {
		return storedProducts;
	}

	public void setStoredProducts(StoredProductList storedProducts) {
		this.storedProducts = storedProducts;
	}

	private static Warehouse instance;

	public static Warehouse getInstance() {
		if (instance == null) {
			instance = new Warehouse();
		}

		return instance;
	}

	private Warehouse() {
		this.maxNumOfStock = Main.DefaultNumOfStock_A + Main.DefaultNumOfStock_B + Main.DefaultNumOfStock_C
				+ Main.DefaultNumOfStock_D + Main.DefaultNumOfAddStock;

		storedProducts = new StoredProductList();
		for (Main.DefaultStoredProduct defaultStoredProduct : Main.DefaultStoredProduct.values()) {
			storedProducts.add(defaultStoredProduct.getStoredProduct());
		}
	}

	public int getTotalStockAmount() {
		int storedAmount = 0;

		for (StoredProduct storedProduct : storedProducts) {
			storedAmount += storedProduct.getNumOfStock();
		}

		return storedAmount;
	}

	public int getMaxDemandAmount() {
		return maxNumOfStock - getTotalStockAmount();
	}

	public void order() {
		for (StoredProduct storedProduct : Warehouse.getInstance().getStoredProducts()) {
			double stockRate = Utilities.getInstance().divide(storedProduct.getNumOfStock(),
					Warehouse.getInstance().getMaxNumOfStock());

			FuzzyAmount fuzzyOrderAmount = FuzzyAmount.NOTHING;
			FuzzyAmount fuzzyStockAmount = FuzzyAmount.detAmount(stockRate);

			Log.getInstance().add("******************************************");
			Log.getInstance().add("* " + storedProduct.getProduct().getName());
			Log.getInstance().add("******************************************");
			Log.getInstance().add("Fuzzified stock amount: " + fuzzyStockAmount.toString());
			Log.getInstance().add("Fuzzified demand amount: " + storedProduct.getFuzzyDemandAmount().toString());

			// Stock should exceed demand by 1 step
			fuzzyOrderAmount = storedProduct.getFuzzyDemandAmount().getNextHigherAmount();

			if (fuzzyOrderAmount.getValue() <= fuzzyStockAmount.getValue()) {
				fuzzyOrderAmount = FuzzyAmount.NOTHING;
			}
			
			Log.getInstance().add("Fuzzified order amount: " + fuzzyOrderAmount.toString());

			if (!fuzzyOrderAmount.equals(FuzzyAmount.NOTHING)) {
				// TODO: Implement order amount calculation
			}
		}
	}
}
