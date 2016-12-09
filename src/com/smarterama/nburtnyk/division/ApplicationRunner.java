package com.smarterama.nburtnyk.division;

import java.util.List;

public class ApplicationRunner {

	public static void main(String[] args) {
		LongDivision division = new LongDivision(12332123, -4);
		List<String> divideTwoNumbers = division.divideTwoNumbers();
		division.print(divideTwoNumbers);
	}
}