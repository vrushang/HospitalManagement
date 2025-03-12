package com.hospitalManagement.hospitalManagement.service;

import static org.junit.jupiter.api.Assertions.*;


import com.hospitalManagement.hospitalManagement.entity.Appointment;
import com.hospitalManagement.hospitalManagement.entity.Doctor;
import com.hospitalManagement.hospitalManagement.entity.Patient;
import com.hospitalManagement.hospitalManagement.exception.ResourceNotFoundException;
import com.hospitalManagement.hospitalManagement.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    private Appointment appointment1;
    private Appointment appointment2;
    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. John Doe");

        patient = new Patient();
        patient.setId(1L);
        patient.setName("Alice Brown");

        appointment1 = new Appointment();
        appointment1.setId(1L);
        appointment1.setDoctor(doctor);
        appointment1.setPatient(patient);
        appointment1.setAppointmentTime(LocalDateTime.now().plusDays(1));

        appointment2 = new Appointment();
        appointment2.setId(2L);
        appointment2.setDoctor(doctor);
        appointment2.setPatient(patient);
        appointment2.setAppointmentTime(LocalDateTime.now().plusDays(2));
    }

    @Test
    void testGetAllAppointments() {
        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(appointment1, appointment2));

        List<Appointment> appointments = appointmentService.getAllAppointments();

        assertNotNull(appointments);
        assertEquals(2, appointments.size());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    void testGetAppointmentById_Found() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment1));

        Appointment foundAppointment = appointmentService.getAppointmentById(1L);

        assertNotNull(foundAppointment);
        assertEquals(1L, foundAppointment.getId());
    }

    @Test
    void testGetAppointmentById_NotFound() {
        when(appointmentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> appointmentService.getAppointmentById(99L));
    }

    @Test
    void testAddAppointment() {
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment1);

        Appointment savedAppointment = appointmentService.addAppointment(appointment1);

        assertNotNull(savedAppointment);
        assertEquals(1L, savedAppointment.getId());
        verify(appointmentRepository, times(1)).save(appointment1);
    }



    @Test
    void testDeleteAppointment() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment1));
        doNothing().when(appointmentRepository).delete(appointment1);

        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository, times(1)).delete(appointment1);
    }
}
