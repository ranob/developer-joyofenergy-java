
package uk.tw.energy.mocks;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uk.tw.energy.interfaces.CalculateCostServiceInt;

@Service
public class CalculateCostServiceMock implements CalculateCostServiceInt{

	
	@Override
	public Optional<BigDecimal> calculateCosts(int numDays, String smartMeterId) {
		
		return Optional.of(BigDecimal.ONE);
	}


	

}