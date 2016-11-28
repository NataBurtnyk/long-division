package com.smarterama.nburtnyk.division;

import java.util.ArrayList;
import java.util.List;

public class DivisionStep {
	
	private int dividend;
	private int divisor;
	private int partResult;
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
		deduction = findDeduction(dividend, divisor);
		String dividendTail = Integer.toString(dividend).substring(findNumberLength(deduction));
		partResult = deduction / divisor;
		minuend = divisor * partResult;
		difference = deduction - minuend;
		newDividend = findNewDividend(difference, dividendTail);
	}
	
	private int findDeduction(int dividend, int divisor) {
		int numberOfDeductionDigits = findNumberLength(divisor);
		int deduction = findFirstNDigits(dividend, numberOfDeductionDigits++);
		while (deduction < divisor) {
			deduction = findFirstNDigits(dividend, numberOfDeductionDigits++);
		}
		return deduction;
	}
	
	private int findNewDividend(int difference, String dividendTail) {
		if (dividendTail.length() == 0) {
			return difference;
		}
		if (difference == 0) {
			return Integer.parseInt(dividendTail);
		}
		return Integer.parseInt(Integer.toString(difference) + dividendTail);
	}
	
	private int findNumberLength(int number) {
		return Integer.toString(number).length();
	}
	
	private int findFirstNDigits(int number, int n) {
		String sNumber = Integer.toString(number);
		String upToNDigits = sNumber.substring(0, Math.min(sNumber.length(), n));
		return Integer.parseInt(upToNDigits);
	}
	
	public List<String> shapeOutput() {
		List<String> output = new ArrayList<String>();
		int length = findNumberLength(deduction);
		output.add(" " + Integer.toString(deduction));
		output.add("-");
		output.add(" " + String.format("%1$" + length + "s", minuend));
		output.add(" " + new String(new char[length]).replace("\0", "-"));
		return output;
	}
	
	public String reciavePartResultAsString() {
		return Integer.toString(partResult);
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
