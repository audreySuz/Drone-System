package com.dronesystem.dronex.services.impl;

import com.dronesystem.dronex.entities.Drone;
import com.dronesystem.dronex.entities.Medication;
import com.dronesystem.dronex.entities.Model;
import com.dronesystem.dronex.exceptions.BadRequestException;
import com.dronesystem.dronex.repositories.DroneRepository;
import com.dronesystem.dronex.repositories.MedicationRepository;
import com.dronesystem.dronex.repositories.ModelRepository;
import com.dronesystem.dronex.responses.CustomResponse;
import java.util.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.dronesystem.dronex.services.DroneService;
import com.dronesystem.dronex.utils.ValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sabi Employee
 */
@Service
@AllArgsConstructor
public class DroneServiceImpl implements DroneService {

    private static final Set<Drone.DroneState> DRONE_STATES = Collections.unmodifiableSet(EnumSet.of(Drone.DroneState.IDLE, Drone.DroneState.LOADING));
    private final DroneRepository droneRepository;
    private final ModelRepository modelRepository;
    private final MedicationRepository medicationRepository;
    private final ValidationUtil validationUtil;

    @Override
    public CustomResponse<Model> createModel(Model model) throws Exception {
        model.setName(validationUtil.formatString(model.getName()));
        if (modelRepository.findByName(model.getName()).isPresent()) {
            throw new Exception("Model already exists");
        }
        if (model.getName().isEmpty()) {
            throw new Exception("Model name cannot be empty!");
        }
        if (modelRepository.findByWeightLimit(model.getWeightLimit()).isPresent()) {
            throw new Exception("Model with weight limit already exists");
        }
        Model newModel = modelRepository.save(model);
        return new CustomResponse.CustomResponseBuilder<Model>().withCode("200")
                .withMessage("Model Created successfully").withStatus(HttpStatus.OK).withTimestamp(new Date())
                .withData(newModel).build();
    }

    @Override
    public CustomResponse<Drone> registerDrone(String serialNumber, String model) {
        model = validationUtil.formatString(model);
        if (modelRepository.findByName(model).isEmpty()) {
            throw new EntityNotFoundException("Model does not exists");
        }
        if (droneRepository.findBySerialNumber(serialNumber).isPresent() || serialNumber.length() > 100) {
            throw new RuntimeException("Invalid Serial Number");
        } else {
            Drone drone = new Drone();
            drone.setBatteryLevel(100);
            drone.setDroneState(Drone.DroneState.IDLE);
            drone.setSerialNumber(serialNumber);
            drone.setModel(modelRepository.findByName(model).get());
            Drone newDrone = droneRepository.save(drone);
            return new CustomResponse.CustomResponseBuilder<Drone>().withCode("200")
                    .withMessage("Drone registered successfully").withStatus(HttpStatus.OK).withTimestamp(new Date())
                    .withData(newDrone).build();
        }
    }

    @Override
    public CustomResponse<Drone> loadDrone(Medication medication, String serialNumber) {
        Drone newDrone;
        Drone drone = droneRepository.findBySerialNumber(serialNumber).get();
        if (medicationRepository.findByCode(medication.getCode()).isPresent()) {
            throw new RuntimeException("Medication already loaded");
        }
        int totalWeightOfMeds = drone.getMedications()
                .stream()
                .mapToInt(med -> med.getWeight())
                .sum();
        if (!validationUtil.validateCode(medication.getCode())) {
            throw new BadRequestException("Invalid code! (allowed only upper case letters, underscore and numbers)");
        }
        if (!validationUtil.validateMedicationName(medication.getName())) {
            throw new BadRequestException("Invalid medication name! (allowed only letters, numbers, ‘-‘, ‘_’)");
        }
        if (droneRepository.findBySerialNumber(serialNumber).isPresent() && DRONE_STATES.contains(drone.getDroneState())) {
            if (drone.getBatteryLevel() > 25) {
                if (totalWeightOfMeds + medication.getWeight() <= drone.getModel().getWeightLimit()) {
                    medication.setDrone(drone);
                    drone.getMedications().add(medication);
                    drone.setDroneState(Drone.DroneState.LOADING);
                    newDrone = droneRepository.save(drone);
                } else {
                    throw new RuntimeException("Medication too heavy!");
                }
            } else {
                throw new RuntimeException("Battery Low!");
            }
        } else {
            throw new RuntimeException("Drone Unavailable");
        }
        return new CustomResponse.CustomResponseBuilder<Drone>().withCode("200")
                .withMessage("Drone Loaded successfully").withStatus(HttpStatus.OK).withTimestamp(new Date())
                .withData(drone).build();
    }

    @Override
    public CustomResponse<List<Medication>> getDroneMedications(String serialNumber) {
        Drone drone = droneRepository.findBySerialNumber(serialNumber).get();
        if (drone == null) {
            throw new RuntimeException("Invalid Serial Number!");
        }
        if (!(drone.getDroneState() != Drone.DroneState.DELIVERED
                && drone.getDroneState() != Drone.DroneState.DELIVERING
                && drone.getDroneState() != Drone.DroneState.RETURNING)
        ) {
            throw new RuntimeException("No Medications on this drone ");
        } else {
            return new CustomResponse.CustomResponseBuilder<List<Medication>>().withCode("200")
                    .withMessage("Medications retrieved successfully").withStatus(HttpStatus.OK).withTimestamp(new Date())
                    .withData(drone.getMedications()).build();
        }
    }

    @Override
    public CustomResponse<List<Drone>> getAvailableDrones() {
        List<Drone> drones = droneRepository.findByDroneStateIn(List.of(Drone.DroneState.IDLE, Drone.DroneState.LOADING));
        return new CustomResponse.CustomResponseBuilder<List<Drone>>().withCode("200")
                .withMessage("Drones retrieved successfully").withStatus(HttpStatus.OK).withTimestamp(new Date())
                .withData(drones).build();
    }

    @Override
    public CustomResponse<Integer> getBatteryLevel(String serialNumber) {

        Drone drone = droneRepository.findBySerialNumber(serialNumber).get();
        if (droneRepository.findBySerialNumber(serialNumber).isEmpty()) {
            throw new InternalError("Invalid Serial Number!");
        }
        return new CustomResponse.CustomResponseBuilder<Integer>().withCode("200")
                .withMessage("Battery Level is: " + drone.getBatteryLevel()).withStatus(HttpStatus.OK).withTimestamp(new Date())
                .withData(drone.getBatteryLevel()).build();
    }

    @Override
    public CustomResponse<String> updateDroneState(String serialNumber, String State) {
        Drone drone = droneRepository.findBySerialNumber(serialNumber).get();
        drone.setDroneState(Drone.DroneState.valueOf(State));
        return new CustomResponse.CustomResponseBuilder<String>().withCode("200")
                .withMessage("State changed successfully").withStatus(HttpStatus.OK).withTimestamp(new Date())
                .withData(drone.getDroneState().toString()).build();
    }

}
