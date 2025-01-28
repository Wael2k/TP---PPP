package com.example.examenprincipalewaelboussoffara.security.service;




import com.example.examenprincipalewaelboussoffara.security.dao.dto.LoginRequestDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.LoginResponseDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.userDto.UserCreateRequestDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.userDto.UserResponseDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.userDto.UserUpdateRequestDto;

import java.util.List;

public interface AuthService  {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
    UserResponseDto add(UserCreateRequestDto userCreateRequestDto);
    UserResponseDto update(UserUpdateRequestDto userUpdateRequestDto);
    void delete(Integer id);
    List<UserResponseDto> getAll();
    List<UserResponseDto> getAllUsersWithEvents();

}
