package ui;

import java.util.Scanner;

import calculator.Calculator;

public class Main {

	public static void main(String[] args) {
		
		Calculator calc = new Calculator();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
		try {
			
			System.out.println("Skriv ett matematiskt utryck:");
			System.out.println(calc.calculateExpression(sc.nextLine()));
			
		}catch (IllegalArgumentException e) {
			System.out.println("***"+e.getMessage());
		}catch (ArithmeticException e) {
			System.out.println("***"+e.getMessage());
		}
		}
	}

}
