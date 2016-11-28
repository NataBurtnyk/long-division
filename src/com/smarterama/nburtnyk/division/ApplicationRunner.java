package com.smarterama.nburtnyk.division;

import java.util.List;

public class ApplicationRunner {

	public static void main(String[] args) {
		LongDivision division = new LongDivision(21054,31);
		List<String> outputStrings = division.buildOutputStrings();
		division.printDivisionSteps(outputStrings);
	}
}