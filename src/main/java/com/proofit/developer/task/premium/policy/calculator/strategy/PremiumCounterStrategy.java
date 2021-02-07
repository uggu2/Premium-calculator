package com.proofit.developer.task.premium.policy.calculator.strategy;

import java.math.BigDecimal;

public interface PremiumCounterStrategy {
    BigDecimal calculateTotals(final BigDecimal totalSum);
}
