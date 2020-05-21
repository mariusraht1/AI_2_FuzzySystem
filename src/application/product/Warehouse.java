package application.product;

import java.util.ArrayList;

import application.Main;
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

	private ArrayList<StoredProduct> storedProducts;

	public ArrayList<StoredProduct> getStoredProducts() {
		return storedProducts;
	}

	public void setStoredProducts(ArrayList<StoredProduct> storedProducts) {
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
		int maxNumOfStock = Main.DefaultNumOfStock_A + Main.DefaultNumOfStock_B + Main.DefaultNumOfStock_C
				+ Main.DefaultNumOfStock_D + Main.DefaultNumOfAddStock;

		this.maxNumOfStock = maxNumOfStock;

		storedProducts = new ArrayList<StoredProduct>();
		for (Main.DefaultStoredProduct defaultStoredProduct : Main.DefaultStoredProduct.values()) {
			storedProducts.add(defaultStoredProduct.getStoredProduct());
		}
	}

	public int detTotalStoredAmount() {
		int storedAmount = 0;

		for (StoredProduct storedProduct : storedProducts) {
			storedAmount += storedProduct.getAmount();
		}

		return storedAmount;
	}

	public int detStoredAmount(Product product) {
		int storedAmount = 0;

		for (StoredProduct storedProduct : storedProducts) {
			if (storedProduct.getProduct().equals(product)) {
				storedAmount += storedProduct.getAmount();
			}
		}

		return storedAmount;
	}

	public FuzzyAmount fuzzifyStockAmount() {
		return FuzzyAmount.NOTHING;
	}
}
