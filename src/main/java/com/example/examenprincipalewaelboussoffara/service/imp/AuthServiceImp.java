package com.example.examenprincipalewaelboussoffara.service.imp;


import com.example.examenprincipalewaelboussoffara.security.JwtConfig;
import com.example.examenprincipalewaelboussoffara.security.JwtUtils;
import com.example.examenprincipalewaelboussoffara.security.ObjectsValidatorUtils;
import com.example.examenprincipalewaelboussoffara.dao.AuthRepository;
import com.example.examenprincipalewaelboussoffara.dao.EventRepository;
import com.example.examenprincipalewaelboussoffara.dao.UserRepository;
import com.example.examenprincipalewaelboussoffara.dao.dto.LoginRequestDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.LoginResponseDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.eventDto.EventResponseDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.userDto.UserCreateRequestDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.userDto.UserResponseDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.userDto.UserUpdateRequestDto;
import com.example.examenprincipalewaelboussoffara.dao.model.User;
import com.example.examenprincipalewaelboussoffara.exceptions.BadRequestException;
import com.example.examenprincipalewaelboussoffara.exceptions.DataNotFoundException;
import com.example.examenprincipalewaelboussoffara.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthServiceImp implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ObjectsValidatorUtils objectsValidatorUtil;

    @Autowired
    AuthRepository authRepository;






    @Autowired
    private JwtConfig jwtConfig;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private EventRepository eventRepository;

    @Override

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        List<String> errors  = objectsValidatorUtil.validate(loginRequestDto);
        if(!errors.isEmpty()){
            throw new BadRequestException("BAD_REQUEST","Bad request" ,errors);
        }
        User userInfo = userRepository.findByUserName(loginRequestDto.getUsername()).orElseThrow(() -> new DataNotFoundException("NOT_FOUND","User not found"));

        if(!this.encoder.matches(loginRequestDto.getPassword(), userInfo.getPassword())){
            throw new RuntimeException("Incorrect password");
        }
        var jwtToken = JwtUtils.generateToken(userInfo,jwtConfig);
        log.trace("jwt token generated for user {}",userInfo.getId());
        return LoginResponseDto.builder().accessToken(jwtToken).build();
    }

    @Override
    public UserResponseDto add(UserCreateRequestDto userCreateRequestDto) {
        if(authRepository.findByUserName(userCreateRequestDto.getUsername()).isPresent()) {
            throw new BadRequestException("BAD_REQUEST","Username already exists");
        }
        User user = new User();

        user.setPassword(encoder.encode(userCreateRequestDto.getPassword()));
        user.setUserName(userCreateRequestDto.getUsername());
        user.setRole(userCreateRequestDto.getRole());
        user = authRepository.save(user);





        UserResponseDto userResponseDto = UserResponseDto.builder().id(user.getId()).role(user.getRole()).userName(user.getUsername()).build();
      return userResponseDto;
    }

    @Override
    public UserResponseDto update(UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userUpdateRequestDto.getId()).orElseThrow(() -> new DataNotFoundException("NOT_FOUND","User not found"));
        user.setPassword(encoder.encode(userUpdateRequestDto.getPassword()));
        user.setUserName(userUpdateRequestDto.getUsername());
        user.setRole(userUpdateRequestDto.getRole());
        user = userRepository.save(user);

        UserResponseDto userResponseDto = UserResponseDto.builder().id(user.getId()).role(user.getRole()).userName(user.getUsername()).build();
        return userResponseDto;
    }

    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("NOT_FOUND","User not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        users.forEach(user -> {

            UserResponseDto userResponseDto = UserResponseDto.builder().id(user.getId()).role(user.getRole()).userName(user.getUsername()).build();
            userResponseDtos.add(userResponseDto);
        });
        return userResponseDtos;
    }

    @Override
    public List<UserResponseDto> getAllUsersWithEvents() {
     return this.userRepository.findUsersWithEvents().stream().map(user -> {
         List<EventResponseDto> events = user.getEvents().stream().map(event -> {
             return EventResponseDto.builder().description(event.getDescription()).isModified(event.getIsModified()).date(event.getDate()).id(event.getId()).title(event.getTitle()).build();
         }).toList();
         return  UserResponseDto.builder().events(events).id(user.getId()).role(user.getRole()).userName(user.getUsername()).build();

     }).toList();
    }
}
