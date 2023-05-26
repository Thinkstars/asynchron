package eu.thinkstars.asynchron.controller;

import eu.thinkstars.asynchron.domain.ExpenseDto;
import eu.thinkstars.asynchron.services.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class ExpensesController {

    private ExpensesService expensesService;
    public static final String EXPENSES = "expenses";

    @GetMapping("/expenses")
    public String loadAllData(Model model) {
        model.addAttribute(EXPENSES, expensesService.fetchAllExpenses());
        BigDecimal summe = expensesService.fetchAllExpenses().stream().filter(Objects::nonNull)
                .map(ExpenseDto::getAmount).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("sum", summe);

        return EXPENSES;
    }

    @GetMapping("/expenses/{id}")
    public String loadExpensesData(Model model, @PathVariable("id") String id) {
        ExpenseDto expense = "create".equals(id) ? new ExpenseDto() : expensesService.fetchExpense(Long.valueOf(id));
        model.addAttribute("expense", expense);

        return "expense";
    }

    @PostMapping("/expenses")
    public String saveExpense(@ModelAttribute("expense") ExpenseDto expense) {
        expensesService.persistExpense(expense);

        return "redirect:/" + EXPENSES;
    }

    @GetMapping("/expenses/delete/{id}")
    public String dropExpense(@PathVariable("id") String id) {
        expensesService.dropExpense(Long.valueOf(id));

        return "redirect:/" + EXPENSES;
    }
}
