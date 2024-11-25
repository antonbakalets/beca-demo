package bh.beca.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDto {

    private long id;
    private String firstName;
    private String lastName;
}
