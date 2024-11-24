package bh.beca.demo.repository;

import bh.beca.demo.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository  extends JpaRepository<AccountEntity, Long> {

    @Query("SELECT a FROM AccountEntity a WHERE a.name = 'Bonus Account'")
    AccountEntity getBonusDebitAccount();
}
