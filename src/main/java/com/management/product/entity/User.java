package com.management.product.entity;

import com.management.product.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@Table(name = "users")
public class User extends Model implements UserDetails {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "locked")
    private boolean isLocked;

    public User() {
        username = "";
        password = "";
        role = UserRole.USER;
    }

    public User(String username) {
        this();
        setUsername(username);
    }

    public User(String username, String password) {
        this(username);
        setPassword(password);
    }

    public User(String username, String password, UserRole role) {
        this(username, password);
        setRole(role);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isLocked=" + isLocked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o))
            return false;
        User user = (User) o;
        if (!username.equals(user.username))
            return false;
        if (!password.equals(user.password))
            return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" +
                getRole().name());
        grantedAuthorities.add(simpleGrantedAuthority);
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = isNotBlank(password) ? password : "";
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = isNotBlank(username) ? username : "";
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = (role != null) ? role : UserRole.USER;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isLocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isLocked;
    }

    @Override
    public boolean isEnabled() {
        return !isLocked;
    }
}
