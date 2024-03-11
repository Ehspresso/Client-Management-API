package com.riley.haircutAPI.service;

import com.riley.haircutAPI.entity.Appointment;
import com.riley.haircutAPI.exception.AppointmentRequestException;
import com.riley.haircutAPI.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> fetchAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {

        appointmentRepository.findById(id).orElseThrow(() -> new AppointmentRequestException("Appointment could not be deleted!"));
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment fetchAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentRequestException("Appointment could not be found!"));
        return appointment;
    }
}
