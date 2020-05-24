package application.fuzzy;

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


}
