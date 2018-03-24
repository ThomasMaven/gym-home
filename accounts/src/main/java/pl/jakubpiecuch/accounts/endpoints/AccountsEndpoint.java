package pl.jakubpiecuch.accounts.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.jakubpiecuch.accounts.dto.AccountDto;
import pl.jakubpiecuch.accounts.service.AccountsService;

import java.util.Map;

@RestController
public class AccountsEndpoint {

    private final Map<String, AccountsService> accountsServices;

    public AccountsEndpoint(Map<String, AccountsService> accountsServices) {
        this.accountsServices = accountsServices;
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping(path = "/users/{name}")
    public AccountDto getUser(OAuth2Authentication authentication, @PathVariable("name") String name) {
        return accountsServices.get(getClientId(authentication))
                .getOne(name);
    }

    private String getClientId(OAuth2Authentication authentication) {
        return authentication.getOAuth2Request().getClientId();
    }
}
