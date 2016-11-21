package com.smarterama.nburtnyk.division;

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
	
	
	
}