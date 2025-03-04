
package uk.tw.energy;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import uk.tw.energy.interfaces.CalculateCostServiceInt;
import uk.tw.energy.interfaces.MeterReadingServiceInt;
import uk.tw.energy.mocks.CalculateCostServiceMock;

@Configuration
public class ConfigBeans {

   
    /*@Bean
    public CalculateCostServiceMock calculateCostServiceMock() {
        return new CalculateCostServiceMock();        
    }*/

    @Bean
    @Primary
    public CalculateCostServiceInt nameService() {
        return Mockito.mock(CalculateCostServiceInt.class);
    }

    @Bean
    @Primary
    public MeterReadingServiceInt MeterReadingsMock() {
        return Mockito.mock(MeterReadingServiceInt.class);
    }

   
}
