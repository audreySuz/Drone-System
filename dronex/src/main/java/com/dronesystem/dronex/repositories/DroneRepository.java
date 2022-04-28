
package com.dronesystem.dronex.repositories;

import com.dronesystem.dronex.entities.Drone;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Sabi Employee
 */
public interface DroneRepository extends JpaRepository<Drone, Long> {
    public Optional<Drone> findBySerialNumber(String serialNumber);
    public List<Drone> findByDroneStateIn(List<Drone.DroneState>droneState);
}
