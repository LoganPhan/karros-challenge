package com.example.demo.repository;

import com.example.demo.domain.GPS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpsRepository extends JpaRepository<GPS, Long> {

}