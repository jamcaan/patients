package com.patients.rest;
import com.patients.entity.Patients;
import com.patients.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/patients", produces={MediaType.APPLICATION_JSON})
    public ResponseEntity<List<Patients>> getPatients(){
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @GetMapping(value = "/datapoint", produces={MediaType.APPLICATION_XML})
    public ResponseEntity<List<Patients>> getDataPoint(){
        return new ResponseEntity<>(patientService.saveXmlDataFromUrl(), HttpStatus.OK);
    }

    @PostMapping(value = "/addpatient", produces={MediaType.APPLICATION_JSON})
    public ResponseEntity<Patients> addPatient(@RequestBody Patients patient){
            return new ResponseEntity<>(patientService.savePatient(patient), HttpStatus.CREATED);
    }


    @PutMapping(value = "/updatepatient/{id}", produces={MediaType.APPLICATION_JSON})
    public ResponseEntity<Patients> addPatient(@PathVariable("id") Long id, @RequestBody Patients patient){
        return new ResponseEntity<>(patientService.updatePatient(id, patient), HttpStatus.CREATED);
    }

    @GetMapping(value = "/patient/{id}", produces={MediaType.APPLICATION_JSON})
    public ResponseEntity<Patients> getPatientById(@PathVariable("id") Long id){
        return new ResponseEntity<>(patientService.getPatient(id), HttpStatus.OK);
    }

    @DeleteMapping("/patients/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        patientService.deletePatient(id);
    }

}
