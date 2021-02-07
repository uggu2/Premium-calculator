package com.proofit.developer.task.premium;

import com.google.common.collect.ImmutableList;
import com.proofit.developer.task.premium.policy.calculator.impl.PremiumCounter;
import com.proofit.developer.task.premium.policy.enums.PolicyRiskTypeEnum;
import com.proofit.developer.task.premium.policy.enums.PolicyStatusEnum;
import com.proofit.developer.task.premium.policy.model.Policy;
import com.proofit.developer.task.premium.policy.model.PolicyObject;
import com.proofit.developer.task.premium.policy.model.PolicySubObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class PremiumCounterApplicationTests {

	@Autowired
	private PremiumCounter premiumCounter;

	@Test
	void countsTotalsBelowThreshold() {
		final PolicySubObject fireSubObject = new PolicySubObject("TV", 100, PolicyRiskTypeEnum.FIRE);
		final PolicySubObject theftSubObject = new PolicySubObject("PC", 8, PolicyRiskTypeEnum.THEFT);
		final PolicyObject policyObject = new PolicyObject("Home", ImmutableList.of(fireSubObject, theftSubObject));
		final Policy policy = new Policy(1, PolicyStatusEnum.REGISTERED, Collections.singletonList(policyObject));

		Assertions.assertEquals(BigDecimal.valueOf(2.28), premiumCounter.calculate(policy));
	}

	@Test
	void countsTotalsAboveThreshold() {
		final PolicySubObject fireSubObject = new PolicySubObject("TV", 500, PolicyRiskTypeEnum.FIRE);
		final PolicySubObject theftSubObject = new PolicySubObject("PC", 102.51, PolicyRiskTypeEnum.THEFT);
		final PolicyObject policyObject = new PolicyObject("Home", ImmutableList.of(fireSubObject, theftSubObject));
		final Policy policy = new Policy(1, PolicyStatusEnum.REGISTERED, Collections.singletonList(policyObject));

		Assertions.assertEquals(BigDecimal.valueOf(17.13), premiumCounter.calculate(policy));
	}
}
