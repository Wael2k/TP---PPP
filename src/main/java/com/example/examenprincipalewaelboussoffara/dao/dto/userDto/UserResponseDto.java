package com.example.examenprincipalewaelboussoffara.dao.dto.userDto;


import com.example.examenprincipalewaelboussoffara.dao.dto.eventDto.EventResponseDto;
import com.example.examenprincipalewaelboussoffara.enumaration.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDto {
    private Integer id ;
    private String userName ;
    private RoleEnum role;
    private List<EventResponseDto>events;
}
