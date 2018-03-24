package pl.jakubpiecuch.accounts.service;

import org.springframework.stereotype.Service;
import pl.jakubpiecuch.accounts.dto.AccountDto;
import pl.jakubpiecuch.accounts.model.Account;
import pl.jakubpiecuch.accounts.repository.AccountsRepository;

@Service("gym-home")
public class DefaultAccountsService extends AbstractAccountsService {

    public DefaultAccountsService(AccountsRepository accountsRepository) {
        super(accountsRepository);
    }

    @Override
    void append(Account account, AccountDto result) {

    }
}
