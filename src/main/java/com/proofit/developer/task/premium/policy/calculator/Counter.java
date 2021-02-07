package com.proofit.developer.task.premium.policy.calculator;

import com.proofit.developer.task.premium.policy.model.Policy;

import java.math.BigDecimal;

public interface Counter {
    BigDecimal calculate(Policy policy);
}
