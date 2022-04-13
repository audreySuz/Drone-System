/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.dronesystem.dronex.repositories;

import com.dronesystem.dronex.entities.Drone;
import javax.persistence.Id;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sabi Employee
 */
public interface DroneRepository extends CrudRepository<Drone, Long> {
    
}
