package bh.beca.demo.mapper;

import bh.beca.demo.dto.AccountDto;
import bh.beca.demo.model.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDto map(AccountEntity entity) {
        AccountDto dto = new AccountDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTotal(entity.getTotal());
        return dto;
    }
}
