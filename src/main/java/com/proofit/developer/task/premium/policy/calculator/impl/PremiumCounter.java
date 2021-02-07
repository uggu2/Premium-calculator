package com.proofit.developer.task.premium.policy.calculator.impl;

import com.proofit.developer.task.premium.policy.calculator.Counter;
import com.proofit.developer.task.premium.policy.enums.PolicyRiskTypeEnum;
import com.proofit.developer.task.premium.policy.model.Policy;
import com.proofit.developer.task.premium.policy.model.PolicyObject;
import com.proofit.developer.task.premium.policy.model.PolicySubObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PremiumCounter implements Counter {

    @Autowired
    private PolicyTypeCounterStrategyFactory policyTypeCounterStrategyFactory;

    @Override
    public BigDecimal calculate(final Policy policy) {
        return Arrays.stream(PolicyRiskTypeEnum.values())
                .map(policyRiskTypeEnum -> {
                    final List<PolicySubObject> subObjectsByType = getByType(policy.getPolicies(), policyRiskTypeEnum);
                    return policyTypeCounterStrategyFactory
                            .countTotalsForType(policyRiskTypeEnum, getTotalSumInsured(subObjectsByType));
                })
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getTotalSumInsured(List<PolicySubObject> policySubObjects) {
        return policySubObjects.stream()
                .map(policySubObject -> BigDecimal.valueOf(policySubObject.getSumInsured()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private List<PolicySubObject> getByType(List<PolicyObject> policyObjects, PolicyRiskTypeEnum riskType) {
        return policyObjects.stream()
                .map(PolicyObject::getPolicySubObjects)
                .flatMap(Collection::stream)
                .filter(policySubObject -> policySubObject.getRiskType().equals(riskType))
                .collect(Collectors.toList());
    }
}
