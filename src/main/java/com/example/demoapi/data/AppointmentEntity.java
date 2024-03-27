package com.example.demoapi.data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Appointment")
public class AppointmentEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public LocalDate date;
    public String title;

    public AppointmentEntity() {
    }

    public AppointmentEntity(Long id, LocalDate date, String title) {
        this.id = id;
        this.date = date;
        this.title = title;
    }
}
