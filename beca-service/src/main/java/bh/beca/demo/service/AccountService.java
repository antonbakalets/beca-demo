package bh.beca.demo.service;

import bh.beca.demo.dto.AccountCreateDto;
import bh.beca.demo.dto.AccountDto;

public interface AccountService {

    AccountDto createAccount(long customerId, AccountCreateDto accountCreateDto);
}
