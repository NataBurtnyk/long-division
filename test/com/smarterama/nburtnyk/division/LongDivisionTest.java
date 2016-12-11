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
		new LongDivision(744, 0).divideTwoNumbers();
	}
	
	@Test
	public void testConditionWithDigitsLikeInTask() {
		LongDivision longDivision = new LongDivision(78459, 4);
		List<String> outputStrings = longDivision.divideTwoNumbers();
		String[] expectedOutput = new String[]{
 				" 78459 |4" + "\n"+
 				"-      |-----" + "\n" +
 				" 4     |19614" + "\n" + 
 				" -",
 				" 38",
 				"-",
 				" 36",
 				" --",
 				"  24",
 				" -",
 				"  24",
 				"  --",
 				"    5",
 				"   -",
 				"    4",
 				"    -",
 				"    19",
 				"   -",
 				"    16",
 				"    --",
 				"     3"};
		
		for (int i = 0; i < expectedOutput.length; i++) {
 			assertEquals(expectedOutput[i], outputStrings.get(i));	
		}
		
	}
	
	@Test
	public void testConditionWithDigits() {
		LongDivision longDivision = new LongDivision(36, 3);
		List<String> outputStrings = longDivision.divideTwoNumbers();
		String[] expectedOutput = new String[]{
 				" 36 |3" + "\n"+
 				"-   |--" + "\n"+
 				" 3  |12" + "\n"+
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
	
	@Test
	public void testConditionWithNegativeDivisor() {
		LongDivision longDivision = new LongDivision(68, -4);
		List<String> outputStrings = longDivision.divideTwoNumbers();
		String[] expectedOutput = new String[]{
 				" 68 |-4" + "\n"+
 				"-   |---" + "\n"+
 				" 4  |-17" + "\n"+
 				" -",
 				" 28",
 				"-",
 				" 28",
 				" --",
 				"  0"};
		
		for (int i = 0; i < expectedOutput.length; i++) {
 			assertEquals(expectedOutput[i], outputStrings.get(i));	
		}
		
	}
	
	@Test
	public void testConditionWithNegativeDigits() {
		LongDivision longDivision = new LongDivision(-968, -4);
		List<String> outputStrings = longDivision.divideTwoNumbers();
		String[] expectedOutput = new String[]{
 				"-968 |-4" + "\n"+
 				"-    |---" + "\n"+
 				" 8   |242" + "\n"+
 				" -",
 				" 16",
 				"-",
 				" 16",
 				" --",
 				"   8",
 				"  -",
 				"   8",
 				"   -",
 				"   0"};
		
		for (int i = 0; i < expectedOutput.length; i++) {
 			assertEquals(expectedOutput[i], outputStrings.get(i));	
		}
		
	}
	
	@Test
	public void testConditionWithDividendZero() {
		LongDivision longDivision = new LongDivision(0, 8);
		List<String> outputStrings = longDivision.divideTwoNumbers();
		String[] expectedOutput = new String[]{
 				"0 |8" + "\n"+
 				"  |-" + "\n"+
 				"  |0" + "\n"};
		
		for (int i = 0; i < expectedOutput.length; i++) {
 			assertEquals(expectedOutput[i], outputStrings.get(i));	
		}
		
	}
	
	@Test
	public void testConditionWhenDivisorLargerThanDividend() {
		LongDivision longDivision = new LongDivision(8, 20);
		List<String> outputStrings = longDivision.divideTwoNumbers();
		String[] expectedOutput = new String[]{
 				"8 |20" + "\n"+
 				"  |--" + "\n"+
 				"  |0" + "\n"};
		
		for (int i = 0; i < expectedOutput.length; i++) {
 			assertEquals(expectedOutput[i], outputStrings.get(i));	
		}
		
	}
	
	
}