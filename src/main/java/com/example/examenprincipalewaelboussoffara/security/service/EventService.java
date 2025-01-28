package com.example.examenprincipalewaelboussoffara.security.service;



import com.example.examenprincipalewaelboussoffara.security.dao.dto.eventDto.EventCreateRequestDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.eventDto.EventResponseDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.eventDto.EventUpdateRequestDto;

import java.util.List;

public interface EventService {
EventResponseDto create (EventCreateRequestDto eventCreateRequestDto);
EventResponseDto update (EventUpdateRequestDto eventUpdateRequestDto);
List<EventResponseDto> getAllMessages ();
void deleteMessage (int messageId);
EventResponseDto getRecentEvent();
long getNumberTotalEvents();
List<EventResponseDto> getAllEventsModifiable();

}
