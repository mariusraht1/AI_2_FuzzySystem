package application.fuzzy;

public class FuzzySystem {
	// TODO: Classification of stock, demand and order
	private static FuzzySystem instance;

	public static FuzzySystem getInstance() {
		if (instance == null) {
			instance = new FuzzySystem();
		}

		return instance;
	}

	private FuzzySystem() {
	};

}
