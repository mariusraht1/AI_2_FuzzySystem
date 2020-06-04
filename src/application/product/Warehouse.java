package application.product;

import application.Main;

public class Warehouse {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int numOfMaxStock;

	public int getNumOfMaxStock() {
		return numOfMaxStock;
	}

	public void setNumOfMaxStock(int numOfMaxStock) {
		this.numOfMaxStock = numOfMaxStock;
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
		setNumOfMaxStock(Main.DefaultNumOfStock_A + Main.DefaultNumOfStock_B + Main.DefaultNumOfStock_C
				+ Main.DefaultNumOfStock_D + Main.DefaultNumOfAddStock);

		storedProducts = new StoredProductList();
		for (Main.DefaultStoredProduct defaultStoredProduct : Main.DefaultStoredProduct.values()) {
			storedProducts.add(defaultStoredProduct.getStoredProduct());
		}
	}

	public int getNumOfStoredStock() {
		int storedAmount = 0;

		for (StoredProduct storedProduct : getStoredProducts()) {
			storedAmount += storedProduct.getNumOfStock();
		}

		return storedAmount;
	}

	public int getNumOfMaxDemand() {
		return (getNumOfMaxStock() - getNumOfStoredStock());
	}

	public int getNumOfFreeStock() {
		return getNumOfMaxStock() - getNumOfStoredStock();
	}
}
