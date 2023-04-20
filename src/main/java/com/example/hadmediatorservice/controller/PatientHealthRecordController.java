package com.example.hadmediatorservice.controller;

import com.example.hadmediatorservice.bean.*;
import com.example.hadmediatorservice.encryption.AESUtils;
import com.example.hadmediatorservice.exception.ResourceNotFoundException;
import com.example.hadmediatorservice.interfaces.ConnectionURLInterface;
import com.example.hadmediatorservice.security.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/patientHealthRecord")
public class PatientHealthRecordController {
    @Value("${hospital_name}")
    String username;

    @Value("${hospital_pass}")
    String password;

    @Autowired
    AESUtils aesUtils;
    public PatientHealthRecordController() {}


    @Autowired
    private ConnectionURLInterface connectionURLInterface;

    @PostMapping("/getPatientHealthRecord")
    public ResponseEntity<List<PatientHealthRecord>> getPatientHealthRecord(@RequestBody ConsentArtifact consentArtifact) throws Exception {
        //Fetching the connectionURLs from the DB
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<String>> requestEntityURLs= connectionURLInterface.getAllConnectionURL();
        List<String> listOfConnectionURLs = requestEntityURLs.getBody();
        List<PatientHealthRecord> filteredList = new ArrayList<>();
        for (int i = 0; i < listOfConnectionURLs.size(); i++) {
            String connectionURLTemp = listOfConnectionURLs.get(i);
            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("username", username);
            tokenParams.put("password", password);
            tokenParams.put("role", "ADMIN");
            HttpHeaders tokenHeaders = new HttpHeaders();
            tokenHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> tokenEntity = new HttpEntity<>(tokenParams, tokenHeaders);
            ResponseEntity<Response> tokenResponseEntity = restTemplate.exchange(connectionURLTemp + "/authenticate", HttpMethod.POST, tokenEntity,  new ParameterizedTypeReference<Response>() {});
            Response response = tokenResponseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            String tokenAsString = objectMapper.writeValueAsString(response.getObject());
            AuthenticationResponse authenticationResponse = objectMapper.readValue(tokenAsString, AuthenticationResponse.class);
            String token = authenticationResponse.getAccessToken();
            String connectionURL=connectionURLTemp+"/patientHealthRecord/getPatientHealthRecord";
            List<ConsentItem> listOfConsentItem = consentArtifact.getConsentItems();
                //for each connectionURL fetching List of PatientHealthRecord
                for (int j = 0; j < listOfConsentItem.size(); j++) {
                    ConsentItem consentItem = listOfConsentItem.get(j);

                    //create a map with the correct parameter names and values
                    Map<String, Object> params = new HashMap<>();
                    params.put("abhaId", consentItem.getPatientID());
                    params.put("recordType", consentItem.getConsentMessage());
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Authorization", "Bearer " + token);
                    HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);
                    ResponseEntity<List<PatientHealthRecord>> responseEntity = restTemplate.exchange(connectionURL, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<PatientHealthRecord>>() {
                    });
                    List<PatientHealthRecord> records = responseEntity.getBody();

                    // filter the records based on the fromDate and toDate fields
                    for (int k = 0; k < records.size(); k++) {
                        PatientHealthRecord patientHealthRecord = records.get(k);
                        if (patientHealthRecord != null && consentItem.getFromDate() != null && consentItem.getToDate() != null) {
                            Date recordDate = patientHealthRecord.getTimestamp();
                            Date fromDate = consentItem.getFromDate();
                            Date toDate = consentItem.getToDate();
                            if (recordDate.after(fromDate) && recordDate.before(toDate)) {
                                filteredList.add(patientHealthRecord);
                            }
                        }
                    }
                }
            }
            return new ResponseEntity<List<PatientHealthRecord>>(filteredList, HttpStatus.OK);
        }

    @PostMapping("/getPatientHealthRecordByAbhaId")
    public ResponseEntity<List<PatientHealthRecord>> getPatientHealthRecordByAbhaId(@PathParam("id") String id) throws JsonProcessingException {
        //Fetching the connectionURLs from the DB
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<String>> requestEntityURLs = connectionURLInterface.getAllConnectionURL();
        List<String> listOfConnectionURLs = requestEntityURLs.getBody();
        List<PatientHealthRecord> listOfPatientHealthRecord = new ArrayList<>();

        //for each connectionURL fetching List of PatientHealthRecord
        for (int i = 0; i < listOfConnectionURLs.size(); i++) {
            String connectionURLTemp = listOfConnectionURLs.get(i);
            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("username", username);
            tokenParams.put("password", password);
            tokenParams.put("role", "ADMIN");
            HttpHeaders tokenHeaders = new HttpHeaders();
            tokenHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> tokenEntity = new HttpEntity<>(tokenParams, tokenHeaders);
            ResponseEntity<Response> tokenResponseEntity = restTemplate.exchange(connectionURLTemp + "/authenticate", HttpMethod.POST, tokenEntity,  new ParameterizedTypeReference<Response>() {});
            Response response = tokenResponseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            String tokenAsString = objectMapper.writeValueAsString(response.getObject());
            AuthenticationResponse authenticationResponse = objectMapper.readValue(tokenAsString, AuthenticationResponse.class);
            String token = authenticationResponse.getAccessToken();
            String connectionURL = connectionURLTemp + "/patientHealthRecord/getPatientHealthRecordByAbhaId";
            Map<String, Object> params = new HashMap<>();
            params.put("abhaId", URLDecoder.decode(id, StandardCharsets.UTF_8));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + token);
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);
            ResponseEntity<List<PatientHealthRecord>> responseOfListofPatientHealthRecord = restTemplate.exchange(connectionURL, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<PatientHealthRecord>>() {
            });
            List<PatientHealthRecord> records = responseOfListofPatientHealthRecord.getBody();
            for (int j = 0; j < records.size(); j++) {
                PatientHealthRecord patientHealthRecord = records.get(j);
                listOfPatientHealthRecord.add(patientHealthRecord);
            }

        }
        return new ResponseEntity<List<PatientHealthRecord>>(listOfPatientHealthRecord, HttpStatus.OK);
    }

    @GetMapping("/getAllPatientHealthRecord")
    public Response getAllPatientHealthRecord() throws ResourceNotFoundException{
        ArrayList<String> portNumbers = new ArrayList<String>();
        portNumbers.add("http://localhost:3002/api/receptionist/getAll");
        portNumbers.add("http://localhost:3000/api/receptionist/getAll");
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
