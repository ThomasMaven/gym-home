package pl.jakubpiecuch.accounts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AccountDto {

    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private List<String> permissions;
}
