package com.proofit.developer.task.premium.policy.calculator.impl;

import com.proofit.developer.task.premium.policy.calculator.strategy.impl.FirePremiumCounterStrategy;
import com.proofit.developer.task.premium.policy.calculator.strategy.impl.TheftPremiumCounterStrategy;
import com.proofit.developer.task.premium.policy.enums.PolicyRiskTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PolicyTypeCounterStrategyFactory {

    @Autowired
    private FirePremiumCounterStrategy firePremiumCounterStrategy;
    @Autowired
    private TheftPremiumCounterStrategy theftPremiumCounterStrategy;

    public BigDecimal countTotalsForType(final PolicyRiskTypeEnum policyRiskTypeEnum, final BigDecimal total) {
        switch (policyRiskTypeEnum) {
            case THEFT: {
                return theftPremiumCounterStrategy.calculateTotals(total);
            }
            case FIRE: {
                return firePremiumCounterStrategy.calculateTotals(total);
            }
            default: {
                return BigDecimal.ZERO;
            }
        }
    }
}
