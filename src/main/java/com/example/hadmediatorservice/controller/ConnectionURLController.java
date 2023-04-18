package com.example.hadmediatorservice.controller;

import com.example.hadmediatorservice.bean.ConnectionURLData;
import com.example.hadmediatorservice.interfaces.ConnectionURLInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connectionURLData")
public class ConnectionURLController {
    @Autowired
    private ConnectionURLInterface connectionURLInterface;

    @GetMapping("/getAllConnectionURL")
    public ResponseEntity<List<String>> getAllConnectionURL() {
        return connectionURLInterface.getAllConnectionURL();
    }

    @PostMapping("/addConnectionURL")
    public ResponseEntity<ConnectionURLData> addConnectionURL(@RequestBody ConnectionURLData connectionURLData) {
        return connectionURLInterface.addConnectionURL(connectionURLData);
    }
}
