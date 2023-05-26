package eu.thinkstars.asynchron.controller;

import eu.thinkstars.asynchron.enums.CalculationTyp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;

@ControllerAdvice
public class AllControllerAdvice {

    @ModelAttribute("calculationTypes")
    public Collection<CalculationTyp> calculationTypes() {
        return CalculationTyp.allAvailableCalculationTypes();
    }
}
