package com.patients.services;

import com.patients.entity.Patients;

import java.util.List;

public interface PatientService {

    Patients getPatient(Long id);
    Patients savePatient(Patients patients);
    List<Patients> getAllPatients();
    Patients updatePatient(Long id, Patients patients);
    void deletePatient(Long id);
    List<Patients> saveXmlDataFromUrl();
}
