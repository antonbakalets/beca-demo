package bh.beca.demo.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import bh.beca.demo.model.AccountEntity;
import bh.beca.demo.model.TransactionEntity;
import bh.beca.demo.repository.AccountRepository;
import bh.beca.demo.repository.TransactionRepository;
import bh.beca.demo.repository.UserRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionInsertCommandTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        when(accountRepository.save(any(AccountEntity.class))).thenAnswer(input -> input.getArguments()[0]);
    }

    private TransactionInsertCommand createCommand(long customerId, BigDecimal credit) {
        TransactionInsertCommand command = new TransactionInsertCommand(customerId, credit);
        command.setUserRepository(userRepository);
        command.setAccountRepository(accountRepository);
        command.setTransactionRepository(transactionRepository);
        return command;
    }

    @Test
    void executePositiveAmount() {
        TransactionInsertCommand command = createCommand(34L, new BigDecimal("236"));

        AccountEntity account = command.execute();
        assertThat(account).isNotNull();

        verify(userRepository).getReferenceById(34L);
        verify(accountRepository).save(account);
        verify(transactionRepository).save(any(TransactionEntity.class));
    }

    @Test
    void executeZeroCredit() {
        TransactionInsertCommand command = createCommand(35L, BigDecimal.ZERO);

        AccountEntity account = command.execute();
        assertThat(account).isNotNull();
        assertThat(account.getTotal()).isZero();

        verify(userRepository).getReferenceById(35L);
        verify(accountRepository).save(account);
        verifyNoInteractions(transactionRepository);
    }
}