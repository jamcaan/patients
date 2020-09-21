package com.patients.services.impl;

import com.patients.entity.Patients;
import com.patients.repositories.PatientsRepository;
import com.patients.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.patients.constants.FileConstant.*;



@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private PatientsRepository patientsRepository;

    @Autowired
    public PatientServiceImpl(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    @Override
    public Patients getPatient(Long id) {
        return patientsRepository.findById(id).stream()
                .filter(patient -> id.equals(patient.getId()))
                .findFirst().orElseThrow(() -> new IllegalStateException("Patient" + id + "does not exist!"));
    }
    @Override
    public Patients savePatient(Patients patients) {
        patients.setFirstName(patients.getFirstName());
        patients.setLastName(patients.getLastName());
        patients.setCity(patients.getCity());
        patients.setEmail(patients.getEmail());
        patients.setState(patients.getState());
        patients.setZipCode(patients.getZipCode());
        patients.setPhoneNumber(patients.getPhoneNumber());

        return patientsRepository.save(patients);
    }
    @Override
    public List<Patients> getAllPatients() {
       return patientsRepository.findAll();
    }
    @Override
    public Patients updatePatient(Long id, Patients patients) {
        Patients targetPatient = patientsRepository.findById(id)
                .map(patient -> {
                    patient.setFirstName(patients.getFirstName());
                    patient.setLastName(patients.getLastName());
                    patient.setCity(patients.getCity());
                    patient.setState(patients.getState());
                    patient.setZipCode(patients.getZipCode());
                    patient.setEmail(patients.getEmail());
                    patient.setPhoneNumber(patients.getPhoneNumber());
                    return patientsRepository.save(patient);
                }).orElseGet(()-> {
                    patients.setId(id);
                    return patientsRepository.save(patients);
                });
        return targetPatient;
    }
    @Override
    public void deletePatient(Long id) {
        patientsRepository.deleteById(id);
    }
    @Override
    public List<Patients> saveXmlDataFromUrl(){
            String dir =USER_FOLDER;
            try {
                readFromUrl(dir + "\\datapoint.xml", XML_BASE_URL);
                LOGGER.info("File is saved into that directory");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    return patientsRepository.findAll();
    }

    private byte[] readFromUrl (String fileName, String url ) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedInputStream stream = new BufferedInputStream(new URL(url).openStream());
        try (stream) {
            FileOutputStream outfile = new FileOutputStream(fileName);
            int bytesRead;
            byte[] chunk = new byte[1024];
            while ((bytesRead = stream.read(chunk)) > 0){
                outfile.write(chunk , 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();

    }


}






