package bh.beca.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerSummaryDto {

    @Getter
    @Builder
    public static class AccountSummaryDto {
        private long id;
        private String name;
        private BigDecimal total;
        private List<TransactionSummaryDto> transactions;
    }

    @Getter
    @Builder
    public static class TransactionSummaryDto {
        private long id;
        private LocalDateTime created;
        private BigDecimal amount;
        private String comment;
    }

    private long id;
    private String firstName;
    private String lastName;
    private List<AccountSummaryDto> accounts;
}
