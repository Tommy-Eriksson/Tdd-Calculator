package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.Calculator;

public class TestCalculatorExpressions {
	
	@Test
	@DisplayName("5*4+7-3*0+4-10=21.0")
	void calculateExpression_expression1_expectTwentyOne() {
				
		// Arrange
		Calculator calc = new Calculator();
		
		// Act
		String result = calc.calculateExpression("5*4+7-3*0+4-10");
		
		// Assert
		assertEquals("21.0", result);
	}	
	@Test
	@DisplayName("7*4+6-3/2=32.5")
	void calculateExpression_expression2_expectThirtyTwoPointFive() {
				
		// Arrange
		Calculator calc = new Calculator();
		
		// Act
		String result = calc.calculateExpression("7*4+6-3/2");
		
		// Assert
		assertEquals("32.5", result);
	}	
	@Test
	@DisplayName("10 + 5 - 2 * 6 = 3.0")
	void calculateExpression_expression3_expectThree() {
				
		// Arrange
		Calculator calc = new Calculator();
		
		// Act
		String result = calc.calculateExpression("10+5-2*6");
		
		// Assert
		assertEquals("3.0", result);
	}		
	@Test
	@DisplayName("5.3 - 10 = -4.7")
	void calculateExpression_expression4_expectMinusFourPointSeven() {
				
		// Arrange
		Calculator calc = new Calculator();
		
		// Act
		String result = calc.calculateExpression("5.3-10");

		// Assert
		assertEquals("-4.7", result);
	}	
	
	@Test
	@DisplayName("7*6/2 = 21")
	void calculateExpression_expression5_expectTwentyOne() {
				
		// Arrange
		Calculator calc = new Calculator();
		
		// Act
		String result = calc.calculateExpression("7*6/2");

		// Assert
		assertEquals("21.0", result);
	}	
	
	@Test
	@DisplayName("5+6/2*2 = 11")
	void calculateExpression_expression6_expectEleven() {
				
		// Arrange
		Calculator calc = new Calculator();
		
		// Act
		String result = calc.calculateExpression("5+6/2*2");

		// Assert
		assertEquals("11.0", result);
	}	
	
	@Test
	@DisplayName("246732156468")
	void calculateExpression_nrGiven_expectNr() {
				
		// Arrange
		Calculator calc = new Calculator();
		
		// Act
		String result = calc.calculateExpression("246732156468");
		
		// Assert
		assertEquals("246732156468", result);
	}
	
	@Test
	@DisplayName("9%3 = 0")
	void calculateExpression_nineModThree_expectZero() {
				
		// Arrange
		Calculator calc = new Calculator();
		
		// Act
		String result = calc.calculateExpression("9%3");
		
		// Assert
		assertEquals("0.0", result);
	}
	
	@Test
	@DisplayName("11%3 = 2")
	void calculateExpression_elevenModThree_expectTwo() {
				
		// Arrange
		Calculator calc = new Calculator();
		
		// Act
		String result = calc.calculateExpression("11%3");
		
		// Assert
		assertEquals("2.0", result);
	}
}
