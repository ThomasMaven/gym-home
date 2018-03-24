package pl.jakubpiecuch.accounts.service;

import pl.jakubpiecuch.accounts.dto.AccountDto;

public interface AccountsService {
    AccountDto getOne(String name);
}
