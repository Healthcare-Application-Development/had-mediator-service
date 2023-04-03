package com.example.hadmediatorservice.controller;

import com.example.hadmediatorservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.example.hadmediatorservice.bean.PatientData;
//import spring.restapi.model.PatientRequest;
import com.example.hadmediatorservice.bean.Response;
import com.example.hadmediatorservice.repository.PatientDataRepository;
//import spring.restapi.repository.PatientDataRequestRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class PatientDataController {
    //
    @Autowired
    private PatientDataRepository patientDataRepository;
//    private PatientDataRequestRepository patientDataRequestRepository;


    @GetMapping("/get-patient-data/{id}")
    public ResponseEntity<PatientData> getPatientById(@PathVariable(value = "id") Long abha_id) throws ResourceNotFoundException {
        PatientData patientData = patientDataRepository.findById(abha_id)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with Abha ID:" + abha_id+" could not be found"));
        return ResponseEntity.ok().body(patientData);
    }



    @GetMapping("/get-all-patient-health-record")
    public Response getAllPatientData() throws ResourceNotFoundException{
        ArrayList<String> portNumbers = new ArrayList<String>();
        portNumbers.add("http://localhost:3002/api/receptionist/getAll");
//        portNumbers.add("http://localhost:3000/api/receptionist/getAll");
        List<Response> responseList=new ArrayList<>();
        for(int i = 0; i < portNumbers.size(); i++) {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = portNumbers.get(i);
            ResponseEntity<Response> response= restTemplate.getForEntity(fooResourceUrl, Response.class);
            responseList.add(response.getBody());
        }
        return new Response(responseList, 200);
    }




}
