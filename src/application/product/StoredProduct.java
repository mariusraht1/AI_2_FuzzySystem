package application.product;

import application.fuzzy.FuzzyAmount;

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
	
	public double getPrice() {
		return (product.getPrice() * numOfStock);
	}

	private FuzzyAmount fuzzyStockAmount;

	public FuzzyAmount getFuzzyStockAmount() {
		return fuzzyStockAmount;
	}

	public void setFuzzyStockAmount(FuzzyAmount fuzzyStockAmount) {
		this.fuzzyStockAmount = fuzzyStockAmount;
	}

	private FuzzyAmount fuzzyDemandAmount;

	public FuzzyAmount getFuzzyDemandAmount() {
		return fuzzyDemandAmount;
	}

	public void setFuzzyDemandAmount(FuzzyAmount fuzzyDemandAmount) {
		this.fuzzyDemandAmount = fuzzyDemandAmount;
	}

	public StoredProduct(Product product, int amount) {
		this.product = product;
		this.numOfStock = amount;
	}


}
