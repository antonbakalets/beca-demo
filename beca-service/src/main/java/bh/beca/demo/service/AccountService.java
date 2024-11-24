package bh.beca.demo.service;

import bh.beca.demo.command.TransactionFactory;
import bh.beca.demo.command.TransactionInsertCommand;
import bh.beca.demo.dto.AccountDto;
import bh.beca.demo.model.AccountEntity;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final TransactionFactory transactionFactory;

    public AccountDto createAccount(long customerId, BigDecimal creditAmount) {
        log.info("Creating new account for customer id: {}", customerId);

        TransactionInsertCommand transactionInsertCommand = transactionFactory.transactionInsertCommand(customerId, creditAmount);
        return convert(transactionInsertCommand.execute());

    }

    private AccountDto convert(AccountEntity entity) {
        AccountDto dto = new AccountDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTotal(entity.getTotal());
        return dto;
    }

}
