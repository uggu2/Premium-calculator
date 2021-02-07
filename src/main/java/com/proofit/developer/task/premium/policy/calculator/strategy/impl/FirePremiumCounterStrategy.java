package com.proofit.developer.task.premium.policy.calculator.strategy.impl;

import com.proofit.developer.task.premium.policy.calculator.impl.Calculator;
import com.proofit.developer.task.premium.policy.calculator.strategy.PremiumCounterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FirePremiumCounterStrategy implements PremiumCounterStrategy {

    private final Double threshold;
    private final Double thresholdCoefficient;
    private final Double defaultCoefficient;

    @Autowired
    public FirePremiumCounterStrategy(@Value("${fire.threshold}") Double threshold,
                                      @Value("${over-threshold.coefficient.fire}") Double thresholdCoefficient,
                                      @Value("${default.coefficient.fire}") Double defaultCoefficient) {
        this.threshold = threshold;
        this.thresholdCoefficient = thresholdCoefficient;
        this.defaultCoefficient = defaultCoefficient;
    }

    @Override
    public BigDecimal calculateTotals(final BigDecimal totalSum) {
        return Calculator.calculate(totalSum, thresholdCoefficient, threshold, defaultCoefficient);
    }
}
