package calculator;

import java.util.ArrayList;
import java.util.Stack;
/**
 * Created by Tommy Eriksson 2018 for Lernia: course TestDrivenDevelopment(TDD) 
 * */
public class Calculator {
	
	/**
	 * <p>calculateExpression takes a mathematical expression as a string and returns the result as a string. 
	 * This function is able to calculate unlimited number of operands and operators together without the need for brackets. </p>
	 * 
	 * <b>The method handles:</b><br>
	 * 5*4+7-3*0+4-10 <br>
	 * 7*4+6-3/2<br>
	 * 
	 * <b>The method don´t handle:</b><br>
	 * 5 - -5 <br>
	 * 5+(6*7)<br><br>
	 *
	 * <b>Operator precedence is handled:</b><br>
	 * 7*6/2 = 21<br>
	 * 5+6/2*2 = 11 <br><br>
	 * 
	 * @param expression the mathematical expression to be evaluated ex. 5*4+7-3*0+4-10 <br>
	 * @return The evaluated expression as a string
	 * */
	public String calculateExpression(String expression) throws IllegalArgumentException, ArithmeticException{

		// test the expression for illegal arguments
		testExpression(expression);
		
		// convert expression to arraylist
		ArrayList<String> expressionList = expressionToList(expression);
		
		// convert infix arraylist to postfixarraylist
		ArrayList<String> postfixExpression = inFixToPostFix(expressionList);
		
		// evaluate postfix expression 
		String result = evaluateExpression(postfixExpression);
		
		return result;
	}

	
	/**
	 * Test the expression for illegal arguments
	 * 
	 * */
	private void testExpression(String expression) {

		// check for empty string
		if (!hasLength(expression)) throw new IllegalArgumentException("Empty Expression");
		
		// check for whitespace
		if (containsWhiteSpace(expression)) throw new IllegalArgumentException("Expression contains whitepsace");

		// check for illegal char
		if (containsIllegalChar(expression)) throw new IllegalArgumentException("Expression contains illegal characters");
		
		// check that expression starts with digit
		if (!startsWithDigit(expression)) throw new IllegalArgumentException("Starts with non digit");
		
		// check that expression ends with digit
		if (!endsWithDigit(expression)) throw new IllegalArgumentException("Ends with non digit");
		
		// check for operator duplicates
		if (operatorDuplicates(expression)) throw new IllegalArgumentException("Operator duplicates exists");
		
	}
	
	/***
	 * Tests the double for under/overflow
	 */
	private void testDouble(double num) {
		if (num == Double.POSITIVE_INFINITY) throw new ArithmeticException("OverFlow");
		if (num == Double.NEGATIVE_INFINITY) throw new ArithmeticException("UnderFlow");
	}
	
	/**
	 * Takes a expression as a string and converts it to a infix array expression
	 * 
	 * */
	private ArrayList<String> expressionToList(String expression) {
		
		ArrayList<String> infixArr = new ArrayList<String>();
 		
		StringBuffer sb = new StringBuffer();
				
		for (int i = 0; i < expression.length(); i++) {
			if ((Character.isDigit(expression.charAt(i))) || Character.valueOf(expression.charAt(i)) == 46 || Character.valueOf(expression.charAt(i)) == 69){
				sb.append(expression.charAt(i));
			}
			else {
				infixArr.add(sb.toString());
				sb.delete(0, 50);
				infixArr.add(String.valueOf(expression.charAt(i)).toString());
			}	
		}
		infixArr.add(sb.toString());

		return infixArr;
	}
	
	/**
	 	This method converts an array of infixexpression to an array of postfixexpression by a set of rules<br /><br />
		1. Print operands as they arrive.<br />
		2. If the stack is empty, push the incoming operator onto the stack.<br />
		3. If the incoming symbol has higher precedence than the top of the stack, push it on the stack.<br />
		4. If the incoming symbol has equal precedence with the top of the stack, use association.<br /> If the association is left to right, pop and print the top of the stack and then push the incoming operator. <br />If the association is right to left, push the incoming operator.<br />
		5. If the incoming symbol has lower precedence than the symbol on the top of the stack, pop the stack and print the top operator. Then test the incoming operator against the new top of stack.<br />
		6. At the end of the expression, pop and print all operators on the stack.<br />
	 */
	private ArrayList<String> inFixToPostFix(ArrayList<String> expression){
		
		ArrayList<String> postFixArr = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		
		for(int i = 0; i < expression.size(); i++) {
			
			// Rule 1. Print operands as they arrive.
			if (!expression.get(i).matches("[-*/+%]")){									
				postFixArr.add(expression.get(i));
				
			// Rule 2. If the stack is empty, push the incoming operator onto the stack.	
			}else {
				if (stack.empty()) {
					stack.push(expression.get(i));
					
				// Rule 3. If the incoming symbol has higher precedence than the top of the stack, push it on the stack.	
				}else {
					if(hasHigherPrecedence(expression.get(i),stack.peek())) {
						stack.push(expression.get(i));
						
					// Rule 4. 	If the incoming symbol has equal precedence with the top of the stack, use association. 
					//			If the association is left to right, pop and print the top of the stack and then push the 
					//			incoming operator. If the association is right to left, push the incoming operator.	
					}else if (hasEqualPrecedence(expression.get(i),stack.peek())) {
						postFixArr.add(stack.pop());
						stack.push(expression.get(i));
						
					// Rule 5. 	If the incoming symbol has lower precedence than the symbol on the top of the stack, 
					//			pop the stack and print the top operator. Then test the incoming operator against the 
					//			new top of stack. 	
					}else {
						while (!stack.empty()) {
							if(!hasHigherPrecedence(expression.get(i),stack.peek())) {
								postFixArr.add(stack.pop());
							}else {
								break;
							}
						}
						stack.push(expression.get(i));
					}
				}
			}		
		}
		
		// Rule 6. At the end of the expression, pop and print all operators on the stack.
		while(!stack.empty()) {
			postFixArr.add(stack.pop());
		}

		return postFixArr;
	}
	
