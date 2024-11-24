package bh.beca.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreateDto {

    @PositiveOrZero
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal initialCredit;
}
