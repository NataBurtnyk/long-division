package com.smarterama.nburtnyk.division;

import java.util.ArrayList;
import java.util.List;

public class DivisionStep {
	
	private int dividend;
	private int divisor;
	private int resultDigit;
	private int deduction;
	private int minuend;
	private int difference;
	private int newDividend;
	
	public DivisionStep(int dividend, int divisor) {
		this.dividend = Math.abs(dividend);
		this.divisor = Math.abs(divisor);
		countStepValues();
	}
	
	private void countStepValues() {
		deduction = getDeduction(dividend, divisor);
		String dividendTail = Integer.toString(dividend).substring(getNumberLength(deduction));
		resultDigit = deduction / divisor;
		minuend = divisor * resultDigit;
		difference = deduction - minuend;
		newDividend = getNewDividend(difference, dividendTail);
	}
	
	private int getDeduction(int dividend, int divisor) {
		int numberOfDeductionDigits = getNumberLength(divisor);
		int deduction = getFirstNDigits(dividend, numberOfDeductionDigits++);
		while (deduction < divisor)
			deduction = getFirstNDigits(dividend, numberOfDeductionDigits++);
		return deduction;
	}
	
	private int getNewDividend(int difference, String dividendTail) {
		if (dividendTail.length() == 0)
			return difference;
		if (difference == 0)
			return Integer.parseInt(dividendTail);
		return Integer.parseInt(Integer.toString(difference) + dividendTail);
	}
	
	private int getNumberLength(int number) {
		return Integer.toString(number).length();
	}
	
	private int getFirstNDigits(int number, int n) {
		String sNumber = Integer.toString(number);
		String upToNDigits = sNumber.substring(0, Math.min(sNumber.length(), n));
		return Integer.parseInt(upToNDigits);
	}
	
	public List<String> getOutput() {
		List<String> output = new ArrayList<String>();
		int length = getNumberLength(deduction);
		output.add(" " + Integer.toString(deduction));
		output.add("-");
		output.add(" " + String.format("%1$" + length + "s", minuend));
		output.add(" " + new String(new char[length]).replace("\0", "-"));
		return output;
	}
	
	public String reciaveResultDigitAsString() {
		return Integer.toString(resultDigit);
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

}
