package com.smarterama.nburtnyk.division;

import java.util.List;

public class ApplicationRunner {

	public static void main(String[] args) {
		LongDivision division = new LongDivision(36, 6);
		List<String> outputStrings = division.buildOutputStrings();
		division.print(outputStrings);
	}
}