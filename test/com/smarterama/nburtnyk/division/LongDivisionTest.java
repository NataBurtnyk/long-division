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
		new LongDivision(744, 0).divide();
	}
	
	@Test
	public void testConditionWithDigitsLikeInTask() {
		LongDivision longDivision = new LongDivision(78459, 4);
		String outputStrings = longDivision.divide();
		String expectedOutput = 
 				" 78459 |4"     + "\n" +
 				"-      |-----" + "\n" +
 				" 4     |19614" + "\n" + 
 				" -"      + "\n" +
 				" 38"     + "\n" +
 				"-"       + "\n" +
 				" 36"     + "\n" +
 				" --"     + "\n" +
 				"  24"    + "\n" +
 				" -"      + "\n" +
 				"  24"    + "\n" +
 				"  --"    + "\n" +
 				"    5"   + "\n" +
 				"   -"    + "\n" +
 				"    4"   + "\n" +
 				"    -"   + "\n" +
 				"    19"  + "\n" +
 				"   -"    + "\n" +
 				"    16"  + "\n" +
 				"    --"  + "\n" +
 				"     3";

 			assertEquals(expectedOutput, outputStrings);	
	}
	
	@Test
	public void testConditionWithDigits() {
		LongDivision longDivision = new LongDivision(36, 3);
		String outputStrings = longDivision.divide();
		String expectedOutput = 
 				" 36 |3"  + "\n" +
 				"-   |--" + "\n" +
 				" 3  |12" + "\n" +
 				" -"      + "\n" +
 				"  6"     + "\n" +
 				" -"      + "\n" +
 				"  6"     + "\n" +
 				"  -"     + "\n" +
 				"  0";
		
 			assertEquals(expectedOutput, outputStrings);
	}
	
	@Test
	public void testConditionWithNegativeDivisor() {
		LongDivision longDivision = new LongDivision(68, -4);
		String outputStrings = longDivision.divide();
		String expectedOutput = 
 				" 68 |-4"  + "\n" +
 				"-   |---" + "\n" +
 				" 4  |-17" + "\n" +
 				" -"       + "\n" +
 				" 28"      + "\n" +
 				"-"        + "\n" +
 				" 28"      + "\n" +
 				" --"      + "\n" +
 				"  0";
		
 			assertEquals(expectedOutput, outputStrings);	
	}
	
	@Test
	public void testConditionWithNegativeDigits() {
		LongDivision longDivision = new LongDivision(-968, -4);
		String outputStrings = longDivision.divide();
		String expectedOutput =
 				"-968 |-4"  + "\n" +
 				"-    |---" + "\n" +
 				" 8   |242" + "\n" +
 				" -"        + "\n" +
 				" 16"       + "\n" +
 				"-"         + "\n" +
 				" 16"       + "\n" +
 				" --"       + "\n" +
 				"   8"      + "\n" +
 				"  -"       + "\n" +
 				"   8"      + "\n" +
 				"   -"      + "\n" +
 				"   0";
	
 			assertEquals(expectedOutput, outputStrings);	
	}
	
	@Test
	public void testConditionWithDividendZero() {
		LongDivision longDivision = new LongDivision(0, 8);
		String outputStrings = longDivision.divide();
		String expectedOutput =
 				"0 |8" + "\n" +
 				"  |-" + "\n" +
 				"  |0" + "\n";
		
 			assertEquals(expectedOutput, outputStrings);	
	}
	
	@Test
	public void testConditionWhenDivisorLargerThanDividend() {
		LongDivision longDivision = new LongDivision(8, 20);
		String outputStrings = longDivision.divide();
		String expectedOutput = 
 				"8 |20" + "\n" +
 				"  |--" + "\n" +
 				"  |0"  + "\n";
	
 			assertEquals(expectedOutput, outputStrings);		
	}
}