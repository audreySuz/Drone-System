
package com.dronesystem.dronex.repositories;

import com.dronesystem.dronex.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Sabi Employee
 */
public interface DroneRepository extends JpaRepository<Drone, Long> {
    
}
