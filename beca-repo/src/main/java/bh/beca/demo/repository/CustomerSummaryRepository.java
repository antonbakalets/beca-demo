package bh.beca.demo.repository;

import bh.beca.demo.model.CustomerSummary;
import bh.beca.demo.model.SummaryView;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerSummaryRepository extends JpaRepository<CustomerSummary, Long> {

    @Query("SELECT " +
            "   new bh.beca.demo.model.SummaryView(cs.id, cs.firstName, cs.lastName, " +
            "       a.id, a.name, a.total," +
            "       t.id, t.txDate, t.amount, t.comment) " +
            "FROM CustomerSummary cs " +
            "LEFT JOIN cs.accounts a " +
            "LEFT JOIN a.transactions t WHERE cs.id = :id")
    List<SummaryView> viewById(@Param("id") long id);
}
