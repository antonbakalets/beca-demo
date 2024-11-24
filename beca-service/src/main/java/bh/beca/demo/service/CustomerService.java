package bh.beca.demo.service;

import bh.beca.demo.dto.CustomerSummaryDto;
import bh.beca.demo.model.AccountEntity;
import bh.beca.demo.model.CustomerSummary;
import bh.beca.demo.model.TransactionEntity;
import bh.beca.demo.repository.CustomerSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerSummaryRepository customerSummaryRepository;

    public CustomerSummaryDto getSummary(long id) {
        return convert(customerSummaryRepository.findById(id));
    }

    private CustomerSummaryDto convert(CustomerSummary summary) {
        return CustomerSummaryDto.builder()
                .id(summary.getId())
                .firstName(summary.getFirstName())
                .lastName(summary.getLastName())
                .accounts(summary.getAccounts().stream().map(this::convert).toList())
                .build();
    }

    private CustomerSummaryDto.AccountSummaryDto convert(AccountEntity account) {
        return CustomerSummaryDto.AccountSummaryDto.builder()
                .id(account.getId())
                .name(account.getName())
                .total(account.getTotal())
                .transactions(account.getTransactions().stream().map(this::convert).toList())
                .build();
    }

    private CustomerSummaryDto.TransactionSummaryDto convert(TransactionEntity transaction) {
        return CustomerSummaryDto.TransactionSummaryDto.builder()
                .id(transaction.getId())
                .created(transaction.getTxDate())
                .amount(transaction.getAmount())
                .comment(transaction.getComment())
                .build();
    }
}
