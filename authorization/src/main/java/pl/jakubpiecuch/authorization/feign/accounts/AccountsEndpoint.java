package pl.jakubpiecuch.authorization.feign.accounts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accountsEndpoint", url = "http://localhost:8081")
public interface AccountsEndpoint {

    @GetMapping(path = "/users/{name}")
    Account getUser(@PathVariable("name") String name);
}
