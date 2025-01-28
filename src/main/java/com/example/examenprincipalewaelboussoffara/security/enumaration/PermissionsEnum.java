package com.example.examenprincipalewaelboussoffara.security.enumaration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PermissionsEnum {
    PRINCIPAL_READ("principal:read");
    @Getter
    private final String permission;
}
