package lab6.Polska;


import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class PolandMain {

	public static void main(String[] args) {
		String expression = "2 / 5 - 2 / 3 : 3 / 4 * 1 / 2";
		String[] expressions = expression.split(" ");
		check(expressions);
		Stack<String> outExpressions = new Stack<String>();
		Stack<String> symbols = new Stack<String>();
		Stack<String> expressionInOtherOrder = new Stack<String>();
		Stack<Integer> result = new Stack<Integer>();
		toPolishNotation(expressions, outExpressions, symbols);
		fullPolish(outExpressions, symbols);
		System.out.println("Обычная запись: " + expression);
		System.out.print("Обратная польская запись: ");
		for (String word : outExpressions) {
			System.out.print(word + " ");
		}
		System.out.println();
		complete(expressionInOtherOrder, outExpressions, result);

	}

	public static void check(String[] expression) {
		for (int i = 0; i < expression.length; i++) {
			if (expression[i].equals("/")) {
				if ((expression[i + 1].equals("*")) || (expression[i + 1].equals(":"))
						|| (expression[i + 1].equals("-")) || (expression[i + 1].equals("+"))) {
					System.out.println("Error#1");
					break;
				}

			}

		}
		for (int i = 0; i < expression.length; i++) {
			if (expression[i].equals("/")) {
				if (expression[i + 1].equals("0")) {
					System.out.println("Error#2");
					break;
				}

			}

		}
		for (int i = 0; i < expression.length; i++) {
			if ((expression[i].equals("*")) || (expression[i].equals(":")) || (expression[i].equals("-"))
					|| (expression[i].equals("+"))) {
				if (expression[i + 1].equals("/")) {
					System.out.println("Error#3");
					break;
				}

			}

		}
		for (int i = 0; i < expression.length; i++) {
			if (expression[i].equals("(")) {
				if (expression[i + 1].equals(")")) {
					System.out.println("Error#4");
					break;
				}

			}

		}
		for (int i = 0; i < expression.length; i++) {
			if ((expression[i].equals("*")) | (expression[i].equals(":")) | (expression[i].equals("+"))
					| (expression[i].equals("-"))) {
				if ((expression[i + 1].equals("*")) | (expression[i + 1].equals(":")) | (expression[i + 1].equals("+"))
						| (expression[i + 1].equals("-"))) {
					System.out.println("Error#5");
					break;
				}

			}
		}
		for (int i = 0; i < expression.length; i++) {
			if ((expression[0].equals("*")) || (expression[0].equals(":")) || (expression[0].equals("-"))
					|| (expression[0].equals("+")) || (expression[0].equals(")"))) {
				System.out.println("Error#7");
				break;

			}

		}

	}

	public static void toPolishNotation(String[] a, Stack<String> outExpress, Stack<String> symbol) {
		for (int i = 0; i < a.length; i++) {
			if (a[i].equals("/")) {
				outExpress.push(a[i - 1]);
				outExpress.push(a[i]);
				outExpress.push(a[i + 1]);
			}
			if ((a[i].equals("*")) | (a[i].equals(":"))) {
				if (symbol.isEmpty()) {
					symbol.push(a[i]);
				} else {
					if ((symbol.peek().equals("+")) | (symbol.peek().equals("-")) | (symbol.peek().equals("("))) {
						symbol.push(a[i]);
					} else {
						String sign = symbol.pop();
						outExpress.push(sign);
						symbol.push(a[i]);
					}
				}
			}
			if (a[i].equals("(")) {
				symbol.push(a[i]);
			}
			if (a[i].equals(")")) {
				while (!symbol.peek().equals("(")) {
					String newSign = symbol.pop();
					outExpress.push(newSign);
				}
				symbol.pop();
			}
			if ((a[i].equals("+")) | (a[i].equals("-"))) {
				if (symbol.isEmpty()) {
					symbol.push(a[i]);
				} else {
					if ((symbol.peek().equals("+")) | (symbol.peek().equals("-")) | (symbol.peek().equals("("))) {
						symbol.push(a[i]);
					}
					else {
						String secondSign = symbol.pop();
						outExpress.push(secondSign);
						symbol.push(a[i]);
					}
				}
			}
		}

	}

	public static void fullPolish(Stack<String> outExpress, Stack<String> symbol) {
		while (!symbol.isEmpty()) {
			String thirdSign = symbol.pop();
			outExpress.push(thirdSign);
		}
	}

	public static void complete(Stack<String> second, Stack<String> outExpress, Stack<Integer> result) {
		while (!outExpress.isEmpty()) {
			String a = outExpress.pop();
			second.push(a);
		}
		while (!second.isEmpty()) {
			String b = second.pop();
			if ((!b.equals("/")) & (!b.equals("+")) & (!b.equals("-")) & (!b.equals("*")) & (!b.equals(":"))) {
				int number = Integer.parseInt(b);
				result.push(number);
			}
			if ((b.equals("+"))) {
				int one = result.pop();
				int two = result.pop();
				int three = result.pop();
				int four = result.pop();
				four = four * one + two * three;
				result.push(four);
				three = three * one;
				result.push(three);
			}
			if ((b.equals("-"))) {
				int one = result.pop();
				int two = result.pop();
				int three = result.pop();
				int four = result.pop();
				four = four * one - two * three;
				result.push(four);
				three = three * one;
				result.push(three);
			}
			if ((b.equals("*"))) {
				int one = result.pop();
				int two = result.pop();
				int three = result.pop();
				int four = result.pop();
				four = four * two;
				result.push(four);
				three = three * one;
				result.push(three);
			}
			if ((b.equals(":"))) {
				int one = result.pop();
				int two = result.pop();
				int three = result.pop();
				int four = result.pop();
				four = four * one;
				result.push(four);
				three = three * two;
				result.push(three);
			}
		}
		int denom = result.pop();
		int num = result.pop();
		System.out.println("Результат: " + num + "/" + denom);
		result.push(num);
		result.push(denom);

	}
	@Test
	public void polishNotationTest() {
		String expression = "1 / 2 - 1 / 3 + 1 / 4";
		String[] expressions = expression.split(" ");
		check(expressions);
		Stack<String> outExpressions = new Stack<String>();
		Stack<String> symbols = new Stack<String>();
		toPolishNotation(expressions, outExpressions, symbols);
		fullPolish(outExpressions, symbols);
		Stack<String> expect = new Stack<String>();
		expect.push("1");
		expect.push("/");
		expect.push("2");
		expect.push("1");
		expect.push("/");
		expect.push("3");
		expect.push("1");
		expect.push("/");
		expect.push("4");
		expect.push("+");
		expect.push("-");
		Assert.assertEquals(expect, outExpressions);
		
	}
	@Test
	public void result() {
		String expression = "1 / 2 - 1 / 3 + 1 / 4";
		String[] expressions = expression.split(" ");
		check(expressions);
		Stack<String> outExpressions = new Stack<String>();
		Stack<String> symbols = new Stack<String>();
		Stack<String> expressionInOtherOrder = new Stack<String>();
		Stack<Integer> result = new Stack<Integer>();
		toPolishNotation(expressions, outExpressions, symbols);
		fullPolish(outExpressions, symbols);
		complete(expressionInOtherOrder, outExpressions, result);
		Stack<Integer> expect = new Stack<Integer>();
		expect.push(-2);
		expect.push(24);
		Assert.assertEquals(expect, result);
		
	}

}
