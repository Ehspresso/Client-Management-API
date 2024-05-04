package com.riley.haircutAPI.controller;

import com.riley.haircutAPI.entity.Appointment;
import com.riley.haircutAPI.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    @Operation(summary = "Retrieve all appointments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all appointments"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully retrieved all appointments")
    })
    public ResponseEntity<List<Appointment>> fetchAllAppointments() {
        return ResponseEntity.ok(appointmentService.fetchAllAppointments());
    }

    @GetMapping("/appointments/{id}")
    @Operation(summary = "Retrieve appointment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointment"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully retrieved appointment")
    })
    public ResponseEntity<Appointment> fetchAppointment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(appointmentService.fetchAppointment(id));
    }

    @PostMapping("/appointments")
    @Operation(summary = "Create new appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created new appointment"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully created new appointment")
    })
    public Appointment saveAppointments(@RequestBody Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    @DeleteMapping("/appointments/{id}")
    @Operation(summary = "Delete appointment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted appointment"),
            @ApiResponse(responseCode = "400", description = "Unsuccessfully deleted appointment")
    })
    public String deleteAppointment(@PathVariable("id") Long id) {
        appointmentService.deleteAppointment(id);
        return "Appointment deleted!";
    }
}
