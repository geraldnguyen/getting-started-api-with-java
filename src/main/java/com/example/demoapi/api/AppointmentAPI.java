package com.example.demoapi.api;

import com.example.demoapi.data.AppointmentDTO;
import com.example.demoapi.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = { "/appointments", "/calendar" })
public class AppointmentAPI {
    @Autowired
    private AppointmentService appointmentService;


    // Listing
    @GetMapping(value = { "/", "/get-appointments" }, headers = "user-agent!=PostmanRuntime/7.29.0")
    public List<AppointmentDTO> list() {
        return this.appointmentService.listAppointments();
    }

    // View details
    @GetMapping("/{id}")
    public AppointmentDTO view(@PathVariable("id") Long id) {
        return this.appointmentService.findAppointment(id);
    }

    // Search: e.g. GET /appointments/search?date=2024-02-01
    @GetMapping("/search")
    public List<AppointmentDTO> search(@RequestParam("date") String date) {
        return this.appointmentService.findAppointmentByDate(date);
    }

    // Create new appointment
    @PostMapping("/")
    public void create(@RequestBody AppointmentDTO appointmentDTO) {
        this.appointmentService.createAppointment(appointmentDTO);
    }

    // update an existing appointment
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody AppointmentDTO appointmentDTO) {
        this.appointmentService.updateAppointment(id, appointmentDTO);
    }

    // cancel an existing appointment
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.appointmentService.deleteAppointment(id);
    }


}
