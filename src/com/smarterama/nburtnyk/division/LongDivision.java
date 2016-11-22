package com.smarterama.nburtnyk.division;

import java.util.ArrayList;
import java.util.List;

public class LongDivision {
	
	private int dividend;
	private int divisor;
	private String result;
	
	public LongDivision(int dividend, int divisor) {
		checkDivisor(divisor);
		this.dividend = dividend;
		this.divisor = divisor;
	}
	
	private void checkDivisor(int divisor){
		if (divisor == 0) {
			throw new IllegalArgumentException("Divisor can not be 0"); 
		}
	}
	
	public void divideWithOutput() {
		for (String line : buildOutputStrings()) {
			System.out.println(line);
		}
	}
	
	public List<String> buildOutputStrings() {
		List<DivisionStep> steps = buildDivisionSteps();
		List<String> output = buildOutputHeader(steps);
		int shift = 0;
	
		boolean firstStep = true;
		for (DivisionStep step : steps) {
			List<String> stepOutput = step.getOutput();
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
	
	protected List<DivisionStep> buildDivisionSteps() {
		List<DivisionStep> divisionSteps = new ArrayList<DivisionStep>();
		int currentDividend = Math.abs(dividend);
		result = "";

		while (currentDividend >= Math.abs(divisor)) {
			DivisionStep step = new DivisionStep(currentDividend, Math.abs(divisor));
			currentDividend = step.getNewDividend();
			result += step.reciaveResultDigitAsString();
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
	
	protected String findResultSign() {
		return (dividend / divisor) < 0 ? "-": "";
	}
	
	private List<String> buildOutputHeader(List<DivisionStep> steps) {
		List<String> output = new ArrayList<String>();
		List<String> firstStepOutput;
		String[] lines = { "", "", "" };
		if (steps.size() > 0) {
			firstStepOutput = steps.get(0).getOutput();
			for (int i = 0; i < 3; i++) {
				lines[i] = firstStepOutput.get(i);
			}
		}
		
		lines[0] = (dividend > 0 && steps.size() > 0 ? " " : "") + Integer.toString(dividend);
		int length = Math.max(lines[0].length(), lines[2].length()) + 1;
		
		for (int i = 0; i < 3; i++) {
			lines[i] += repeatString(" ", length - lines[i].length()) + "|";
		}
		lines[0] += Integer.toString(divisor);
		lines[2] += result;
		length = Math.max(result.length(), Integer.toString(divisor).length());
		lines[1] += repeatString("-", length);
		
		for (int i = 0; i < 3; i++) {
			output.add(lines[i]);
		}
		if (steps.size() > 0) {
			firstStepOutput = steps.get(0).getOutput();
			output.add(firstStepOutput.get(3));
		}
		return output;
	}
	
}