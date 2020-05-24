package application.fuzzy;

public enum FuzzyAmount {
	NOTHING(0, 0.00, 0.00), VERY_LOW(1, 0.01, 0.20), LOW(2, 0.21, 0.40), MEDIUM(3, 0.41, 0.60), HIGH(4, 0.61, 0.80),
	VERY_HIGH(5, 0.81, 1.00);

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private double min;

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	private double max;

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	private FuzzyAmount(int value, double min, double max) {
		this.value = value;
		this.min = min;
		this.max = max;
	}

	public static FuzzyAmount detAmount(double rate) {
		FuzzyAmount result = FuzzyAmount.NOTHING;

		for (FuzzyAmount fuzzyAmount : FuzzyAmount.values()) {
			if (fuzzyAmount.getMin() <= rate && fuzzyAmount.getMax() >= rate) {
				result = fuzzyAmount;
				break;
			}
		}

		return result;
	}

	public FuzzyAmount getNextHigherAmount() {
		switch (this) {
		case NOTHING:
			return VERY_LOW;
		case VERY_LOW:
			return LOW;
		case LOW:
			return MEDIUM;
		case MEDIUM:
			return HIGH;
		case HIGH:
			return VERY_HIGH;
		default:
			return this;
		}
	}
}
