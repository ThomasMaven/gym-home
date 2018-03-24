package pl.jakubpiecuch.authorization.users;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pl.jakubpiecuch.authorization.feign.accounts.Account;
import pl.jakubpiecuch.authorization.feign.accounts.AccountsEndpoint;

import java.util.stream.Collectors;

@Component
public class RemoteAuthenticationManager implements AuthenticationManager {

    private final AccountsEndpoint accountsEndpoint;

    public RemoteAuthenticationManager(AccountsEndpoint accountsEndpoint) {
        this.accountsEndpoint = accountsEndpoint;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Account user = accountsEndpoint.getUser(authentication.getName());
        return new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), user.getPermissions().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
    }
}