	private boolean hasHigherPrecedence(String op1, String op2) {

		int op1Weight = getOperatorWeight(op1);
		int op2Weight = getOperatorWeight(op2);

		return op1Weight > op2Weight ? true : false;
	}
	private boolean hasEqualPrecedence(String op1, String op2) {

		int op1Weight = getOperatorWeight(op1);
		int op2Weight = getOperatorWeight(op2);

		return op1Weight == op2Weight ? true : false;
	}
	
	/**
	 * Takes on operaor and sets the weight for comparence of precedence
	 * 
	 * @param op operator + - * / %
	 * */
	private int getOperatorWeight(String op) {

		int weight = -1;

		switch (op) {
			case "+":
			case "-":
				weight = 1;
				break;
			case "*":
			case "%":
			case "/":
				weight = 2;
				break;
		}

		return weight;
	}
	
	/**
	 * Takes a postfix array, evaluates and returns the result
	 * 
	 * @param expression Postfix expression array
	 * */
	private String evaluateExpression(ArrayList<String> expression)  throws ArithmeticException {
		
		// If only a number given
		if (expression.size() == 1)
			return expression.get(0);
		
		Stack<Double> stack = new Stack<Double>();
		double operand1 = 0;
		double operand2 = 0;
		
		for(int i = 0; i < expression.size(); i++) {
			if (expression.get(i).matches("[-*/+%]")){	
				// Calculate: <operand><operand><operator>
				operand2 = stack.pop();
				operand1 = stack.pop();
				stack.push(calculate(expression.get(i),operand1,operand2));
			}else {
				// Add to the stack, everything that goes into the stack gets parsed here
				stack.push(convertToDouble(expression.get(i)));
			}
		}
		return stack.pop().toString();
	}
	
	/**
	 * Converts a string num to a double. Checks for under/over flow
	 * 
	 *  @return the parsed number
	 *  @param number as string to parse
	  * */
	private double convertToDouble(String num)throws ArithmeticException {
		
		double parsedNum = Double.parseDouble(num);
		testDouble(parsedNum);
		
		return parsedNum;
	}
	
	private double calculate(String operator, double num1, double num2) throws ArithmeticException{

		double result = 0;
		switch (operator) {
		case "+":
			result = add(num1,num2);
			break;
		case "-":
			result = subtract(num1,num2);
			break;
		case "*":
			result = multiply(num1,num2);
			break;
		case "%":
			result = modulus(num1,num2);
			break;
		case "/":
			result = divide(num1,num2);
			break;
		}

		// Test the result for under/overflow
		testDouble(result);
		
		return result;
	}
	
	private double add(double num1, double num2) {
		
		return num1 + num2;
	}

	private double subtract(double num1, double num2) {
		
		return num1 - num2;
	}

	private double multiply(double num1, double num2) {
		
		return num1 * num2;
	}
	
	private double modulus(double num1, double num2) {
		
		return num1 % num2;
	}
	private double divide(double num1, double num2) {
		
		if (num2 == 0) throw new ArithmeticException("Division by zero");
		return num1 / num2;
	}

	/**
	 * checks if the given string is null or empty
	 * 
	 * @param str The string to check
	 * @return true if the string is not emtpy, else false is returned
	 * */
	private boolean hasLength(String str) {
		if(str != null && str.length() > 0)
			return true;
		
		return false;
	}
	
	/**
	 * Takes a string as input an checks if it contains any whitespace.
	 * 
	 * @param str The string to check
	 * @return returns true if the string contains any whitespace, else it returns false
	 * 
	 * */
	private boolean containsWhiteSpace(String str) {
		
		int strLen = str.length();
		
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}	
		return false;
	}
	
	/**
	 * Takes a string and matches an regexp for accepted chars
	 * 
	 * @param str the string to check
	 * @return true if it contain any illegal character, else false
	 * */
	private boolean containsIllegalChar(String str) {
		return !str.matches("([\\d-*/+%.E])*");
	}

	/**
	 * Takes a string and matches an regexp to check that the expression starts with a digit
	 * 
	 * @param str the string to check
	 * @return true if it starts with a digit, else false
	 * */
	private boolean startsWithDigit(String str) {
		return str.matches("^\\d.*");
	}
	
	/**
	 * Takes a string and matches an regexp to check that the expression ends with a digit
	 * 
	 * @param str the string to check
	 * @return true if it ends with a digit, else false
	 * */
	private boolean endsWithDigit(String str) {
		return str.matches(".*\\d$");
	}
	
	/**
	 * Takes a string and matches an regexp to check that there no duplicates of operators
	 * 
	 * @param str the string to check
	 * @return true if duplicates exists, else false
	 * */
	private boolean operatorDuplicates(String str) {
		return str.matches(".*[-*/+%][-*/+%].*");
	}
}
