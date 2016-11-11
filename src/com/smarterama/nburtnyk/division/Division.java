package com.smarterama.nburtnyk.division;

import java.util.List;
import java.util.ArrayList;

public class Division {
	
	private String repeat(String str, int times) {
		return new String(new char[times]).replace("\0", str);
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
		n = String.valueOf(n).length();
		return n;
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
		String type = repeat(" ", j) + "-" + partOrX1 + newline()
				+ repeat(" ", numOf2ndRepeat) + divideRule(partOrX1, x2)[1]
				+ newline() + repeat(" ", numOf2ndRepeat)
				+ repeat("-", findLenth(divideRule(partOrX1, x2)[1]) + 1);
		return type;
	}

	private String newline() {
		return System.getProperty("line.separator");
	}

	private static int outputResult(int numerator, int denominator) {
		int den = denominator;
		if (numerator % denominator == 0) {
			System.out.print(numerator / denominator);
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
		denominator = den;
		
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
			System.out.print(")");
		}
		return periodLenth;
	}
	

	public void start(String digits) {     //to do (rename method)
		//System.out.print(digits);
		//System.out.print(newline());
		
		String[] rowNumbers = digits.split("/"); // to do
		
		int x1 = Integer.parseInt(rowNumbers[0]);
		int x2 = Integer.parseInt(rowNumbers[1]);
		int j, p;
		p = j = 0;
		
		System.out.print(" " + "|");
		int outputResult = outputResult(x1, x2);
		System.out.print(newline());
		
		if (x1 >= x2) {
			String dividend = "";
			for (int i = splitToDigits(x1).size() - 1; i >= 0; i--) {
				dividend += splitToDigits(x1).get(i);
				int part = Integer.parseInt(dividend);
				if (part >= x2) {
					dividend = Integer.toString(part - divideRule(part, x2)[1]);
					System.out.println(toDisplay(j, part, x2));
					j = findSpaces(part, x2, j);
					if (part != divideRule(part, x2)[1] && i == 0) {
						x1 = part - divideRule(part, x2)[1];
					}
				}
			}
		}
		if (x1 < x2) {
			while (x1 != divideRule(x1, x2)[1] && p < outputResult + 5) {
				if (divideRule(x1, x2)[0] > 0) {
					System.out.println(toDisplay(j, x1, x2));
					j = findSpaces(x1, x2, j);
					x1 -= divideRule(x1, x2)[1];
				} else if (divideRule(x1, x2)[0] == 0) {
					while (x1 < x2) {
						x1 *= 10;
					}
				}
				p++;
			}
		}
	}
}


