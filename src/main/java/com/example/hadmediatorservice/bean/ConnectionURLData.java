package com.example.hadmediatorservice.bean;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

import java.util.Date;


@Entity
@Table(name = "connectionurldata")
public class ConnectionURLData {

    public  ConnectionURLData(){}

    public ConnectionURLData(String connectionURL) {
        this.connectionURL = connectionURL;
    }
    @Id
    public String connectionURL;

    @Column(nullable = false)
    public String hospitalName;
    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }



}
