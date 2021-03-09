package com.example.demo.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class AppUser implements UserDetails {
     @Id
     @SequenceGenerator(
             name = "user_sequence",
             sequenceName = "user_sequence",
             allocationSize = 1

     )
     @GeneratedValue(
             strategy = GenerationType.SEQUENCE,
             generator = "user_sequence"
     )
    private Long id;
    private String name;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private String password;
    private boolean locked;
    private boolean enabled;

    public AppUser(String name, String username,
                   String email,
                   AppUserRole appUserRole,
                   String password,
                   boolean locked,
                   boolean enabled) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.appUserRole = appUserRole;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
