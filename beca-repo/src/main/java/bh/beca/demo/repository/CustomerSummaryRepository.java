package bh.beca.demo.repository;

import bh.beca.demo.model.CustomerSummary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSummaryRepository extends JpaRepository<CustomerSummary, Long> {

    @EntityGraph(value = "CustomerSummary.summary")
    CustomerSummary findById(long id);
}
