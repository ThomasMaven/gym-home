package pl.jakubpiecuch.accounts.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
public class Role {
    public static final String NAME_FIELD_NAME = "name";
    public static final String GRANTED_PERMISSIONS_FIELD_NAME = "grantedPermissions";
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String USER_ROLE = "USER";
    public static final String ACCOUNT_FIELD_NAME = "account";
    private static final String PERMISSIONS_SEPARATOR = ";";
    private String name;
    private String permissions;
    private Boolean modifiable;

    @Column(name = NAME_FIELD_NAME)
    @NotNull
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "permissions")
    protected String getPermissions() {
        return permissions;
    }

    protected void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Transient
    public String[] getGrantedPermissions() {
        return ADMIN_ROLE.equals(name) ? Permissions.getAllPermissions() : USER_ROLE.equals(name) ? Permissions.getUserPermissions() : StringUtils.splitByWholeSeparatorPreserveAllTokens(permissions, PERMISSIONS_SEPARATOR);
    }

    public void setGrantedPermissions(String[] grantedPermissions) {
        Assert.isTrue(!ADMIN_ROLE.equals(name) && !USER_ROLE.equals(name));
        this.permissions = StringUtils.join(grantedPermissions, PERMISSIONS_SEPARATOR);
    }


    @Column(name = "modifiable")
    public Boolean getModifiable() {
        return modifiable;
    }

    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
    }
}
