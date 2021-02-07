package com.proofit.developer.task.premium.policy.model;

import com.proofit.developer.task.premium.policy.enums.PolicyRiskTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PolicySubObject {
    private String name;
    private double sumInsured;
    private PolicyRiskTypeEnum riskType;
}
