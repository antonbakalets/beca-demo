package bh.beca.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto {

    private long id;
    private String name;
    private BigDecimal total;

}
