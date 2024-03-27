package com.example.demoapi.repository;

import com.example.demoapi.data.AppointmentDTO;
import com.example.demoapi.data.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentDTO> findByDate(LocalDate date);
}

