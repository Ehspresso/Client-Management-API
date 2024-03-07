package com.riley.haircutAPI.controller;

import com.riley.haircutAPI.entity.Appointment;
import com.riley.haircutAPI.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    public List<Appointment> fetchAllAppointments() {
        return appointmentService.fetchAllAppointments();
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
