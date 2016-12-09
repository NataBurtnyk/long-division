package com.smarterama.nburtnyk.division;

import java.util.ArrayList;
import java.util.List;

public class LongDivision {
	
	private int dividend;
	private int divisor;
	private String result;
	
	public static final int headerRows  = 3;
	
	public LongDivision(int dividend, int divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
	}
	
	private void checkDivisor(int divisor){
		if (divisor == 0) {
			throw new IllegalArgumentException("Divisor can not be 0"); 
		}
	}
	
	public void print(List<String> divisionSteps) {
		for (String line : divisionSteps) {
			System.out.println(line);
		}
	}
		
	public List<String> longDivision() {
		checkDivisor(divisor);
		List<DivisionStep> steps = buildDivisionSteps();
		List<String> output = buildOutputHeader(steps);
		int shift = 0;
		
		// Processing all steps and creating list of strings for output.
		// Create result string.
		boolean firstStep = true;
		for (DivisionStep step : steps) {
			List<String> stepOutput = step.formatOutput();
			
			if (!firstStep) {
				String shiftString = repeatString(" ", shift);
				for (String line : stepOutput) {
					output.add(shiftString + line);
				}
			}

			shift += ("" + step.getDeduction()).length();
			if (step.getDifference() > 0) {
				shift -= ("" + step.getDifference()).length();
			}
			firstStep = false;
		}
		
		// Add last difference.
		if (!steps.isEmpty()) {
			DivisionStep lastStep = steps.get(steps.size() - 1);
			String difference = "" + lastStep.getDifference();
			shift++;
			if (lastStep.getDifference() == 0) {
				if (lastStep.getNewDividend() == 0) {
					shift--;
				}
				else {
					difference = "" + lastStep.getNewDividend();
				}
			}
			output.add(repeatString(" ", shift) + difference);
		}
		return output;
	}
	
	
	private String repeatString(String s, Integer n) {
		return new String(new char[n]).replace("\0", s);
	}
	
	
	private List<DivisionStep> buildDivisionSteps() {
		List<DivisionStep> divisionSteps = new ArrayList<DivisionStep>();
		int currentDividend = Math.abs(dividend);
		result = "";

		while (currentDividend >= Math.abs(divisor)) {
			DivisionStep step = new DivisionStep(currentDividend, Math.abs(divisor));
			currentDividend = step.getNewDividend();
			result += step.getOneDigitFromFraction();
			divisionSteps.add(step);
		}
		
		if (!result.isEmpty()) {
			// Add 0 to the end, if there is a reminder.
			DivisionStep lastStep = divisionSteps.get(divisionSteps.size() - 1);
			if (lastStep.getDifference() == 0 && lastStep.getNewDividend() != 0){
				result += "0";
			}
			result = findResultSign() + result;
		}
		else
			result = "0";
		return divisionSteps;
	}
	
	
	private String findResultSign() {
		return (dividend / divisor) < 0 ? "-": "";
	}
	
	
	private List<String> buildOutputHeader(List<DivisionStep> steps) {
		List<String> output = new ArrayList<String>();
		List<String> firstStepOutput;

		String firstRow = (dividend > 0 && steps.size() > 0 ? " " : "") + dividend + " |" + divisor;
		String secondRow;
		String thirdRow;
		
		dividend = Math.abs(dividend);
	
		if(!steps.isEmpty()) {
			secondRow = "-" + ("" + dividend).replaceAll("[0-9]", " ") + " |" + result.replaceAll("[0-9]", "-");
			thirdRow = " " + steps.get(0).getMinuend() + ("" + dividend).replaceAll("[0-9]", " ") + "|" + result;
		} else {
			secondRow = ("" + dividend).replaceAll("[0-9]", " ") + " |" + ("" + divisor).replaceAll("[0-9]", "-");
			thirdRow = (" " + dividend).replaceAll("[0-9]", " ") + "|" + result;
		}
		
		output.add(firstRow);
		output.add(secondRow);
		output.add(thirdRow);
		
		
		if (!steps.isEmpty()) {
			firstStepOutput = steps.get(0).formatOutput();
			output.add(firstStepOutput.get(headerRows));
		}
		
		return output;
	}
	
}