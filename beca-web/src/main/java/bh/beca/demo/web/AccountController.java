package bh.beca.demo.web;

import bh.beca.demo.dto.AccountCreateDto;
import bh.beca.demo.dto.AccountDto;
import bh.beca.demo.service.AccountService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("api/customers/{customer}/accounts")
    public List<AccountDto> listByOwner(@PathVariable("customer") long customerId) {
        return accountService.listByOwner(customerId);
    }

    @PostMapping("api/customers/{customer}/accounts")
    public AccountDto create(@PathVariable("customer") long customerId,
                             @Valid AccountCreateDto accountCreateDto) {
        return accountService.createAccount(customerId, accountCreateDto);
    }
}
