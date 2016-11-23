package com.smarterama.nburtnyk.division;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LongDivisionTest {
		
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testConditionWithDivisorZero() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Divisor can not be 0");
		new LongDivision(744, 0);
	}
	
	@Test
	public void testConditionDidits() {
		LongDivision longDivision = new LongDivision(36, 3);
		List<String> outputStrings = longDivision.buildOutputStrings();
		String[] expectedOutput = new String[]{
 				" 36 |3",
 				"-   |--",
 				" 3  |12",
 				" -",
 				"  6",
 				" -",
 				"  6",
 				"  -",
 				"  0"};
		
		for (int i = 0; i < expectedOutput.length; i++) {
 			assertEquals(expectedOutput[i], outputStrings.get(i));	
		}
		
	}
	
	
}