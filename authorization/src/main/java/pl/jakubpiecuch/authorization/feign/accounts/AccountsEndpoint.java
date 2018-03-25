package pl.jakubpiecuch.authorization.feign.accounts;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accountsEndpoint", serviceId = "accounts")
public interface AccountsEndpoint {

    @GetMapping(path = "/users/{name}")
    Account getUser(@PathVariable("name") String name);
}
