package com.proofit.developer.task.premium.policy.model;

import com.proofit.developer.task.premium.policy.enums.PolicyStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Policy {
    private long policyNumber;
    private PolicyStatusEnum policyStatus;
    private List<PolicyObject> policies;
}
