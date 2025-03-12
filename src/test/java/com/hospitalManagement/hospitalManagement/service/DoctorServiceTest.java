package com.hospitalManagement.hospitalManagement.service;

import static org.junit.jupiter.api.Assertions.*;


import com.hospitalManagement.hospitalManagement.entity.Doctor;
import com.hospitalManagement.hospitalManagement.exception.ResourceNotFoundException;
import com.hospitalManagement.hospitalManagement.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor1;
    private Doctor doctor2;

    @BeforeEach
    void setUp() {
        doctor1 = new Doctor();
        doctor1.setId(1L);
        doctor1.setName("Dr. John Doe");
        doctor1.setSpecialization("Cardiology");

        doctor2 = new Doctor();
        doctor2.setId(2L);
        doctor2.setName("Dr. Jane Smith");
        doctor2.setSpecialization("Neurology");
    }

    @Test
    void testGetAllDoctors() {
        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));

        List<Doctor> doctors = doctorService.getAllDoctors();

        assertNotNull(doctors);
        assertEquals(2, doctors.size());
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    void testGetDoctorById_Found() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor1));

        Doctor foundDoctor = doctorService.getDoctorById(1L);

        assertNotNull(foundDoctor);
        assertEquals("Dr. John Doe", foundDoctor.getName());
    }

    @Test
    void testGetDoctorById_NotFound() {
        when(doctorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> doctorService.getDoctorById(99L));
    }

    @Test
    void testAddDoctor() {
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor1);

        Doctor savedDoctor = doctorService.addDoctor(doctor1);

        assertNotNull(savedDoctor);
        assertEquals("Dr. John Doe", savedDoctor.getName());
        verify(doctorRepository, times(1)).save(doctor1);
    }

    @Test
    void testUpdateDoctor() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor1));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor1);

        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setName("Dr. John Updated");
        updatedDoctor.setSpecialization("Dermatology");

        Doctor result = doctorService.updateDoctor(1L, updatedDoctor);

        assertNotNull(result);
        assertEquals("Dr. John Updated", result.getName());
        assertEquals("Dermatology", result.getSpecialization());
        verify(doctorRepository, times(1)).save(doctor1);
    }

    @Test
    void testDeleteDoctor() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor1));
        doNothing().when(doctorRepository).delete(doctor1);

        doctorService.deleteDoctor(1L);

        verify(doctorRepository, times(1)).delete(doctor1);
    }
}
