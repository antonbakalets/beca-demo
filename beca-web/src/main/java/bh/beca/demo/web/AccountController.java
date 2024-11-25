package bh.beca.demo.web;

import bh.beca.demo.dto.AccountCreateDto;
import bh.beca.demo.dto.AccountDto;
import bh.beca.demo.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(value = "api/v1/customers/{customer}/accounts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountDto create(@PathVariable("customer") long customerId,
                                           @Valid @RequestBody AccountCreateDto accountCreateDto) {
        return accountService.createAccount(customerId, accountCreateDto);
    }
}
