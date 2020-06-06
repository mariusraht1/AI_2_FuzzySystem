package application.fuzzy;

public class FuzzySystem {
	private static FuzzySystem instance;

	public static FuzzySystem getInstance() {
		if (instance == null) {
			instance = new FuzzySystem();
		}

		return instance;
	}

	private int round = 0;

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public void increaseRound() {
		this.round += 1;
	}

	// x: Stock / y: Demand
	private FuzzyAmount[][] fuzzyMatrix = new FuzzyAmount[FuzzyAmount.values().length][FuzzyAmount.values().length];

	public FuzzyAmount[][] getFuzzyMatrix() {
		return fuzzyMatrix;
	}

	public void setFuzzyMatrix(FuzzyAmount[][] fuzzyMatrix) {
		this.fuzzyMatrix = fuzzyMatrix;
	}

	public FuzzyAmount getFuzzyOrderAmount(FuzzyAmount stockAmount, FuzzyAmount demandAmount) {
		return fuzzyMatrix[stockAmount.getValue()][demandAmount.getValue()];
	}

	private FuzzySystem() {
		initFuzzyMatrix();
	}

	public void initFuzzyMatrix() {
		for (int x = 0; x < fuzzyMatrix.length; x++) {
			for (int y = 0; y < fuzzyMatrix[x].length; y++) {
				FuzzyAmount orderAmount = FuzzyAmount.NOTHING;
				FuzzyAmount stockAmount = FuzzyAmount.getByID(x);
				FuzzyAmount demandAmount = FuzzyAmount.getByID(y);

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
						orderAmount = FuzzyAmount.LOW;
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
						orderAmount = FuzzyAmount.MEDIUM;
						break;
					case LOW:
						orderAmount = FuzzyAmount.LOW;
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

				fuzzyMatrix[x][y] = orderAmount;
			}
		}
	}
}
