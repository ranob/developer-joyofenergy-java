package uk.tw.energy.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import uk.tw.energy.domain.ElectricityReading;

public interface MeterReadingServiceInt {  
  
  Optional<List<ElectricityReading>> getReadings(String smartMeterId);

  void storeReadings(String smartMeterId, List<ElectricityReading> electricityReadings);
        
}
