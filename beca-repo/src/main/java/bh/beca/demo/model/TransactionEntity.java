package bh.beca.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tx_action")
@Getter
@Setter
@EqualsAndHashCode
@SuppressWarnings("javaarchitecture:S7027")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tx_date")
    private LocalDateTime txDate;

    @JoinColumn(name = "account", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountEntity account;

    @Column(name = "amount", precision = 15, scale = 5)
    private BigDecimal amount;

    @Column(name = "comment")
    private String comment;
}
