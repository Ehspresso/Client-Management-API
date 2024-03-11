package com.riley.haircutAPI.controller;

import com.riley.haircutAPI.entity.Appointment;
import com.riley.haircutAPI.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> fetchAllAppointments() {
        return ResponseEntity.ok(appointmentService.fetchAllAppointments());
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> fetchAppointment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(appointmentService.fetchAppointment(id));
    }

    @PostMapping("/appointments")
    public Appointment saveAppointments(@RequestBody Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    @DeleteMapping("/appointments/{id}")
    public String deleteAppointment(@PathVariable("id") Long id) {
        appointmentService.deleteAppointment(id);
        return "Appointment deleted!";
    }
}
