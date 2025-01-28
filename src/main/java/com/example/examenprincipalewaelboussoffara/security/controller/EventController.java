package com.example.examenprincipalewaelboussoffara.security.controller;



import com.example.examenprincipalewaelboussoffara.security.dao.dto.eventDto.EventCreateRequestDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.eventDto.EventResponseDto;
import com.example.examenprincipalewaelboussoffara.security.dao.dto.eventDto.EventUpdateRequestDto;
import com.example.examenprincipalewaelboussoffara.security.service.EventService;
import examen_TP.demo.enumaration.MessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/event/")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("create")
    public ResponseEntity<EventResponseDto> create(@RequestBody EventCreateRequestDto eventCreateRequestDto) {

        return ResponseEntity.ok(eventService.create(eventCreateRequestDto));
    }
    @PutMapping("update")
    public ResponseEntity<EventResponseDto> update(@RequestBody EventUpdateRequestDto eventUpdateRequestDto) {

        return ResponseEntity.ok(eventService.update(eventUpdateRequestDto));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
       eventService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("getAll")
    public ResponseEntity<List<EventResponseDto>> getAll(){
        return ResponseEntity.ok(eventService.getAllMessages());
    }

    @GetMapping("getRecentEvent")
    public ResponseEntity<EventResponseDto> getRecentEvent(){
        return ResponseEntity.ok(eventService.getRecentEvent());
    }
    @GetMapping("getTotalNumberEvents")
    public ResponseEntity<Long> getTotalNumberEvents(){
        return ResponseEntity.ok(eventService.getNumberTotalEvents());
    }
    @GetMapping("getAllEventsModifiable")
    public ResponseEntity<List<EventResponseDto>> getAllEventsModifiable(){
        return ResponseEntity.ok(eventService.getAllEventsModifiable());
    }

}
