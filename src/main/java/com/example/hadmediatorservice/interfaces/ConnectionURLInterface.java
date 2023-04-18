package com.example.hadmediatorservice.interfaces;

import com.example.hadmediatorservice.bean.ConnectionURLData;
import com.example.hadmediatorservice.bean.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConnectionURLInterface {

    ResponseEntity<List<String>> getAllConnectionURL();

    ResponseEntity<ConnectionURLData> addConnectionURL(ConnectionURLData connectionURLData);
}
