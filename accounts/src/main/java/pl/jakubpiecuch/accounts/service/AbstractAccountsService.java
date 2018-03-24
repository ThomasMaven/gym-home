package pl.jakubpiecuch.accounts.service;

import pl.jakubpiecuch.accounts.dto.AccountDto;
import pl.jakubpiecuch.accounts.model.Account;
import pl.jakubpiecuch.accounts.model.Permissions;
import pl.jakubpiecuch.accounts.model.Role;
import pl.jakubpiecuch.accounts.repository.AccountsRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractAccountsService implements AccountsService {

    private final AccountsRepository accountsRepository;

    public AbstractAccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public AccountDto getOne(String name) {
        final Account account = accountsRepository.getOne(name);
        AccountDto result =  AccountDto.builder().name(account.getName())
                .permissions(getPermissions(account.getRoles()))
                .build();
        append(account, result);
        return result;
    }

    private List<String> getPermissions(List<Role> roles) {
        return Optional.ofNullable(roles).orElse(Collections.emptyList())
                .stream()
                .flatMap(r -> Stream.of(r.getGrantedPermissions()).map(g -> Permissions.ROLE_PREFIX +  g))
                .collect(Collectors.toList());
    }

    abstract void append(Account account, AccountDto result);
}
