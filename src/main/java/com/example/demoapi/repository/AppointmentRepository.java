package com.example.demoapi.repository;

import com.example.demoapi.data.AppointmentDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AppointmentRepository {
    private List<AppointmentDTO> appointments;
    private static Long lastId;

    public AppointmentRepository() {
        lastId = 1L;
        appointments = new ArrayList<>();
        appointments.add(new AppointmentDTO(lastId++, LocalDate.of(2024, 2, 1), "Visiting relative"));
        appointments.add(new AppointmentDTO(lastId++, LocalDate.of(2024, 2, 16), "Finish tutorial"));
    }

    public List<AppointmentDTO> findAll() {
        return this.appointments;
    }

    public AppointmentDTO findById(Long id) {
        return this.appointments.stream()
                .filter(a -> a.id == id)
                .findFirst()
                .get();
    }

    public List<AppointmentDTO> findByDate(String date) {
        return this.appointments.stream()
                .filter(a -> a.date.equals(LocalDate.parse(date)))
                .collect(Collectors.toList());
    }

    public void create(AppointmentDTO appointmentDTO) {
        appointmentDTO.id = lastId++;
        this.appointments.add(appointmentDTO);
    }

    public void save(AppointmentDTO appointmentDTO) {
        if (appointmentDTO.id == null || appointmentDTO.id == 0) {
            appointmentDTO.id = lastId++;
            this.appointments.add(appointmentDTO);
        }
    }

    public void deleteById(Long id) {
        var existingAppointment = findById(id);
        this.appointments.remove(existingAppointment);
    }
}
