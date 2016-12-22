package com.smarterama.nburtnyk.division;

import java.util.ArrayList;
import java.util.List;

public class LongDivision {
	
	private int dividend;
	private int divisor;
	private String result;
	
	private static final int HEADER_ROWS  = 3;
	
	public LongDivision(int dividend, int divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
	}
	
	private void checkDivisor(int divisor){
		if (divisor == 0) {
			throw new IllegalArgumentException("Divisor can not be 0"); 
		}
	}
	
	public void print(String divisionSteps) {
			System.out.println(divisionSteps);
	}
		
	public String divide() {
		checkDivisor(divisor);
		List<DivisionStep> steps = buildDivisionSteps();
		String output = buildOutputHeader(steps) + "\n"; 
		int shift = 0;
		
		// Processing all steps and creating list of strings for output.
		// Create result string.
		boolean firstStep = true;
		for (DivisionStep step : steps) {
			List<String> stepOutput = step.formatOutput();
			
			if (!firstStep) {
				String shiftString = repeatString(" ", shift);
				for (String line : stepOutput) {
					output += shiftString + line + "\n";
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
			output += repeatString(" ", shift) + difference;
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
	
	private String buildOutputHeader(List<DivisionStep> steps) {
		List<String> firstStepOutput;
		String header = (dividend > 0 && steps.size() > 0 ? " " : "") + dividend + " |" + divisor + "\n";
		dividend = Math.abs(dividend);
	
		if(!steps.isEmpty()) {
			header+= "-" + ("" + dividend).replaceAll("[0-9]", " ") + " |" + result.replaceAll("[0-9]", "-") + "\n" ;
			header+= " " + steps.get(0).getMinuend() + ("" + dividend).replaceAll("[0-9]", " ") + "|" + result + "\n";
			
			firstStepOutput = steps.get(0).formatOutput();
			header += firstStepOutput.get(HEADER_ROWS);
		} else {
			header+= ("" + dividend).replaceAll("[0-9]", " ") + " |" + ("" + divisor).replaceAll("[0-9]", "-") + "\n";
			header+= (" " + dividend).replaceAll("[0-9]", " ") + "|" + result;
		}
		
		return header;
	}
}