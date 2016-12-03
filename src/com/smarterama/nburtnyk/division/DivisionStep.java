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
		partResult = deduction / divisor;
		minuend = divisor * partResult;
		difference = deduction - minuend;
		newDividend = findNextDividend(dividend, partResult * divisor);
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
		while(findNumberLength(dividend) != findNumberLength(deduction)){
			deduction *= 10;
		}
		return dividend - deduction;

	}
	
	private int findNumberLength(int number) {
		 int count = (number == 0) ? 1 : 0;
	        while (number != 0) {
	            count++;
	            number /= 10;
	        }
	        return count;
	    }
	
	private int findFirstNDigits(int number, int n) {
		while(findNumberLength(number) != n){
			number /= 10;
		}
		return number;
	}
	
	public List<String> shapeOutput() {
		List<String> output = new ArrayList<String>();
		int numberLength = findNumberLength(deduction);
		output.add(" " + deduction);
		output.add("-");
		output.add(" " + String.format("%1$" + numberLength + "s", minuend));
		output.add(" " + new String(new char[numberLength]).replace("\0", "-"));
		return output;
	}
	
	public int getPartResult() {
		return partResult;
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
