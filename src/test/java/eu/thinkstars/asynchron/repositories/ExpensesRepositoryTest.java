package eu.thinkstars.asynchron.repositories;

import eu.thinkstars.asynchron.data.Expense;
import eu.thinkstars.asynchron.domain.ExpenseDto;
import eu.thinkstars.asynchron.enums.CalculationTyp;
import eu.thinkstars.asynchron.utils.ExpenseMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ExpensesRepositoryTest {

    @Autowired
    private ExpensesRepository expenseRepository;

    private final ExpenseMapper expenseMapper = Mappers.getMapper(ExpenseMapper.class);

    @Test
    @DisplayName("Kosteninstanz erstellen und speichern")
    void createAndPersistExpense() {
        ExpenseDto expenseDto =
                new ExpenseDto(null, "Fahrt nach Stuttgart", CalculationTyp.DEDUCTIONS_FOR_MILEAGE, null,
                        new BigDecimal(237), null, "", Date.valueOf("2023-01-10"), Date.valueOf("2023-01-15"));

        Expense expense = expenseRepository.save(expenseMapper.dtoToEntity(expenseDto));

        assertNotNull(expense.getId());
    }
}