package application.fuzzy;

public enum FuzzyAmount {
	NOTHING(0, 0), VERY_LOW(1, 20), LOW(21, 40), MEDIUM(41, 60), HIGH(61, 80), VERY_HIGH(81, 100);

	private int min;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	private int max;

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	private FuzzyAmount(int min, int max) {
		this.min = min;
		this.max = max;
	}
}
