package bh.beca.demo.command;

import bh.beca.demo.model.AccountEntity;
import bh.beca.demo.model.TransactionEntity;
import bh.beca.demo.repository.AccountRepository;
import bh.beca.demo.repository.TransactionRepository;
import bh.beca.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class TransactionInsertCommand {

    private final long customerId;
    private final BigDecimal amount;

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public AccountEntity execute() {
        AccountEntity account = new AccountEntity();
        account.setName("Account " + accountRepository.count());
        account.setOwner(userRepository.getReferenceById(customerId));
        account.setTotal(amount);

        if (BigDecimal.ZERO.compareTo(amount) < 0) {
            TransactionEntity transaction = new TransactionEntity();
            transaction.setTxDate(now());
            transaction.setAccount(account);
            transaction.setAmount(amount);
            transaction.setComment("Initial transfer to " + account.getName());
            transactionRepository.save(transaction);
        }

        return accountRepository.save(account);
    }

    protected LocalDateTime now() {
        return LocalDateTime.now();
    }
}
