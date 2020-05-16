package application;

import java.util.ArrayList;

public class Order {
    private ArrayList<OrderItem> items;

    public ArrayList<OrderItem> getItems() {
	return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
	this.items = items;
    }
    
    public Order(ArrayList<OrderItem> items) {
	this.items = items;
    }
}
