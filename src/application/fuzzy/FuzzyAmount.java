package application.fuzzy;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public enum FuzzyAmount {
	NOTHING(0, 0.00, 0.00, 0.00), VERY_LOW(1, 0.01, 0.10, 0.20), LOW(2, 0.21, 0.30, 0.40), MEDIUM(3, 0.41, 0.50, 0.60),
	HIGH(4, 0.61, 0.70, 0.80), VERY_HIGH(5, 0.81, 0.90, 1.00);

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

	private double median;

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	private double max;

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	private FuzzyAmount(int value, double min, double median, double max) {
		this.setValue(value);
		this.setMin(min);
		this.setMedian(median);
		this.setMax(max);
	}

	public static FuzzyAmount getByRate(double rate) {
		FuzzyAmount result = FuzzyAmount.NOTHING;

		DecimalFormat decimalFormat = new DecimalFormat("#.##");
	    DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	    decimalFormatSymbols.setDecimalSeparator('.');
	    decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);    
		rate = Double.valueOf(decimalFormat.format(rate));
		
		for (FuzzyAmount fuzzyAmount : FuzzyAmount.values()) {
			if (fuzzyAmount.getMin() <= rate && fuzzyAmount.getMax() >= rate) {
				result = fuzzyAmount;
				break;
			}
		}

		return result;
	}
	
	public static FuzzyAmount getByID(int id) {
		FuzzyAmount result = FuzzyAmount.NOTHING;
		
		for (FuzzyAmount fuzzyAmount : FuzzyAmount.values()) {
			if(id == fuzzyAmount.getValue()) {
				result = fuzzyAmount;
				break;
			}
		}
		
		return result;
	}
}
