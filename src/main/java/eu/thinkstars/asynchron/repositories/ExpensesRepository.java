package eu.thinkstars.asynchron.repositories;

import eu.thinkstars.asynchron.data.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expense, Long> {
}
