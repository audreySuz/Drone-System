/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.dronesystem.dronex.repositories;

import com.dronesystem.dronex.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sabi Employee
 */
public interface AuditRepository extends JpaRepository<Audit, Long> {
    
}
