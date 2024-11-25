package bh.beca.demo.command;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public interface TransactionFactory {

    @Lookup
    TransactionInsertCommand transactionInsertCommand(long customerId, BigDecimal creditAmount);
}
