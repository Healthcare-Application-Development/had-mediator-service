package com.example.hadmediatorservice.bean;
import java.util.Date;

public class PatientHealthRecord {
    public PatientHealthRecord(Integer abhaId, String recordType, Date timestamp, String hospitalName) {
        this.abhaId = abhaId;
        this.recordType = recordType;
        this.timestamp=timestamp;
        this.hospitalName=hospitalName;
    }

    private Integer abhaId;
    String recordType;
    Date timestamp;
    String hospitalName;
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public PatientHealthRecord(){}

    public Integer getAbhaId() {
        return abhaId;
    }

    public void setAbhaId(Integer abhaId) {
        this.abhaId = abhaId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }


}
