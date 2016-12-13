package com.smarterama.nburtnyk.division;

import java.util.ArrayList;
import java.util.List;

public class DivisionStep {

	private int dividend;
	private int divisor;
	private int oneDigitFromFraction;
	private int deduction;
	private int minuend;
	private int difference;
	private int newDividend;

	private static final int FOUNDATION_DIGIT_ORDER = 10;

	public DivisionStep(int dividend, int divisor) {
		this.dividend = Math.abs(dividend);
		this.divisor = Math.abs(divisor);
		countStepValues();
	}

	private void countStepValues() {
		deduction = findDeduction(dividend, divisor);
		oneDigitFromFraction = deduction / divisor;
		minuend = divisor * oneDigitFromFraction;
		difference = deduction - minuend;
		newDividend = findNextDividend(dividend, oneDigitFromFraction * divisor);
	}

	private int findDeduction(int dividend, int divisor) {
		int numberOfDeductionDigits = findNumberLength(divisor);
		int deduction = findFirstNDigits(dividend, numberOfDeductionDigits++);
		while (deduction < divisor) {
			deduction = findFirstNDigits(dividend, numberOfDeductionDigits++);
		}
		return deduction;
	}

	private int findNextDividend(int dividend, int deduction) {
		while (findNumberLength(dividend) != findNumberLength(deduction)) {
			deduction *= FOUNDATION_DIGIT_ORDER;
		}
		return dividend - deduction;

	}

	private int findNumberLength(int number) {
		int count = (number == 0) ? 1 : 0;
		while (number != 0) {
			count++;
			number /= FOUNDATION_DIGIT_ORDER;
		}
		return count;
	}

	private int findFirstNDigits(int number, int n) {
		while (findNumberLength(number) != n) {
			number /= FOUNDATION_DIGIT_ORDER;
		}
		return number;
	}

	public List<String> formatOutput() {
		List<String> output = new ArrayList<String>();
		int numberLength = findNumberLength(deduction);
		output.add(" " + deduction);
		output.add("-");
		output.add(" " + String.format("%1$" + numberLength + "s", minuend));
		output.add(" " + new String(new char[numberLength]).replace("\0", "-"));
		return output;
	}

	public int getOneDigitFromFraction() {
		return oneDigitFromFraction;
	}

	public int getDeduction() {
		return deduction;
	}

	public int getDifference() {
		return difference;
	}

	public int getNewDividend() {
		return newDividend;
	}

	public int getMinuend() {
		return minuend;
	}
}
