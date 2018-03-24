package pl.jakubpiecuch.authorization.feign.accounts;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Account implements Serializable {
    private String name;
    private String password;
    private List<String> permissions;
}
