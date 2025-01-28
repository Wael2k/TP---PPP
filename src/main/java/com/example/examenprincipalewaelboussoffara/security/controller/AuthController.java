package com.example.examenprincipalewaelboussoffara.security.controller;


import com.example.examenprincipalewaelboussoffara.security.dao.dto.LoginRequestDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.LoginResponseDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.RegisterRequestDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.RegisterResponseDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.userDto.UserCreateRequestDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.userDto.UserResponseDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.userDto.UserUpdateRequestDto;
import com.example.examenprincipalewaelboussoffara.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("create")
    public ResponseEntity<UserResponseDto> createRole(@RequestBody UserCreateRequestDto userCreateRequestDto){

        return ResponseEntity.ok(authService.add(userCreateRequestDto));
    }
    @PutMapping("update")
    public ResponseEntity<UserResponseDto> updateRole(@RequestBody UserUpdateRequestDto userUpdateRequestDto){

        return ResponseEntity.ok(authService.update(userUpdateRequestDto));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Integer id){
        authService.delete(id);
        return ResponseEntity.ok().body("deleted");
    }
    @GetMapping("getAll")
    public ResponseEntity<List<UserResponseDto>> getAll(){
        return ResponseEntity.ok(authService.getAll());
    }

    @GetMapping("getAllUserWithEvents")
    public ResponseEntity<List<UserResponseDto>> getAllUserWithEvents(){
        return ResponseEntity.ok(authService.getAllUsersWithEvents());
    }

}
