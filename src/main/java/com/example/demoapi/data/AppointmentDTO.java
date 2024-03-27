package com.example.demoapi.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AppointmentDTO {
    public Long id;
    @NotNull
    public LocalDate date;
    @NotBlank
    public String title;

    public AppointmentDTO(Long id, LocalDate date, String title) {
        this.id = id;
        this.date = date;
        this.title = title;
    }
}
