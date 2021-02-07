package com.proofit.developer.task.premium.policy.calculator.impl;

import com.proofit.developer.task.premium.policy.calculator.strategy.impl.FirePremiumCounterStrategy;
import com.proofit.developer.task.premium.policy.calculator.strategy.impl.TheftPremiumCounterStrategy;
import com.proofit.developer.task.premium.policy.enums.PolicyRiskTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PolicyTypeCounterStrategyFactoryTest {

    @InjectMocks
    private PolicyTypeCounterStrategyFactory policyTypeCounterStrategyFactory;

    @Mock
    private FirePremiumCounterStrategy firePremiumCounterStrategy;
    @Mock
    private TheftPremiumCounterStrategy theftPremiumCounterStrategy;

    @Test
    public void willCallFirePremiumCounterStrategy() {
        policyTypeCounterStrategyFactory.countTotalsForType(PolicyRiskTypeEnum.FIRE, BigDecimal.TEN);
        verify(firePremiumCounterStrategy, times(1)).calculateTotals(BigDecimal.TEN);
        verify(theftPremiumCounterStrategy, times(0)).calculateTotals(BigDecimal.TEN);
    }

    @Test
    public void willCallTheftPremiumCounterStrategy() {
        policyTypeCounterStrategyFactory.countTotalsForType(PolicyRiskTypeEnum.THEFT, BigDecimal.TEN);
        verify(firePremiumCounterStrategy, times(0)).calculateTotals(BigDecimal.TEN);
        verify(theftPremiumCounterStrategy, times(1)).calculateTotals(BigDecimal.TEN);
    }
}