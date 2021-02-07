package com.proofit.developer.task.premium.policy.calculator.impl;

import com.google.common.collect.ImmutableList;
import com.proofit.developer.task.premium.policy.enums.PolicyRiskTypeEnum;
import com.proofit.developer.task.premium.policy.enums.PolicyStatusEnum;
import com.proofit.developer.task.premium.policy.model.Policy;
import com.proofit.developer.task.premium.policy.model.PolicyObject;
import com.proofit.developer.task.premium.policy.model.PolicySubObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PremiumCounterTest {

    private static final Double SUM_INSURED = 15.0;

    @Mock
    private PolicyTypeCounterStrategyFactory policyTypeCounterStrategyFactory;
    @InjectMocks
    private PremiumCounter premiumCounter;

    @Before
    public void setup() {
        when(policyTypeCounterStrategyFactory.countTotalsForType(any(), any())).thenReturn(BigDecimal.ONE);
    }

    @Test
    public void willCountPremiumBasedOnPolicyType() {
        final Policy policy = setupPolicy();

        premiumCounter.calculate(policy);
        verify(policyTypeCounterStrategyFactory, times(1))
                .countTotalsForType(PolicyRiskTypeEnum.FIRE, BigDecimal.valueOf(SUM_INSURED));
        verify(policyTypeCounterStrategyFactory, times(1))
                .countTotalsForType(PolicyRiskTypeEnum.THEFT, BigDecimal.valueOf(SUM_INSURED));
    }

    private Policy setupPolicy() {
        final PolicySubObject fireSubObject = new PolicySubObject("TV", SUM_INSURED, PolicyRiskTypeEnum.FIRE);
        final PolicySubObject theftSubObject = new PolicySubObject("PC", SUM_INSURED, PolicyRiskTypeEnum.THEFT);
        final PolicyObject policyObject = new PolicyObject("Home", ImmutableList.of(fireSubObject, theftSubObject));

        return new Policy(1, PolicyStatusEnum.REGISTERED, Collections.singletonList(policyObject));
    }
}