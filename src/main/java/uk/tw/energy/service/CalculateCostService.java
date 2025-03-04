package uk.tw.energy.service;

import java.lang.foreign.Linker.Option;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.interfaces.CalculateCostServiceInt;
import uk.tw.energy.interfaces.MeterReadingServiceInt;

@Service
public class CalculateCostService implements CalculateCostServiceInt{

	private MeterReadingServiceInt meterReadingService;

	public CalculateCostService(MeterReadingServiceInt meterReadingService) {
		this.meterReadingService = meterReadingService;
	}
	@Override
	public Optional<BigDecimal> calculateCosts(int numDays, String smartMeterId) {
		Optional<List<ElectricityReading>> readings = meterReadingService.getReadings(smartMeterId);		
		BigDecimal cost = BigDecimal.ZERO;
		BigDecimal hoursIn7Days = BigDecimal.valueOf(7L * 24);

		if (readings.isPresent() && !readings.get().isEmpty()) {
           
            Instant hace7Dias = Instant.now().minus(7, ChronoUnit.DAYS);

			cost = readings.get().stream()
                    .filter(reading -> reading.time().isAfter(hace7Dias))
                    .map(ElectricityReading::reading) // Obtiene el valor de la lectura (kW)
            //        .map(kw -> kw.multiply(BigDecimal.valueOf(1000))) // Convierte kW a W
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
			cost = cost.multiply(hoursIn7Days);
        }
		return Optional.of(cost);
	}


	

}
