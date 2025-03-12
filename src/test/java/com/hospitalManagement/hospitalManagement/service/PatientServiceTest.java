package com.hospitalManagement.hospitalManagement.service;

import static org.junit.jupiter.api.Assertions.*;


import com.hospitalManagement.hospitalManagement.entity.Patient;
import com.hospitalManagement.hospitalManagement.exception.ResourceNotFoundException;
import com.hospitalManagement.hospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    void setUp() {
        patient1 = new Patient();
        patient1.setId(1L);
        patient1.setName("Alice Brown");
        patient1.setAge(30);

        patient2 = new Patient();
        patient2.setId(2L);
        patient2.setName("Bob Smith");
        patient2.setAge(40);
    }

    @Test
    void testGetAllPatients() {
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        List<Patient> patients = patientService.getAllPatients();

        assertNotNull(patients);
        assertEquals(2, patients.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testGetPatientById_Found() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient1));

        Patient foundPatient = patientService.getPatientById(1L);

        assertNotNull(foundPatient);
        assertEquals("Alice Brown", foundPatient.getName());
    }

    @Test
    void testGetPatientById_NotFound() {
        when(patientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientById(99L));
    }

    @Test
    void testAddPatient() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient1);

        Patient savedPatient = patientService.addPatient(patient1);

        assertNotNull(savedPatient);
        assertEquals("Alice Brown", savedPatient.getName());
        verify(patientRepository, times(1)).save(patient1);
    }

    @Test
    void testUpdatePatient() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient1));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient1);

        Patient updatedPatient = new Patient();
        updatedPatient.setName("Alice Updated");
        updatedPatient.setAge(32);

        Patient result = patientService.updatePatient(1L, updatedPatient);

        assertNotNull(result);
        assertEquals("Alice Updated", result.getName());
        assertEquals(32, result.getAge());
        verify(patientRepository, times(1)).save(patient1);
    }

    @Test
    void testDeletePatient() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient1));
        doNothing().when(patientRepository).delete(patient1);

        patientService.deletePatient(1L);

        verify(patientRepository, times(1)).delete(patient1);
    }
}
