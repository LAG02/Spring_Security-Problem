package webshop.backend.auth;

import org.springframework.security.core.GrantedAuthority;

public class StringAuthority implements GrantedAuthority {
    private final String authority;

    public StringAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
