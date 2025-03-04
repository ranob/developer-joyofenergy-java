package uk.tw.energy.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import uk.tw.energy.ConfigBeans;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.interfaces.CalculateCostServiceInt;
import uk.tw.energy.interfaces.MeterReadingServiceInt;

@SpringBootTest(classes = {ConfigBeans.class,CalculateCostService.class})
class CalculateCostServiceTest {

  
    
    @Autowired
    private MeterReadingServiceInt meterReadingService;

    private CalculateCostServiceInt s1;

    @BeforeEach
    void setUp() {
    
        s1 = new CalculateCostService(meterReadingService);
    }

    @Test
    void givenTheSmartMeterDontExistsShouldReturnEmpty() {
              
        when(meterReadingService.getReadings("unknown")).thenReturn(java.util.Optional.of(new ArrayList<ElectricityReading>()));       
        assertThat(s1.calculateCosts(1, "unknown")).isEqualTo(Optional.of(BigDecimal.ZERO));
    	
    

    	
    }
    
    @Test
    void givenTheSmartMeterExist()  {
        
        List<ElectricityReading> electricityReadings = CalculateCostServiceTest.generateAndProcessReadingsForLast10Days();
        when(meterReadingService.getReadings("smart-meter-0")).thenReturn(java.util.Optional.of(electricityReadings));       
        //meterReadingService.storeReadings("smart-meter-0", electricityReadings);
    	BigDecimal cost = BigDecimal.valueOf(0.01)
                .multiply(BigDecimal.valueOf(7))
                .multiply(BigDecimal.valueOf(7))
                .multiply(BigDecimal.valueOf(24));                
        assertThat(s1.calculateCosts(7, "smart-meter-0").get()).isEqualTo(cost);
    	
    }

    

    private static  List<ElectricityReading> generateAndProcessReadingsForLast10Days() {
        Instant now = Instant.now();
        List<ElectricityReading> electricityReadings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Instant time = now.minus(i, ChronoUnit.DAYS);
            //BigDecimal reading = new BigDecimal(Math.random() * 10); // Genera una lectura aleatoria

            ElectricityReading electricityReading = new ElectricityReading(time, BigDecimal.valueOf(0.01));
            electricityReadings.add(electricityReading);
            // Procesa la lectura individualmente (por ejemplo, imprímela)
            System.out.println(electricityReading);

            // Aquí puedes agregar cualquier otra lógica de procesamiento que necesites
        }
        return electricityReadings;
    }


}
