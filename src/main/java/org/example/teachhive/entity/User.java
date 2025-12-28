package org.example.teachhive.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.teachhive.entity.base.BaseUUIDEntity;
import org.example.teachhive.enums.Position;
import org.example.teachhive.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@SuperBuilder
public class User extends BaseUUIDEntity implements UserDetails {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "phone_number", nullable = false, updatable = false)
    private String phoneNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    private boolean accountExpired = false;

    @Column(columnDefinition = "boolean default true")
    private Boolean isEnabled;

    @Column(nullable = false, updatable = false)
    private LocalDate joinedAt;

    private String bio;

    private String webUrl;

    private byte[] avatar;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountExpired;
    }


    @Override
    public boolean isEnabled() {
        return (Boolean.TRUE.equals(this.isEnabled) && !this.deleted);
    }

    @Override
    public @NonNull String getUsername() {
        return this.username;
    }


}
