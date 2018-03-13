package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.Calculator;

public class TestCalculatorIllegalArgumentException {

	@Test
	@DisplayName("empty string given, expect IllegalArgumentException")
	void calculateExpression_emptyStringGiven_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
	
		// Act
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	calc.calculateExpression("");
	    });
	    
	    // Assert
	    assertEquals("Empty Expression", exception.getMessage());
		
	}

	@Test
	@DisplayName("given non digit last, expect IllegalArgumentException")
	void calculateExpression_endsWithNonDigit_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
	
		// Act
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	calc.calculateExpression("2+38/");
	    });
	    
	    // Assert
	    assertEquals("Ends with non digit", exception.getMessage());
	}

	@Test
	@DisplayName("illegal characters given, expect IllegalArgumentException")
	void calculateExpression_illegalCharGiven_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
	
		// Act
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	calc.calculateExpression("22*654+51-123/3%5n");
	    });
	    
	    // Assert
	    assertEquals("Expression contains illegal characters", exception.getMessage());
	}

	@Test
	@DisplayName("given duplicate operators, expect IllegalArgumentException")
	void calculateExpression_operatorDuplicates_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
	
		// Act
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	calc.calculateExpression("2+-38");
	    });
	    
	    // Assert
	    assertEquals("Operator duplicates exists", exception.getMessage());
	}

	@Test
	@DisplayName("given non digit first, expect IllegalArgumentException")
	void calculateExpression_startsWithNonDigit_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
	
		// Act
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	calc.calculateExpression("-2+3");
	    });
	    
	    // Assert
	    assertEquals("Starts with non digit", exception.getMessage());
	}

	@Test
	@DisplayName("whitespace given, expect IllegalArgumentException")
	void calculateExpression_whitespaceStringGiven_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
	
		// Act
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	calc.calculateExpression("1 +1");
	    });
	    
	    // Assert
	    assertEquals("Expression contains whitepsace", exception.getMessage());
	}

	@Test
	@DisplayName("5 - -5, expect IllegalArgumentException")
	void calculateExpression_expression5_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
	
		// Act
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	calc.calculateExpression("5--5");
	    });
	    
	    // Assert
	    assertEquals("Operator duplicates exists", exception.getMessage());
	}

	@Test
	@DisplayName("5+(6*7), expect IllegalArgumentException")
	void calculateExpression_expression6_expectException() {
	
		// Arrange
		Calculator calc = new Calculator();
	
		// Act
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	calc.calculateExpression("5+(6*7)");
	    });
	    
	    // Assert
	    assertEquals("Expression contains illegal characters", exception.getMessage());
	}

}
