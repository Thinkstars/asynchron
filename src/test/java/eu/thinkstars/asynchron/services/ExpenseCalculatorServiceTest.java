package eu.thinkstars.asynchron.services;

import eu.thinkstars.asynchron.enums.CalculationTyp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExpenseCalculatorServiceTest {

    private final BigDecimal PER_DIEMS_AMOUNT = new BigDecimal("140.00");
    private final BigDecimal DED_FOR_MILEAGE_AMOUNT = new BigDecimal("105.00");

    @Autowired
    private ExpenseCalculatorService calculatorService;

    @Test
    @DisplayName("Berechnen der Tagespauschale für 5 Tage")
    void calculatePerDiemsAmount() {
        assertEquals(PER_DIEMS_AMOUNT, calculatorService.calculateAmount(new BigDecimal("5"), CalculationTyp.PER_DIEMS.name()));
    }

    @Test
    @DisplayName("Berechnen der Entfernungspauschale für 350 km")
    void calculateMileageAmount() {
        assertEquals(DED_FOR_MILEAGE_AMOUNT, calculatorService.calculateAmount(new BigDecimal("350"), CalculationTyp.DEDUCTIONS_FOR_MILEAGE.name()));
    }
}