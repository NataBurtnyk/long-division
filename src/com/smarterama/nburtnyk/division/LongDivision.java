package com.smarterama.nburtnyk.division;

import java.util.ArrayList;
import java.util.List;

public class LongDivision {
	
	private int dividend;
	private int divisor;
	private String result;
	
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
		
	public List<String> buildOutputStrings() {
		checkDivisor(divisor);
		List<DivisionStep> steps = buildDivisionSteps();
		List<String> output = buildOutputHeader(steps);
		int shift = 0;
	
		boolean firstStep = true;
		for (DivisionStep step : steps) {
			List<String> stepOutput = step.shapeOutput();
			if (!firstStep) {
				String shiftString = repeatString(" ", shift);
				for (String line : stepOutput) {
					output.add(shiftString + line);
				}
			}
			shift += Integer.toString(step.getDeduction()).length();
			if (step.getDifference() > 0){
				shift -= Integer.toString(step.getDifference()).length();
			}
			firstStep = false;
		}
		if (steps.size() > 0) {
			DivisionStep lastStep = steps.get(steps.size() - 1);
			String difference = Integer.toString(lastStep.getDifference());
			shift++;
			if (lastStep.getDifference() == 0) {
				if (lastStep.getNewDividend() == 0) {
					shift--;
				}
				else {
					difference = Integer.toString(lastStep.getNewDividend());
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
			result += step.getPartResult();
			divisionSteps.add(step);
		}
		if (result.length() > 0) {
			DivisionStep lastStep = divisionSteps.get(divisionSteps.size() - 1);
			if (lastStep.getDifference() == 0 && lastStep.getNewDividend() != 0)
				result += "0";
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
		int headerRows = 3;
		List<String> firstStepOutput;
		String[] rows = { "", "", "" };
				
		if (steps.size() > 0) {
			firstStepOutput = steps.get(0).shapeOutput();
			for (int i = 0; i < headerRows; i++) {
				rows[i] = firstStepOutput.get(i);
			}
		}
		
		rows[0] = (dividend > 0 && steps.size() > 0 ? " " : "") + "" + dividend;
		int rowLength = Math.max(rows[0].length(), rows[2].length()) + 1;

		for (int i = 0; i < headerRows; i++) {
			rows[i] += repeatString(" ", rowLength - rows[i].length()) + "|";
		}
		
		rows[0] += "" + divisor;
		rows[2] += result;
		int splitterHorizontalLineLenght = Math.max(result.length(), String.valueOf(Math.abs(divisor)).length());
		rows[1] += repeatString("-", splitterHorizontalLineLenght);
		
		for (int i = 0; i < headerRows; i++) {
			output.add(rows[i]);
		}
		
		if (steps.size() > 0) {
			firstStepOutput = steps.get(0).shapeOutput();
			output.add(firstStepOutput.get(headerRows));
		}
		
		return output;
	}
	
}