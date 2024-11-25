package bh.beca.demo.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private long id;
    private String name;
    private BigDecimal total;

}
