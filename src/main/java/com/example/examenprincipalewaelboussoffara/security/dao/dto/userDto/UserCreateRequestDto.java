package com.example.examenprincipalewaelboussoffara.security.dao.dto.userDto;

import com.example.examenprincipalewaelboussoffara.security.enumaration.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateRequestDto {
    private String username;
    private String password;
    private RoleEnum role;

}
