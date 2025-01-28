package com.example.examenprincipalewaelboussoffara.security.dao.dto.eventDto;

import com.example.examenprincipalewaelboussoffara.security.dao.dto.userDto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EventResponseDto {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime date;
    private Boolean isModified;
    private UserResponseDto organizer;
}
