package com.example.examenprincipalewaelboussoffara.security.dao;

import com.example.examenprincipalewaelboussoffara.security.dao.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e FROM Event e ORDER BY e.date DESC")
    Event findMostRecentEvent();
    @Query("SELECT COUNT(e) FROM Event e")
    long countTotalEvents();
    @Query("SELECT e FROM Event e WHERE e.isModified = true")
    List<Event> findModifiableEvents();


}
