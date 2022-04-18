package com.dronesystem.dronex.services;

import com.dronesystem.dronex.entities.Drone;
import com.dronesystem.dronex.entities.Model;
import com.dronesystem.dronex.repositories.DroneRepository;
import com.dronesystem.dronex.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sabi Employee
 */
@Service
@AllArgsConstructor
public class DroneService {

    private final DroneRepository droneRepository;
    private final ModelRepository modelRepository;

    public Model createModel(Model model) {
        Model exModel = modelRepository.findByName(model.getName());
        if (exModel != null) {
            throw new RuntimeException("Model already exists");
        }
        Model newModel = modelRepository.save(model);
        return newModel;
    }

    public Drone registerDrone(String serialNumber, String model) {
        Model exModel = modelRepository.findByName(model);
        Drone exDrone = droneRepository.findBySerialNumber(serialNumber);
        if (exModel == null) {
            throw new RuntimeException("Model does not exists");
        }
        if (exDrone != null || serialNumber.length() > 100) {
            throw new RuntimeException("Invalid Serial Number");
        } else {
            Drone drone = new Drone();
            drone.setBatteryLevel(100);
            drone.setDroneState(Drone.DroneState.IDLE);
            drone.setSerialNumber(serialNumber);
            drone.setModel(exModel);
            Drone newDrone = droneRepository.save(drone);
            return newDrone;
        }
    }

}
