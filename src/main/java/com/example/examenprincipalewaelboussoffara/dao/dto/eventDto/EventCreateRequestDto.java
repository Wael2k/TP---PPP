package com.example.examenprincipalewaelboussoffara.dao.dto.eventDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventCreateRequestDto {

    private String title;
    private String description;
    private LocalDateTime date;
    private Integer organizer;
}
