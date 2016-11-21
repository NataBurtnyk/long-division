package com.smarterama.nburtnyk.division;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IntegerDivisionTest {
		
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testConditionWithNull() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Divisor can not be 0");
		new LongDivision(744, 0);
	}
}