package spring.restapi.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

//import javax.persistence.*;

@Entity
@Table(name = "patient_data")
@EntityListeners(AuditingEntityListener.class)
public class PatientData {
    @Id
    private long abha_id;
    @Column(name = "record_type", nullable = false)
    private String record_type;
    @Column(name = "details", nullable = false)
    private String details;

    public long getId(){
        return abha_id;
    }

    public void setId(long abha_id) {
        this.abha_id = abha_id;
    }


    public String getRecordType() {
        return record_type;
    }
    public void setRecordType(String record_type) {
        this.record_type = record_type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }



}
