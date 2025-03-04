package uk.tw.energy.interfaces;

import java.math.BigDecimal;
import java.util.Optional;

public interface CalculateCostServiceInt {

	Optional<BigDecimal> calculateCosts(int numDays,String smartMeterId);
}
