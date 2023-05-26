package eu.thinkstars.asynchron.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ExpenseAmount {

    @JsonProperty("amount")
    private final BigDecimal amount;
}
