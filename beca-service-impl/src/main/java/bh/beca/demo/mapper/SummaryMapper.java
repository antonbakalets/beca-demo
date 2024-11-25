package bh.beca.demo.mapper;

import bh.beca.demo.dto.CustomerSummaryDto;
import bh.beca.demo.model.SummaryView;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SummaryMapper {

    public Optional<CustomerSummaryDto> map(List<SummaryView> view) {
        if (view.isEmpty()) {
            return Optional.empty();
        } else {
            SummaryView customer = view.get(0);

            Map<Long, List<SummaryView>> byAccountId = view.stream()
                    .filter(account -> Objects.nonNull(account.getAccountId()))
                    .collect(Collectors.groupingBy(SummaryView::getAccountId));

            return Optional.of(CustomerSummaryDto.builder()
                    .id(customer.getCustomerId())
                    .firstName(customer.getCustomerFirstName())
                    .lastName(customer.getCustomerLastName())
                    .accounts(byAccountId.values().stream()
                            .map(this::mapAccount)
                            .filter(Optional::isPresent)
                            .map(Optional::orElseThrow)
                            .toList())
                    .build());
        }
    }

    private Optional<CustomerSummaryDto.AccountSummaryDto> mapAccount(List<SummaryView> oneAccountsGroup) {
        if (oneAccountsGroup.isEmpty()) {
            return Optional.empty();
        } else {
            SummaryView account = oneAccountsGroup.get(0);
            return Optional.of(CustomerSummaryDto.AccountSummaryDto.builder()
                    .id(account.getAccountId())
                    .name(account.getAccountName())
                    .total(account.getAccountTotal())
                    .transactions(mapTransactions(oneAccountsGroup))
                    .build());
        }
    }

    private List<CustomerSummaryDto.TransactionSummaryDto> mapTransactions(List<SummaryView> transactionsGroup) {
        return transactionsGroup.stream()
                .filter(transaction -> Objects.nonNull(transaction.getTransactionId()))
                .map(transaction -> CustomerSummaryDto.TransactionSummaryDto.builder()
                        .id(transaction.getTransactionId())
                        .created(transaction.getCreated())
                        .amount(transaction.getAmount())
                        .comment(transaction.getComment())
                        .build())
                .toList();
    }

}
