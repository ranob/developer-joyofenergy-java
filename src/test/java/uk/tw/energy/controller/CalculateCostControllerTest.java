package uk.tw.energy.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import uk.tw.energy.ConfigBeans;
import uk.tw.energy.builders.MeterReadingsBuilder;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.MeterReadings;
import uk.tw.energy.interfaces.CalculateCostServiceInt;
import uk.tw.energy.mocks.CalculateCostServiceMock;
import uk.tw.energy.service.MeterReadingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.FilterType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc 
//Añadir configuración para el contexto de la aplicación
//@WebMvcTest(controllers ={CalculateCostController.class}, includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ConfigBeans.class))
@WebMvcTest(controllers ={CalculateCostController.class}, includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ConfigBeans.class))
 class CalculateCostControllerTest {

    @Autowired
	private MockMvc mockMvc;
    
    @Autowired
    private CalculateCostServiceInt calculateCostServiceInt;

    @BeforeEach
    void setUp() {
      
    }

    @Test    
    void givenTheSmartMeterHasValues() throws Exception {        
    
        when(calculateCostServiceInt.calculateCosts(7,"smart-meter-0")).thenReturn(Optional.of(BigDecimal.TEN));
        this.mockMvc.perform(get("/cost/lastsevendays/smart-meter-0")).andExpect(status().isOk()).andExpect(content().string(BigDecimal.TEN.toString()));           
           
    }

    @Test    
    void givenNoSmtartMeterIsProvided() throws Exception {        
              
      this.mockMvc.perform(get("/cost/lastsevendays/")).andExpect(status().isNotFound());      
    
    }
   
   
}
