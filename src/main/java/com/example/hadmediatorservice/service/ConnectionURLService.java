package com.example.hadmediatorservice.service;

import com.example.hadmediatorservice.bean.ConnectionURLData;
import com.example.hadmediatorservice.interfaces.ConnectionURLInterface;
import com.example.hadmediatorservice.repository.ConnectionURLRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionURLService implements ConnectionURLInterface {

    final ConnectionURLRepository connectionURLRepository;

    public ConnectionURLService(ConnectionURLRepository connectionURLRepository) {
        this.connectionURLRepository = connectionURLRepository;
    }

    @Override
    public ResponseEntity<List<String>> getAllConnectionURL() {
        List<ConnectionURLData> connectionURLData = connectionURLRepository.findAll();
        List<String> connectionURLs = new ArrayList<>();
        for (int i = 0; i < connectionURLData.size(); i++) {
            connectionURLs.add(connectionURLData.get(i).getConnectionURL());
        }
        return new ResponseEntity<List<String>>(connectionURLs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConnectionURLData> addConnectionURL(ConnectionURLData connectionURLData){
        ConnectionURLData connectionURLDataInput = connectionURLRepository.save(connectionURLData);
        return new ResponseEntity<ConnectionURLData>(connectionURLDataInput, HttpStatus.OK);
    }


}
