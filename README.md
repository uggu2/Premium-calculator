This Premium Calculator was implemented following Proof IT's provided test task.
For the task I chose to use SpringBoot, Java 11 and Maven wrapper. 

Premium Counter accepts Policy, which holds information about PolicyObjects and PolicySubObjects.
Based on the PolicySubObject types and insured sum, we can count total sum for insurance.

Various coefficients provided in the task are configurable in case they need to be quickly updated
or are different between environments.
I chose to use strategy pattern for counting sums by type, because it allows different
calculation strategies to be introduced in case of new risk types.

You can find the testcases for scenarios mentioned in acceptance criteria in PremiumCounterApplicationTests.
