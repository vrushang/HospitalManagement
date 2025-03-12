package com.hospitalManagement.hospitalManagement.controller;

import com.hospitalManagement.hospitalManagement.entity.Appointment;
import com.hospitalManagement.hospitalManagement.entity.Patient;
import com.hospitalManagement.hospitalManagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
}
