package com.example.demoapi.service;

import com.example.demoapi.data.AppointmentDTO;
import com.example.demoapi.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<AppointmentDTO> listAppointments() {
        return this.appointmentRepository.findAll();
    }

    public AppointmentDTO findAppointment(Long id) {
        return this.appointmentRepository.findById(id);
    }

    public List<AppointmentDTO> findAppointmentByDate(String date) {
        return this.appointmentRepository.findByDate(date);
    }

    public void createAppointment(AppointmentDTO appointmentDTO) {
        appointmentDTO.id = null;
        this.appointmentRepository.save(appointmentDTO);
    }

    public void updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        var existingAppointment = this.appointmentRepository.findById(id);
        existingAppointment.title = appointmentDTO.title;
        existingAppointment.date = appointmentDTO.date;
        this.appointmentRepository.save(appointmentDTO);
    }

    public void deleteAppointment(Long id) {
        this.appointmentRepository.deleteById(id);
    }
}
