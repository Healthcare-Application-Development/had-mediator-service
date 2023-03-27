package spring.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.restapi.exception.ResourceNotFoundException;
import spring.restapi.model.PatientData;
import spring.restapi.repository.PatientDataRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class PatientDataController {
    //
    @Autowired
    private PatientDataRepository patientDataRepository;


    @GetMapping("/get-patient-data/{id}")
    public ResponseEntity<PatientData> getPatientById(@PathVariable(value = "id") Long abha_id) throws ResourceNotFoundException{
        PatientData patientData = patientDataRepository.findById(abha_id)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with Abha ID:" + abha_id+" could not be found"));
        return ResponseEntity.ok().body(patientData);
    }

}
