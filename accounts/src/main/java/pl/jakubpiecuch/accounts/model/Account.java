package pl.jakubpiecuch.accounts.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    public static final String NAME_FIELD_NAME = "name";
    public static final String CREDENTIAL_FIELD_NAME = "password";
    public static final String SALT_FIELD_NAME = "salt";
    public static final String STATUS_FIELD_NAME = "status";
    public static final String EMAIL_FIELD_NAME = "email";
    public static final String CONFIG_FIELD_NAME = "config";
    public static final String ROLE_FIELD_NAME = "role";
    public static final String SOCIAL_TYPE_FIELD_NAME = "social_type";

    @Column(name = NAME_FIELD_NAME)
    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9\\-\\.])+$")
    @Id
    private String name;
    @Column(name = CREDENTIAL_FIELD_NAME)
    @NotNull
    private String password;
    @Column(name = EMAIL_FIELD_NAME)
    private String email;
    @Column(name = STATUS_FIELD_NAME)
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Status status;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_role", joinColumns = {@JoinColumn(name = Role.ACCOUNT_FIELD_NAME)}, inverseJoinColumns = {@JoinColumn(name = ROLE_FIELD_NAME)})
    private List<Role> roles = new ArrayList<Role>();

    public enum Status {
        ACTIVE, RESET_PASSWORD, EXPIRED, CREATED
    }
}
