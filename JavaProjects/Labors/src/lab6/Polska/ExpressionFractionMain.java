package lab6.Polska;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionFractionMain {

	public static void main(String[] args) {
		String expression = "1 / 2 + 1 / 3 - 1 / 4";
		String[] expressions = expression.split(" ");
		Stack<Integer> numbers = new Stack<Integer>();
		Stack<String> symbols = new Stack<String>();
		toStack(expressions, numbers, symbols);
		complete(numbers, symbols);

	}
	public static void toStack(String[] a, Stack<Integer> numbers, Stack<String> symbols) {
		for(int i = 0; i < a.length; i++) {
			if((!a[i].equals("/")) & (!a[i].equals("+")) & (!a[i].equals("-"))) {
				int number = Integer.parseInt(a[i]);
				numbers.push(number);
			}
			if(a[i].equals("+")) {
				symbols.push(a[i]);
			}
			if(a[i].equals("-")) {
				symbols.push(a[i]);
			}
		}
	}
	public static void complete(Stack<Integer> numbers, Stack<String> symbols) {
		for(String word: symbols) {
			if(word.equals("+")) {
				int one = numbers.pop();
				int two = numbers.pop();
				int three = numbers.pop();
				int four = numbers.pop();
				four = four * one + two * three;
				numbers.push(four);
				three = three * one;
				numbers.push(three);
			}
			if(word.equals("-")) {
				int one = numbers.pop();
				int two = numbers.pop();
				int three = numbers.pop();
				int four = numbers.pop();
				four = four * one - two * three;
				numbers.push(four);
				three = three * one;
				numbers.push(three);
			}
		}
		int denom = numbers.pop();
		int num = numbers.pop();
		System.out.println("Результат: " + num + "/" + denom);
		numbers.push(num);
		numbers.push(denom);
	}
	@Test
	public void toStack() {
		String expression = "1 / 2 + 1 / 3";
		String[] expressions = expression.split(" ");
		Stack<Integer> numbers = new Stack<Integer>();
		Stack<String> symbols = new Stack<String>();
		toStack(expressions, numbers, symbols);
		Stack<Integer> expectedNumbers = new Stack<Integer>();
		Stack<String> expectedSymbols = new Stack<String>();
		expectedNumbers.push(1);
		expectedNumbers.push(2);
		expectedNumbers.push(1);
		expectedNumbers.push(3);
		expectedSymbols.push("+");
		Assert.assertEquals(expectedNumbers,numbers);
		Assert.assertEquals(expectedSymbols,symbols);
		
	}
	@Test
	public void complete() {
		String expression = "1 / 2 + 1 / 3";
		String[] expressions = expression.split(" ");
		Stack<Integer> numbers = new Stack<Integer>();
		Stack<String> symbols = new Stack<String>();
		toStack(expressions, numbers, symbols);
		complete(numbers, symbols);
		Stack<Integer> expectedNumbers = new Stack<Integer>();
		expectedNumbers.push(5);
		expectedNumbers.push(6);
		Assert.assertEquals(expectedNumbers,numbers);
		}

}
