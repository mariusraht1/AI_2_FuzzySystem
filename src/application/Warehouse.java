package application;

import java.util.ArrayList;

public class Warehouse {
    private String name;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    private int maxStorageAmount;

    public int getMaxStorageAmount() {
	return maxStorageAmount;
    }

    public void setMaxStorageAmount(int maxStorageAmount) {
	this.maxStorageAmount = maxStorageAmount;
    }

    private ArrayList<Product> storedProducts;

    public ArrayList<Product> getStoredProducts() {
	return storedProducts;
    }

    public void setStoredProducts(ArrayList<Product> storedProducts) {
	this.storedProducts = storedProducts;
    }

    public Warehouse(String name, int maxStorageAmount) {
	this.setName(name);
	this.maxStorageAmount = maxStorageAmount;
    }

    
    
}
