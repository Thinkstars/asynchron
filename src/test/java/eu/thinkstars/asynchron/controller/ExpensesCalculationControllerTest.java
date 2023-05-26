package eu.thinkstars.asynchron.controller;

import eu.thinkstars.asynchron.services.ExpenseCalculatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExpensesCalculationController.class)
class ExpensesCalculationControllerTest {

    private final String URL = "/calculateamount?multiplicator=10&calculationTyp=PER_DIEMS";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseCalculatorService expenseCalculatorService;

    @Test
    void calculateAmount() throws Exception {
        Mockito.when(expenseCalculatorService.calculateAmount(any(BigDecimal.class), any(String.class))).thenReturn(new BigDecimal("200.00"));

        this.mockMvc.perform(get(URL)).andExpect(status().isOk()).andExpect(content().json("{'amount' :  200.00}"));
    }
}