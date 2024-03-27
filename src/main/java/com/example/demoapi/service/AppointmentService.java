package com.example.demoapi.service;

import com.example.demoapi.data.AppointmentDTO;
import com.example.demoapi.data.AppointmentEntity;
import com.example.demoapi.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentDTO entityToDTO(AppointmentEntity entity) {
        return new AppointmentDTO(entity.id, entity.date, entity.title);
    }

    public AppointmentEntity dtoToEntity(AppointmentDTO dto) {
        return new AppointmentEntity(dto.id, dto.date, dto.title);
    }

    public List<AppointmentDTO> listAppointments() {
        return this.appointmentRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public AppointmentDTO findAppointment(Long id) {
        return entityToDTO(this.appointmentRepository.findById(id).orElseThrow());
    }

    public List<AppointmentDTO> findAppointmentByDate(String date) {
        return this.appointmentRepository.findByDate(LocalDate.parse(date));
    }

    public void createAppointment(AppointmentDTO appointmentDTO) {
        AppointmentEntity entity = dtoToEntity(appointmentDTO);
        entity.id = null;
        this.appointmentRepository.save(entity);
    }

    public void updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        var existingAppointment = this.appointmentRepository.findById(id).orElseThrow();
        existingAppointment.title = appointmentDTO.title;
        existingAppointment.date = appointmentDTO.date;
        this.appointmentRepository.save(existingAppointment);
    }

    public void deleteAppointment(Long id) {
        this.appointmentRepository.deleteById(id);
    }
}
