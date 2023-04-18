package com.example.hadmediatorservice.bean;

import java.util.Date;


public class ConsentItem {

    public ConsentItem(Integer id, Integer doctorID, Integer patientID, String consentMessage, Boolean consentAcknowledged, Boolean approved, Date fromDate, Date toDate, ConsentArtifact consentArtifact) {
        this.id = id;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.consentMessage = consentMessage;
        this.consentAcknowledged = consentAcknowledged;
        this.approved = approved;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ConsentItem() {}
    private Integer id;
    private Integer doctorID;
    private Integer patientID;
    private String consentMessage;
    private Boolean consentAcknowledged;
    private Boolean approved;
    private Date fromDate;
    private Date toDate;
    private ConsentArtifact consentArtifact;

    public ConsentArtifact getConsentArtifact() {
        return consentArtifact;
    }

    public void setConsentArtifact(ConsentArtifact consentArtifact) {
        this.consentArtifact = consentArtifact;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }
    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }
    public String getConsentMessage() {
        return consentMessage;
    }

    public void setConsentMessage(String consentRequest) {
        this.consentMessage = consentRequest;
    }

    public Boolean getConsentAcknowledged() {
        return consentAcknowledged;
    }

    public void setConsentAcknowledged(Boolean consentAcknowledged) {
        this.consentAcknowledged = consentAcknowledged;
    }

    @Override
    public String toString() {
        return "ConsentItem{" +
                "id=" + id +
                ", doctorID=" + doctorID +
                ", patientID=" + patientID +
                ", consentMessage='" + consentMessage + '\'' +
                ", consentAcknowledged=" + consentAcknowledged +
                ", approved=" + approved +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }


}
