package com.smarterama.nburtnyk.division;

import java.util.ArrayList;
import java.util.List;


public class LongDivision {
	
	protected int dividend, divisor;
	protected String result;
	
	public LongDivision(int dividend, int divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
		this.result = "";
	}

	private String repeatString(String s, int n) {
		return new String(new char[n]).replace("\0", s);
	}

	private List<Integer> splitToDigits(int x1) {
		List<Integer> digits = new ArrayList<Integer>();
		while (x1 > 0) {
			digits.add(x1 % 10);
			x1 /= 10;
		}
		return digits;
	}

	private int findLenth(int n) {
		return String.valueOf(n).length();
	}

	private int[] divideRule(int a, int b) {
		int div[] = new int[2];
		div[0] = (int) a / b;
		div[1] = div[0] * b;
		return div;
	}

	private int findSpaces(int c, int d, int j) {
		int h = divideRule(c, d)[1];
		int hLenth = findLenth(h);
		int subLenth = findLenth(c - h);
		int cLenth = findLenth(c);
		if (c != h) {
			j += cLenth - subLenth;
		} else {
			j += hLenth;
		}
		return j;
	}

	private String toDisplay(int j, int partOrX1, int x2) {
		int numOf2ndRepeat = j + 1 + findLenth(partOrX1) - findLenth(divideRule(partOrX1, x2)[1]);
		String type = repeatString(" ", j) + "-" + partOrX1 + "\n"
				+ repeatString(" ", numOf2ndRepeat)
				+ divideRule(partOrX1, x2)[1] + "\n"
				+ repeatString(" ", numOf2ndRepeat)
				+ repeatString("-", findLenth(divideRule(partOrX1, x2)[1]) + 1);
		return type;
	}

	private int resultOfDivide(int numerator, int denominator) {
		int den = denominator;
		if (numerator % denominator == 0) {
			System.out.println(numerator / denominator);
			return den;
		}
		int s = denominator % 2;
		int l = 0;
		while (s == 0) {
			denominator = denominator / 2;
			s = denominator % 2;
			l++;
		}
		s = denominator % 5;
		int l1 = 0;
		while (s == 0) {
			denominator = denominator / 5;
			s = denominator % 5;
			l1++;
		}
		int periodLenth = 1;
		int r = 10;
		while (r != 1) {
			r = (10 * r) % denominator;
			if (r == 0) {
				break;
			}
			periodLenth++;
		}
		int beforePeriodLenth;
		if (l1 > l) {
			beforePeriodLenth = l1;
		} else {
			beforePeriodLenth = l;
		}
		System.out.print(numerator / denominator + ".");
		numerator = numerator % denominator;
		for (int i = 0; i < beforePeriodLenth; i++) {
			System.out.print((numerator * 10) / denominator);
			numerator = (numerator * 10) % denominator;
		}
		if (periodLenth > 0) {
			System.out.print("(");
			for (int i = 0; i < periodLenth; i++) {
				System.out.print((numerator * 10) / denominator);
				numerator = (numerator * 10) % denominator;
			}
			System.out.println(")");
		}
		return periodLenth;
	}

	public void divide(int x1, int x2) {
		int spacesFromLeftSide = 0;
		int digitsAfterPoint = 0;
		System.out.print(" " + x1 + "|" + x2 + "\n " + x1 + "|");
		
		int outputResult = resultOfDivide(x1, x2);
		
		if (x1 >= x2) {
			String dividend = "";
			for (int i = splitToDigits(x1).size() - 1; i >= 0; i--) {
				dividend += splitToDigits(x1).get(i);
				int part = Integer.parseInt(dividend);
				if (part >= x2) {
					dividend = Integer.toString(part - divideRule(part, x2)[1]);
					System.out.println(toDisplay(spacesFromLeftSide, part, x2));
					spacesFromLeftSide = findSpaces(part, x2, spacesFromLeftSide);
					if (part != divideRule(part, x2)[1] && i == 0) {
						x1 = part - divideRule(part, x2)[1];
					}
				}
			}
		}
		if (x1 < x2) {
			while (x1 != divideRule(x1, x2)[1] && digitsAfterPoint < outputResult) {
				if (divideRule(x1, x2)[0] > 0) {
					System.out.println(toDisplay(spacesFromLeftSide, x1, x2));
					spacesFromLeftSide = findSpaces(x1, x2, spacesFromLeftSide);
					x1 -= divideRule(x1, x2)[1];
				} else if (divideRule(x1, x2)[0] == 0) {
					while (x1 < x2) {
						x1 *= 10;
					}
				}
				digitsAfterPoint++;
			}
		}
	}
}
