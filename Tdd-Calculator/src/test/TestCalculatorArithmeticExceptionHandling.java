package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.Calculator;

public class TestCalculatorArithmeticExceptionHandling {

	@Test
	@DisplayName("divison by zero, expect ArithmeticException")
	void calculateExpression_divideByZero_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
	
		// Act
	    Throwable exception = assertThrows(ArithmeticException.class, () -> {
	    	calc.calculateExpression("16/0");
	    });
	    
	    // Assert
	    assertEquals("Division by zero", exception.getMessage());
	}

	@Test
	@DisplayName("test overflow, expect ArithmeticException")
	void calculateExpression_maxPlusmax_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
		String max = (String) Double.toString(Double.MAX_VALUE);
		
		// Act
	    Throwable exception = assertThrows(ArithmeticException.class, () -> {
	    calc.calculateExpression(max + "+" + max);
	    });
	    
	    // Assert
	    assertEquals("OverFlow", exception.getMessage());
	}

	@Test
	@DisplayName("test underflow, expect ArithmeticException")
	void calculateExpression_minMinusMin_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
		String max = (String) Double.toString(Double.MAX_VALUE);
		
		// Act
	    Throwable exception = assertThrows(ArithmeticException.class, () -> {
	    calc.calculateExpression("0-" + max + "-" + max);
	    });
	    
	    // Assert
	    assertEquals("UnderFlow", exception.getMessage());
	}

}
