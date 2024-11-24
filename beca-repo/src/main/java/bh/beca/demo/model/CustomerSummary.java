package bh.beca.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_login")

@NamedEntityGraph(name = "CustomerSummary.summary",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("firstName"),
                @NamedAttributeNode("lastName"),
                @NamedAttributeNode(value = "accounts", subgraph = "AccountEntity.summary")
        },
        subgraphs = {
                @NamedSubgraph(name = "AccountEntity.summary",
                        type = AccountEntity.class,
                        attributeNodes = {
                                @NamedAttributeNode("id"),
                                @NamedAttributeNode("total"),
                                @NamedAttributeNode(value = "transactions", subgraph = "TransactionEntity.summary"),
                }),
                @NamedSubgraph(name = "TransactionEntity.summary",
                        type = TransactionEntity.class,
                        attributeNodes = {
                                @NamedAttributeNode("id"),
                                @NamedAttributeNode("comment")
                        })
        })

@Getter
@Setter
@EqualsAndHashCode
public class CustomerSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<AccountEntity> accounts;
}
