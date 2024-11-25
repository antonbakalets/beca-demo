package bh.beca.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SummaryView {
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;

    private Long accountId;
    private String accountName;
    private BigDecimal accountTotal;

    private Long transactionId;
    private LocalDateTime created;
    private BigDecimal amount;
    private String comment;
}
