package application;

import java.io.IOException;

import application.product.Product;
import application.product.StoredProduct;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Fuzzy System for Warehouse Ordering System
 * 
 * @author Marius Raht
 * @version 19.08.2020-001
 */
public class Main extends Application {
	private static Stage primaryStage;

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static final int DefaultNumOfAddStock = 0;
	public static final int DefaultNumOfStock_A = 250;
	public static final int DefaultNumOfStock_B = 250;
	public static final int DefaultNumOfStock_C = 250;
	public static final int DefaultNumOfStock_D = 250;

	public enum DefaultStoredProduct {
		PRODUCT_A(new StoredProduct(new Product("Produkt A"), Main.DefaultNumOfStock_A)),
		PRODUCT_B(new StoredProduct(new Product("Produkt B"), Main.DefaultNumOfStock_B)),
		PRODUCT_C(new StoredProduct(new Product("Produkt C"), Main.DefaultNumOfStock_C)),
		PRODUCT_D(new StoredProduct(new Product("Produkt D"), Main.DefaultNumOfStock_D));

		private StoredProduct storedProduct;

		public StoredProduct getStoredProduct() {
			return storedProduct;
		}

		private DefaultStoredProduct(StoredProduct storedProduct) {
			this.storedProduct = storedProduct;
		}
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;

			primaryStage.setTitle("Fuzzy System");
			primaryStage.centerOnScreen();

			Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("/application/view/MainScene.fxml")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
