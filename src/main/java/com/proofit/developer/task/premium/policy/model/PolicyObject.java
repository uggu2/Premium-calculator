package com.proofit.developer.task.premium.policy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PolicyObject {
    private String name;
    private List<PolicySubObject> policySubObjects;
}
