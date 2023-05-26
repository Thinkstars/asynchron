package eu.thinkstars.asynchron.controller;

import eu.thinkstars.asynchron.domain.ExpenseAmount;
import eu.thinkstars.asynchron.services.ExpenseCalculatorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ExpensesCalculationController {

    private final ExpenseCalculatorService calculatorService;

    public ExpensesCalculationController(ExpenseCalculatorService expenseCalculatorService) {
        this.calculatorService = expenseCalculatorService;
    }

    @GetMapping(value = "/calculateamount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpenseAmount> calculateAmount(@RequestParam(name = "multiplicator") String multiplicator,
                                                         @RequestParam(name = "calculationTyp") String calculationTyp) {
        return ResponseEntity.ok(new ExpenseAmount(calculatorService.calculateAmount(new BigDecimal(multiplicator), calculationTyp)));
    }

}
