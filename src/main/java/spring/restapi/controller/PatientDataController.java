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

    @GetMapping
    public String hello(){
        return "Hello";
    }

    @GetMapping("/get-all-patient-data/")
    public @ResponseBody Iterable<PatientData> getAllPatients(){
        return patientDataRepository.findAll();
    }

    @GetMapping("/get-patient-data/{id}")
    public ResponseEntity<PatientData> getDeveloperById(@PathVariable(value = "id") Long devId) throws ResourceNotFoundException{
        PatientData patientData = patientDataRepository.findById(devId)
                .orElseThrow(()-> new ResourceNotFoundException("Developer could not find on::" + devId));
        return ResponseEntity.ok().body(patientData);
    }
//
//    @PostMapping("/dev")
//    public Developer createDev(@Valid @RequestBody Developer developer){
//        return developerRepository.save(developer);
//    }

//    @PutMapping("/dev/{id}")
//    public ResponseEntity<Developer> updateDeveloper (
//            @PathVariable(value = "id") Long devId,
//            @Valid @RequestBody Developer developerDetails) throws ResourceNotFoundException{
//        Developer developer = developerRepository.findById(devId)
//                .orElseThrow(() -> new ResourceNotFoundException("Developer not found on:: " + devId));
//
//        developer.setEmailId(developerDetails.getEmailId());
//        developer.setLastName(developerDetails.getLastName());
//        developerDetails.setFirstName(developerDetails.getFirstName());
////        developerDetails.setCreateAt(developerDetails.getCreateAt());
//       // developer.setUpdatedAt(new Date());
//
//        final Developer updateDev = developerRepository.save(developer);
//        return ResponseEntity.ok(updateDev);
//    }

//    @DeleteMapping("/dev/{id}")
//    public Map<String, Boolean> deleteDev(
//            @PathVariable(value = "id") Long devId) throws Exception{
//        Developer developer = developerRepository.findById(devId)
//                .orElseThrow(()-> new ResourceNotFoundException("Developer cannot found on:: " + devId));
//
//        developerRepository.delete(developer);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("delete", Boolean.TRUE);
//        return response;
//    }

}
