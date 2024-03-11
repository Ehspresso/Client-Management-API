package com.riley.haircutAPI.service;

import com.riley.haircutAPI.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    List<Appointment> fetchAllAppointments();

    Appointment saveAppointment(Appointment appointment);

    void deleteAppointment(Long id);

    Appointment fetchAppointment(Long id);
}
