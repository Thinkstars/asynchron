package eu.thinkstars.asynchron.configuration;

import eu.thinkstars.asynchron.data.Expense;
import eu.thinkstars.asynchron.enums.CalculationTyp;
import eu.thinkstars.asynchron.repositories.ExpensesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Configuration
public class InitializeDatabase {

    private final ExpensesRepository expensesRepository;

    public InitializeDatabase(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            Expense expense1 = Expense.builder().expenseName("Fahrt nach München")
                    .expenseType(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                    .millage(new BigDecimal("698"))
                    .amount(new BigDecimal("209.40"))
                    .dateFrom(Date.valueOf(LocalDateTime.of(2023, Month.MAY, 22, 0, 0, 0).toLocalDate()))
                    .dateTo(Date.valueOf(LocalDateTime.of(2023, Month.MAY, 22, 0, 0, 0).toLocalDate()))
                    .build();
            Expense expense2 = Expense.builder().expenseName("Fahrt nach Solingen")
                    .expenseType(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                    .millage(new BigDecimal("698"))
                    .amount(new BigDecimal("209.40"))
                    .dateFrom(Date.valueOf(LocalDateTime.of(2023, Month.MAY, 26, 0, 0, 0).toLocalDate()))
                    .dateTo(Date.valueOf(LocalDateTime.of(2023, Month.MAY, 26, 0, 0, 0).toLocalDate()))
                    .build();
            Expense expense3 = Expense.builder().expenseName("fünf Tage in München")
                    .expenseType(CalculationTyp.PER_DIEMS)
                    .amount(new BigDecimal("112.00"))
                    .dateFrom(Date.valueOf(LocalDateTime.of(2023, Month.MAY, 22, 0, 0, 0).toLocalDate()))
                    .dateTo(Date.valueOf(LocalDateTime.of(2023, Month.MAY, 26, 0, 0, 0).toLocalDate()))
                    .build();

            expensesRepository.saveAll(List.of(expense1, expense2, expense3));
        };
    }
}
