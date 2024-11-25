package bh.beca.demo.service;

import bh.beca.demo.command.TransactionFactory;
import bh.beca.demo.command.TransactionInsertCommand;
import bh.beca.demo.dto.AccountCreateDto;
import bh.beca.demo.dto.AccountDto;
import bh.beca.demo.mapper.AccountMapper;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final TransactionFactory transactionFactory;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto createAccount(long customerId, AccountCreateDto accountCreateDto) {
        log.debug("Creating new account for customer id: {}", customerId);
        BigDecimal creditAmount = accountCreateDto.getInitialCredit();

        TransactionInsertCommand transactionInsertCommand = transactionFactory.transactionInsertCommand(customerId, creditAmount);
        return accountMapper.map(transactionInsertCommand.execute());
    }
}
