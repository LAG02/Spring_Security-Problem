package webshop.backend.model;

import webshop.backend.auth.StringAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity(name = "webshop_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;

    private String password;

    private UserGroup userGroup = UserGroup.CUSTOMER;

    public User() {}

    public User(String username, String password, UserGroup userGroup) {
        this.username = username;
        this.password = password;
        this.userGroup = userGroup;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public UserGroup getUserGroup() { return userGroup; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = Arrays.asList(new StringAuthority(userGroup.toString()));
        if (userGroup == UserGroup.ADMIN) {
            auths.add(new StringAuthority("CUSTOMER"));
        }
        return auths;
    }
}
