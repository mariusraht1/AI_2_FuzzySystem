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

	// TODO: With stockAmount we mean the new stock amount after subtracting the
	// demand from the stock
	public FuzzyAmount calcOrderAmount(FuzzyAmount demandAmount, FuzzyAmount stockAmount) {
		FuzzyAmount orderAmount = FuzzyAmount.NOTHING;

		switch (demandAmount) {
		case NOTHING:
			switch (stockAmount) {
			case NOTHING:
				orderAmount = FuzzyAmount.VERY_LOW;
				break;
			default:
				break;
			}
			break;
		case VERY_LOW:
			switch (stockAmount) {
			case NOTHING:
				orderAmount = FuzzyAmount.LOW;
				break;
			case VERY_LOW:
				orderAmount = FuzzyAmount.VERY_LOW;
				break;
			default:
				break;
			}
			break;
		case LOW:
			switch (stockAmount) {
			case NOTHING:
				orderAmount = FuzzyAmount.MEDIUM;
				break;
			case VERY_LOW:
				orderAmount = FuzzyAmount.LOW;
				break;
			case LOW:
				orderAmount = FuzzyAmount.VERY_LOW;
				break;
			default:
				break;
			}
			break;
		case MEDIUM:
			switch (stockAmount) {
			case NOTHING:
				orderAmount = FuzzyAmount.HIGH;
				break;
			case VERY_LOW:
				orderAmount = FuzzyAmount.MEDIUM;
				break;
			case LOW:
				orderAmount = FuzzyAmount.LOW;
				break;
			case MEDIUM:
				orderAmount = FuzzyAmount.VERY_LOW;
				break;
			default:
				break;
			}
			break;
		case HIGH:
			switch (stockAmount) {
			case NOTHING:
				orderAmount = FuzzyAmount.VERY_HIGH;
				break;
			case VERY_LOW:
				orderAmount = FuzzyAmount.HIGH;
				break;
			case LOW:
				orderAmount = FuzzyAmount.MEDIUM;
				break;
			case MEDIUM:
				orderAmount = FuzzyAmount.LOW;
				break;
			case HIGH:
				orderAmount = FuzzyAmount.VERY_LOW;
				break;
			default:
				break;
			}
			break;
		case VERY_HIGH:
			switch (stockAmount) {
			case NOTHING:
				orderAmount = FuzzyAmount.VERY_HIGH;
				break;
			case VERY_LOW:
				orderAmount = FuzzyAmount.VERY_HIGH;
				break;
			case LOW:
				orderAmount = FuzzyAmount.VERY_HIGH;
				break;
			case MEDIUM:
				orderAmount = FuzzyAmount.HIGH;
				break;
			case HIGH:
				orderAmount = FuzzyAmount.LOW;
				break;
			case VERY_HIGH:
				orderAmount = FuzzyAmount.VERY_LOW;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}

		return orderAmount;
	}
}
