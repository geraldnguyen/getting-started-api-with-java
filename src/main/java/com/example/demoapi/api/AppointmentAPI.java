package com.example.demoapi.api;

import com.example.demoapi.data.AppointmentDTO;
import com.example.demoapi.service.AppointmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentAPI {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // View details
    @GetMapping("/unsafe-view/{id}")
    public AppointmentDTO unsafeView(@PathVariable("id") Long id) {
        return jdbcTemplate.query("SELECT id, date, title from Appointment WHERE id = " + id,
                        (rs, rowNum) ->
                                new AppointmentDTO(rs.getLong(1), LocalDate.parse(rs.getString(2)), rs.getString(3)))
                .get(0);
    }

    // Listing
    @GetMapping("/")
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
    public List<AppointmentDTO> search(@RequestParam(value = "date", required = false) String date) {
        if (date == null) return appointmentService.listAppointments();
        else return this.appointmentService.findAppointmentByDate(date);
    }

    @GetMapping("/dates/{date}")
    public List<AppointmentDTO> searchByDate(@PathVariable(value = "date") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") String date) {
        return this.appointmentService.findAppointmentByDate(date);
    }

    // Create new appointment
    @PostMapping("/")
    public void create(@RequestBody AppointmentDTO appointmentDTO) {
        this.appointmentService.createAppointment(appointmentDTO);
    }

    // update an existing appointment
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @Valid @RequestBody AppointmentDTO appointmentDTO) {
        this.appointmentService.updateAppointment(id, appointmentDTO);
    }

    // cancel an existing appointment
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.appointmentService.deleteAppointment(id);
    }
}
