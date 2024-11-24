package bh.beca.demo.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tx_action")
@Getter
@Setter
@EqualsAndHashCode
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "tx_date")
    private LocalDate txDate;

    @JoinColumn(name = "debit_account", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountEntity debitAccount;

    @Column(name = "debit_amount", precision = 15, scale = 5)
    private BigDecimal debitAmount;

    @JoinColumn(name = "credit", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountEntity creditAccount;

    @Column(name = "credit_amount", precision = 15, scale = 5)
    private BigDecimal creditAmount;

    @Column(name = "comment")
    private String comment;
}
