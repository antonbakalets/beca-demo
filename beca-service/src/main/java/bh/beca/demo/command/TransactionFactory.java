package bh.beca.demo.command;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface TransactionFactory {

    @Lookup
    TransactionInsertCommand transactionInsertCommand(long customerId, BigDecimal creditAmount);
}
