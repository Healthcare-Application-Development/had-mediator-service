package com.example.hadmediatorservice.repository;

import com.example.hadmediatorservice.bean.ConnectionURLData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionURLRepository extends JpaRepository<ConnectionURLData, String> {
}
