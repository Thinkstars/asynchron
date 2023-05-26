package eu.thinkstars.asynchron.domain;

import eu.thinkstars.asynchron.enums.CalculationTyp;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {

    private Long id;
    private String expenseName;
    private CalculationTyp expenseType;
    private BigDecimal amount;
    private BigDecimal millage;
    private BigDecimal dayRange;
    private String comment;
}
