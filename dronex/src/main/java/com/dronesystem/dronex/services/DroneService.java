package com.dronesystem.dronex.services;

import com.dronesystem.dronex.entities.Drone;
import com.dronesystem.dronex.entities.Medication;
import com.dronesystem.dronex.entities.Model;
import com.dronesystem.dronex.repositories.DroneRepository;
import com.dronesystem.dronex.repositories.MedicationRepository;
import com.dronesystem.dronex.repositories.ModelRepository;
import com.dronesystem.dronex.utils.ValidationUtil;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Sabi Employee
 */
@Service
@AllArgsConstructor
public class DroneService {

    private final DroneRepository droneRepository;
    private final ModelRepository modelRepository;
    private final MedicationRepository medicationRepository;

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

    public Drone loadDrone(Medication medication, String serialNumber) {
        ValidationUtil validationUtil = new ValidationUtil();
        Drone newDrone;
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        Medication exMed = medicationRepository.findByCode(medication.getCode());
        if (exMed!=null) {
        throw new RuntimeException("Medication already loaded");
        }
        int totalWeightOfMeds = drone.getMedications()
                .stream()
                .mapToInt(med -> med.getWeight())
                .sum();
        if (!validationUtil.validateCode(medication.getCode())) {
            throw new RuntimeException("Invalid code! (allowed only upper case letters, underscore and numbers)");
        }
        if (!validationUtil.validateMedicationName(medication.getName())) {
            throw new RuntimeException("Invalid medication name! (allowed only letters, numbers, ‘-‘, ‘_’)");
        }

        if (drone != null || drone.getDroneState() == Drone.DroneState.IDLE
                || drone.getDroneState() == Drone.DroneState.LOADING
                || drone.getBatteryLevel() > 25) {
            if (totalWeightOfMeds + medication.getWeight() <= drone.getModel().getWeightLimit()) {
                medication.setDrone(drone);
                drone.getMedications().add(medication);
                drone.setDroneState(Drone.DroneState.LOADING);
                newDrone = droneRepository.save(drone);
            } else {
                throw new RuntimeException("Medication too heavy!");
            }
        } else {
            throw new RuntimeException("Drone Unavailable");
        }
        return newDrone;
    }

    public List<Medication> getDroneMedications(String serialNumber) {
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone == null) {
            throw new RuntimeException("Invalid Serial Number!");
        }
        if (drone.getDroneState() == Drone.DroneState.DELIVERED
                || drone.getDroneState() == Drone.DroneState.DELIVERING
                || drone.getDroneState() == Drone.DroneState.RETURNING) {
            throw new RuntimeException("No Medications on this drone ");
        } else {
            return drone.getMedications();
        }

    }

    public List<Drone> getAvailableDrones() {
        List<Drone> drones = droneRepository.findByDroneStateIn(List.of(Drone.DroneState.IDLE, Drone.DroneState.LOADING));
        return drones;
    }

    public Integer getBatteryLevel(String serialNumber){
      Drone drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone == null) {
            throw new RuntimeException("Invalid Serial Number!");
        }
        return drone.getBatteryLevel();
    }
    
}
