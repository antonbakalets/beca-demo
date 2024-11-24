package bh.beca.demo.repository;

import bh.beca.demo.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<TransactionEntity, Long>  {
}
