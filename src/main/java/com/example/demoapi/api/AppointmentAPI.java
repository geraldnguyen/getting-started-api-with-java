package com.example.demoapi.api;

import com.example.demoapi.data.AppointmentDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentAPI {

    @GetMapping("/hello")
    public String hello() {
        return "hello from AppointmentAPI!";
    }

    private List<AppointmentDTO> appointments;
    private static Long lastId;

    public AppointmentAPI() {
        lastId = 1L;
        appointments = new ArrayList<>();
        appointments.add(new AppointmentDTO(lastId++, LocalDate.of(2024, 2, 1), "Visiting relative"));
        appointments.add(new AppointmentDTO(lastId++, LocalDate.of(2024, 2, 16), "Finish tutorial"));
    }

    // Listing
    @GetMapping("/")
    public List<AppointmentDTO> list() {
        return this.appointments;
    }

    // View details
    @GetMapping("/{id}")
    public AppointmentDTO view(@PathVariable("id") Long id) {
        return this.appointments.stream()
                .filter(a -> a.id == id)
                .findFirst()
                .get();
    }

    // Search: e.g. GET /appointments/search?date=2024-02-01
    @GetMapping("/search")
    public List<AppointmentDTO> search(@RequestParam("date") String date) {
        return this.appointments.stream()
                .filter(a -> a.date.equals(LocalDate.parse(date)))
                .collect(Collectors.toList());
    }



    // Create new appointment
    @PostMapping("/")
    public void create(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentDTO.id = lastId++;
        this.appointments.add(appointmentDTO);
    }

    // update an existing appointment
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody AppointmentDTO appointmentDTO) {
        var existingAppointment = this.appointments.stream()
                .filter(a -> a.id == id)
                .findFirst()
                .get();
        existingAppointment.title = appointmentDTO.title;
        existingAppointment.date = appointmentDTO.date;
    }

    // cancel an existing appointment
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        var existingAppointment = this.appointments.stream()
                .filter(a -> a.id == id)
                .findFirst()
                .get();
        this.appointments.remove(existingAppointment);
    }


}
