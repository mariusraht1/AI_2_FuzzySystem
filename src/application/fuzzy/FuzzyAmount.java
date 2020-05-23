package application.fuzzy;

public enum FuzzyAmount {
	NOTHING(0.00, 0.00), VERY_LOW(0.01, 0.20), LOW(0.21, 0.40), MEDIUM(0.41, 0.60), HIGH(0.61, 0.80), VERY_HIGH(0.81, 0.100);

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

	private FuzzyAmount(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public static FuzzyAmount detAmount(double rate) {
		FuzzyAmount result = FuzzyAmount.NOTHING;
		
		for(FuzzyAmount fuzzyAmount : FuzzyAmount.values()) {
			if(fuzzyAmount.getMin() <= rate && fuzzyAmount.getMax() >= rate) {
				result = fuzzyAmount;
				break;
			}
		}
		
		return result;
	}
}
