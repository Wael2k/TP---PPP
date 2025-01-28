package com.example.examenprincipalewaelboussoffara.service.imp;


import com.example.examenprincipalewaelboussoffara.dao.EventRepository;
import com.example.examenprincipalewaelboussoffara.dao.UserRepository;
import com.example.examenprincipalewaelboussoffara.dao.dto.eventDto.EventCreateRequestDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.eventDto.EventResponseDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.eventDto.EventUpdateRequestDto;
import com.example.examenprincipalewaelboussoffara.dao.dto.userDto.UserResponseDto;
import com.example.examenprincipalewaelboussoffara.dao.model.Event;
import com.example.examenprincipalewaelboussoffara.dao.model.User;
import com.example.examenprincipalewaelboussoffara.exceptions.DataNotFoundException;
import com.example.examenprincipalewaelboussoffara.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImp implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public EventResponseDto create(EventCreateRequestDto eventCreateRequestDto) {
        Event event = new Event();
        event.setDescription(eventCreateRequestDto.getDescription());
        event.setDate(eventCreateRequestDto.getDate());
        event.setTitle(eventCreateRequestDto.getTitle());
        event.setIsModified(false);
        User user = userRepository.findById(eventCreateRequestDto.getOrganizer()).orElseThrow(() -> new DataNotFoundException("BAD_REQUEST","User not found"));
        event.setOrganizer(user);
        event = eventRepository.save(event);
        List<Event> events = user.getEvents();
        events.add(event);
        user.setEvents(events);
        userRepository.save(user);
        return EventResponseDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .date(event.getDate())
                .isModified(event.getIsModified())
                .organizer(UserResponseDto.builder()
                        .userName(event.getOrganizer().getUsername())
                        .role(event.getOrganizer().getRole())
                        .id(event.getOrganizer().getId())
                        .build())
                .build();
    }

    @Override
    public EventResponseDto update(EventUpdateRequestDto eventUpdateRequestDto) {
        Event event = eventRepository.findById(eventUpdateRequestDto.getId()) .orElseThrow(() -> new DataNotFoundException("NOT_FOUND","Event not found"));
        event.setDescription(eventUpdateRequestDto.getDescription());
        event.setDate(eventUpdateRequestDto.getDate());
        event.setTitle(eventUpdateRequestDto.getTitle());
        event.setIsModified(true);
        event = eventRepository.save(event);
        return EventResponseDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .date(event.getDate())
                .isModified(event.getIsModified())
                .organizer(UserResponseDto.builder()
                        .userName(event.getOrganizer().getUsername())
                        .role(event.getOrganizer().getRole())
                        .id(event.getOrganizer().getId())
                        .build())
                .build();

    }

    @Override
    public List<EventResponseDto> getAllMessages() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> EventResponseDto.builder()
                        .id(event.getId())
                        .title(event.getTitle())
                        .description(event.getDescription())
                        .date(event.getDate())
                        .organizer(UserResponseDto.builder()
                                .userName(event.getOrganizer().getUsername())
                                .role(event.getOrganizer().getRole())
                                .id(event.getOrganizer().getId())
                                .build()).build())
                .toList();
    }


    @Override
    public void deleteMessage(int messageId) {
        Event event = eventRepository.findById(messageId) .orElseThrow(() -> new DataNotFoundException("Event not found"));
        eventRepository.delete(event);
    }

    @Override
    public EventResponseDto getRecentEvent() {
        Event event = eventRepository.findMostRecentEvent();
        if(event != null){
            return EventResponseDto.builder()
                   .id(event.getId())
                   .title(event.getTitle())
                   .description(event.getDescription())
                   .date(event.getDate())
                    .isModified(event.getIsModified())
                   .organizer(UserResponseDto.builder()
                           .userName(event.getOrganizer().getUsername())
                           .role(event.getOrganizer().getRole())
                           .id(event.getOrganizer().getId())
                           .build()).build();
        }
        return null;
    }

    @Override
    public long getNumberTotalEvents() {
        return eventRepository.countTotalEvents();
    }

    @Override
    public List<EventResponseDto> getAllEventsModifiable() {
        return eventRepository.findModifiableEvents().stream()
                .map(event -> EventResponseDto.builder()
                        .id(event.getId())
                        .title(event.getTitle())
                        .description(event.getDescription())
                        .date(event.getDate())
                        .isModified(event.getIsModified())
                        .organizer(UserResponseDto.builder()
                                .userName(event.getOrganizer().getUsername())
                                .role(event.getOrganizer().getRole())
                                .id(event.getOrganizer().getId())
                                .build()).build())
                .toList();
    }


}
