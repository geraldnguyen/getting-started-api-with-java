package com.example.demoapi.data;

import java.time.LocalDate;

public class AppointmentDTO {
    public Long id;
    public LocalDate date;
    public String title;

    public AppointmentDTO(Long id, LocalDate date, String title) {
        this.id = id;
        this.date = date;
        this.title = title;
    }
}
