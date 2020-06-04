package application.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import application.fuzzy.FuzzyAmount;

public class Tests {
	@Test
	public void fuzzyAmount_getByRate() {
		assertEquals(FuzzyAmount.MEDIUM, FuzzyAmount.getByRate(0.60));
		assertEquals(FuzzyAmount.HIGH, FuzzyAmount.getByRate(0.61));
	}
}
