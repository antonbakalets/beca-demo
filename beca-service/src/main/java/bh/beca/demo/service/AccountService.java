package bh.beca.demo.service;

import bh.beca.demo.command.TransactionFactory;
import bh.beca.demo.command.TransactionInsertCommand;
import bh.beca.demo.dto.AccountCreateDto;
import bh.beca.demo.dto.AccountDto;
import bh.beca.demo.model.AccountEntity;
import bh.beca.demo.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionFactory transactionFactory;

    public List<AccountDto> listByOwner(long customerId) {
        return accountRepository.findAllByOwner_Id(customerId).stream()
                .map(this::convert)
                .toList();
    }

    public AccountDto createAccount(long customerId, AccountCreateDto accountCreateDto) {
        log.info("Creating new account for customer id: {}", customerId);
        BigDecimal creditAmount = accountCreateDto.getInitialCredit();

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
