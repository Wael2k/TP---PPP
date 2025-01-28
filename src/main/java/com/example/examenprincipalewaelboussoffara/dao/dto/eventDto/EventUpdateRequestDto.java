package com.example.examenprincipalewaelboussoffara.dao.dto.eventDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventUpdateRequestDto {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime date;
}
