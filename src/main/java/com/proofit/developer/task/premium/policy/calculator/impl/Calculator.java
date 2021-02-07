package com.proofit.developer.task.premium.policy.calculator.impl;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Calculator {
    public static BigDecimal calculate(final BigDecimal totalSum, final Double thresholdCoefficient,
                                       final Double thresholdValue, final Double defaultCoefficient) {

        if (totalSumIsAboveThreshold(totalSum, thresholdValue)) {
            return multiplyByCoefficient(totalSum, thresholdCoefficient);
        }
        else {
            return multiplyByCoefficient(totalSum, defaultCoefficient);
        }
    }

    private static boolean totalSumIsAboveThreshold(BigDecimal totalSum, Double thresholdValue) {
        return totalSum.compareTo(BigDecimal.valueOf(thresholdValue)) > 0;
    }

    private static BigDecimal multiplyByCoefficient(BigDecimal totalSum, Double coefficient) {
        return totalSum.multiply(BigDecimal.valueOf(coefficient));
    }
}
