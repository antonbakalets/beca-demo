package bh.beca.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TransactionDto {

    private long id;

    private LocalDateTime txDate;

    private String account;

    private BigDecimal amount;

    private String comment;
}
