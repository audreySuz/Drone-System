/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.dronesystem.dronex.repositories;

import com.dronesystem.dronex.entities.Medication;
import java.util.List;
import javax.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sabi Employee
 */
public interface MedicationRepository extends JpaRepository<Medication, Long> {
        public List<Medication> findByName(Long droneId);
        public Medication findByCode(String Code);
}
