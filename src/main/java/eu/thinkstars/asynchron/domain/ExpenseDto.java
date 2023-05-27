package eu.thinkstars.asynchron.domain;

import eu.thinkstars.asynchron.enums.CalculationTyp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExpenseDto {

    private Long id;
    private String expenseName;
    private CalculationTyp expenseType;
    private BigDecimal amount;
    private BigDecimal millage;
    private BigDecimal dayRange;
    private String comment;
    private Date dateFrom;
    private Date dateTo;

    public ExpenseDto() {
        this.dateFrom = Date.valueOf(LocalDate.now());
        this.dateTo = Date.valueOf(LocalDate.now());
    }
}
