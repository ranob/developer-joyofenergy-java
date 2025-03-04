package uk.tw.energy.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.tw.energy.interfaces.CalculateCostServiceInt;


@RestController
@RequestMapping("/cost")
public class CalculateCostController {

    public CalculateCostController(CalculateCostServiceInt calculateCostServiceInt) {
        this.calculateCostServiceInt = calculateCostServiceInt;
    }
    private CalculateCostServiceInt calculateCostServiceInt;

    @GetMapping("/lastsevendays/{smartMeterId}")
    public ResponseEntity<BigDecimal> method(@PathVariable(name="smartMeterId") String smartMeterId) {
        Optional<BigDecimal> costs = calculateCostServiceInt.calculateCosts(7, "smart-meter-0");
        
        return costs.isPresent()
                ? ResponseEntity.ok(costs.get())
                : ResponseEntity.notFound().build();
    }


}