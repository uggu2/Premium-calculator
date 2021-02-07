package com.proofit.developer.task.premium.policy.calculator.impl;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void calculateAboveThreshold() {
        assertEquals(BigDecimal.valueOf(5.0),
                Calculator.calculate(BigDecimal.TEN, 0.5,9.0,1.0));
    }

    @Test
    public void calculateBelowThreshold() {
        assertEquals(BigDecimal.valueOf(10.0),
                Calculator.calculate(BigDecimal.TEN, 0.5,11.0,1.0));
    }
}