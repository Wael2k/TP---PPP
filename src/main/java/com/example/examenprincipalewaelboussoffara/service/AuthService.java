package com.example.examenprincipalewaelboussoffara.service;




import com.example.examenprincipalewaelboussoffara.dao.dto.LoginRequestDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.LoginResponseDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.userDto.UserCreateRequestDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.userDto.UserResponseDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.userDto.UserUpdateRequestDto;

import java.util.List;

public interface AuthService  {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
    UserResponseDto add(UserCreateRequestDto userCreateRequestDto);
    UserResponseDto update(UserUpdateRequestDto userUpdateRequestDto);
    void delete(Integer id);
    List<UserResponseDto> getAll();
    List<UserResponseDto> getAllUsersWithEvents();

}
