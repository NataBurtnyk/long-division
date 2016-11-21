package com.smarterama.nburtnyk.division;

public class DivisionSteps {
	
	private int dividend;
	private int divisor;
	
	public DivisionSteps(int dividend, int divisor) {
		this.dividend = Math.abs(dividend);
		this.divisor = Math.abs(divisor);
	}

}
