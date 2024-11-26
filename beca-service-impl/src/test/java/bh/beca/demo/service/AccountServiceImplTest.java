package bh.beca.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import bh.beca.demo.command.TransactionFactory;
import bh.beca.demo.command.TransactionInsertCommand;
import bh.beca.demo.dto.AccountCreateDto;
import bh.beca.demo.dto.AccountDto;
import bh.beca.demo.mapper.AccountMapper;
import bh.beca.demo.model.AccountEntity;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    TransactionFactory transactionFactory;

    @Spy
    AccountMapper accountMapper = new AccountMapper();

    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    void createAccount() {
        TransactionInsertCommand command = mock(TransactionInsertCommand.class);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(15L);
        when(command.execute()).thenReturn(accountEntity);
        when(transactionFactory.transactionInsertCommand(12L, BigDecimal.TEN)).thenReturn(command);

        AccountCreateDto accountCreateDto = new AccountCreateDto();
        accountCreateDto.setInitialCredit(BigDecimal.TEN);
        AccountDto account = accountService.createAccount(12L, accountCreateDto);

        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo(15L);
    }
}