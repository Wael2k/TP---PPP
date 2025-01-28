package com.example.examenprincipalewaelboussoffara.security.dao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "TABLE_EVENT")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime date;
    private Boolean isModified;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private User organizer;

}
