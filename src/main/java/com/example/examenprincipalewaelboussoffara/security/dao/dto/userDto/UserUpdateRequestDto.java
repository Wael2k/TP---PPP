package com.example.examenprincipalewaelboussoffara.security.dao.dto.userDto;


import com.example.examenprincipalewaelboussoffara.security.enumaration.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequestDto {
    private Integer id;
    private String username;
    private String password;
    private RoleEnum role ;

}
