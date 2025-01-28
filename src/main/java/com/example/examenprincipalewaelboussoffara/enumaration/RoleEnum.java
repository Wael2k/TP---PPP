package com.example.examenprincipalewaelboussoffara.enumaration;


import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum RoleEnum {
    ORGANIZER(
            Set.of(

            )
    ),
    ADMIN(
            Set.of(
            )
    );

    @Getter
    private final Set<Permissions> permissions;

    RoleEnum(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }



}
