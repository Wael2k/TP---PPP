package com.example.examenprincipalewaelboussoffara.dao.dto.userDto;

import com.example.examenprincipalewaelboussoffara.enumaration.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateRequestDto {
    private String username;
    private String password;
    private RoleEnum role;

}
