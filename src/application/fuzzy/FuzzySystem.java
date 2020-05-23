package application.fuzzy;

import application.product.StoredProduct;
import application.product.Warehouse;

public class FuzzySystem {
	private static FuzzySystem instance;

	public static FuzzySystem getInstance() {
		if (instance == null) {
			instance = new FuzzySystem();
		}

		return instance;
	}

	private FuzzySystem() {
	}

	public void rate() {
		for (StoredProduct storedProduct : Warehouse.getInstance().getStoredProducts()) {
			double stockRate = storedProduct.getNumOfStock() / Warehouse.getInstance().getMaxNumOfStock();
			double demandRate = storedProduct.getNumOfDemand() / Warehouse.getInstance().getMaxDemandAmount();

			storedProduct.setFuzzyStockAmount(FuzzyAmount.detAmount(stockRate));
			storedProduct.setFuzzyDemandAmount(FuzzyAmount.detAmount(demandRate));
		}
	}
}
