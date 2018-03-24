package pl.jakubpiecuch.accounts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.jakubpiecuch.accounts.repository.AccountsRepository;
import pl.jakubpiecuch.accounts.service.AccountsService;
import pl.jakubpiecuch.accounts.service.DefaultAccountsService;
import pl.jakubpiecuch.accounts.service.PasswordAccountsService;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AccountsConfig {

    private final AccountsRepository accountsRepository;

    public AccountsConfig(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Bean
    public Map<String, AccountsService> getAccountsServices() {
        final Map<String, AccountsService> result = new HashMap<>();
        result.put("gym-home", new DefaultAccountsService(this.accountsRepository));
        result.put("authentication", new PasswordAccountsService(this.accountsRepository));
        return result;
    }
}
