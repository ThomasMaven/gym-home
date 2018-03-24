package pl.jakubpiecuch.authorization.users;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.jakubpiecuch.authorization.feign.accounts.Account;
import pl.jakubpiecuch.authorization.feign.accounts.AccountsEndpoint;

import java.util.stream.Collectors;

@Component
public class RemoteUserDetailsService implements UserDetailsService {

    private final AccountsEndpoint accountsEndpoint;

    public RemoteUserDetailsService(AccountsEndpoint accountsEndpoint) {
        this.accountsEndpoint = accountsEndpoint;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountsEndpoint.getUser(username);
        return new User(username, user.getPassword(),
                user.getPermissions().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
    }
}
