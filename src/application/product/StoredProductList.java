package application.product;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class StoredProductList extends ArrayList<StoredProduct> {
	public StoredProduct getByName(String name) {
		StoredProduct result = null;

		for (StoredProduct storedProduct : this) {
			if (storedProduct.getProduct().getName().equals(name)) {
				result = storedProduct;
				break;
			}
		}

		return result;
	}

}
