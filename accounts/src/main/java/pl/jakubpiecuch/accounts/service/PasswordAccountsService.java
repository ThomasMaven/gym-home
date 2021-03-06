package pl.jakubpiecuch.accounts.service;

import org.springframework.stereotype.Service;
import pl.jakubpiecuch.accounts.dto.AccountDto;
import pl.jakubpiecuch.accounts.model.Account;
import pl.jakubpiecuch.accounts.repository.AccountsRepository;

@Service("authentication")
public class PasswordAccountsService extends AbstractAccountsService {


    public PasswordAccountsService(AccountsRepository accountsRepository) {
        super(accountsRepository);
    }

    @Override
    void append(Account account, AccountDto result) {
        result.setPassword(account.getPassword());
    }
}
