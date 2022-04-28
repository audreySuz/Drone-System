package com.dronesystem.dronex.services;

import com.dronesystem.dronex.entities.Drone;
import com.dronesystem.dronex.entities.Model;
import com.dronesystem.dronex.repositories.DroneRepository;
import com.dronesystem.dronex.repositories.ModelRepository;
import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sabi Employee
 */
@RequiredArgsConstructor
@Service
public class preLoadDBService {

    private final ModelRepository modelRepository;
    private final DroneRepository droneRepository;

    @PostConstruct
    public void preLoadModel() {
        Model model1 = new Model();
        Model model2 = new Model();
        Model model3 = new Model();
        Model model4 = new Model();

        model1.setName("LIGHTWEIGHT");
        model1.setWeightLimit(100);
        modelRepository.save(model1);

        model2.setName("MIDDLEWEIGHT");
        model2.setWeightLimit(200);
        modelRepository.save(model2);

        model3.setName("CRUISEWEIGHT");
        model3.setWeightLimit(300);
        modelRepository.save(model3);

        model4.setName("HEAVYWEIGHT");
        model4.setWeightLimit(500);
        modelRepository.save(model4);
    }

    @PostConstruct
    public void preLoadDrones() {
        Drone drone1 = new Drone();
        Drone drone2 = new Drone();
        Drone drone3 = new Drone();
        Drone drone4 = new Drone();
        Drone drone5 = new Drone();
        Drone drone6 = new Drone();
        Drone drone7 = new Drone();
        Drone drone8 = new Drone();
        Drone drone9 = new Drone();
        Drone drone0 = new Drone();

        drone1.setBatteryLevel(100);
        drone1.setDroneState(Drone.DroneState.IDLE);
        drone1.setModel(modelRepository.findByWeightLimit(100).get());
        drone1.setSerialNumber("78654323950P");
        droneRepository.save(drone1);

        drone2.setBatteryLevel(70);
        drone2.setDroneState(Drone.DroneState.IDLE);
        drone2.setModel(modelRepository.findByWeightLimit(300).get());
        drone2.setSerialNumber("83654323950P");
        droneRepository.save(drone2);

        drone3.setBatteryLevel(100);
        drone3.setDroneState(Drone.DroneState.IDLE);
        drone3.setModel(modelRepository.findByWeightLimit(200).get());
        drone3.setSerialNumber("738364323950P");
        droneRepository.save(drone3);

        drone4.setBatteryLevel(60);
        drone4.setDroneState(Drone.DroneState.IDLE);
        drone4.setModel(modelRepository.findByWeightLimit(500).get());
        drone4.setSerialNumber("23456745678H");
        droneRepository.save(drone4);

        drone5.setBatteryLevel(95);
        drone5.setDroneState(Drone.DroneState.IDLE);
        drone5.setModel(modelRepository.findByWeightLimit(200).get());
        drone5.setSerialNumber("765445678K");
        droneRepository.save(drone5);

        drone6.setBatteryLevel(100);
        drone6.setDroneState(Drone.DroneState.IDLE);
        drone6.setModel(modelRepository.findByWeightLimit(100).get());
        drone6.setSerialNumber("345678945678R");
        droneRepository.save(drone6);

        drone7.setBatteryLevel(50);
        drone7.setDroneState(Drone.DroneState.IDLE);
        drone7.setModel(modelRepository.findByWeightLimit(500).get());
        drone7.setSerialNumber("098345670P");
        droneRepository.save(drone7);

        drone8.setBatteryLevel(100);
        drone8.setDroneState(Drone.DroneState.IDLE);
        drone8.setModel(modelRepository.findByWeightLimit(200).get());
        drone8.setSerialNumber("6789J6543G");
        droneRepository.save(drone8);

        drone9.setBatteryLevel(100);
        drone9.setDroneState(Drone.DroneState.IDLE);
        drone9.setModel(modelRepository.findByWeightLimit(300).get());
        drone9.setSerialNumber("98765434GH");
        droneRepository.save(drone9);

        drone0.setBatteryLevel(20);
        drone0.setDroneState(Drone.DroneState.IDLE);
        drone0.setModel(modelRepository.findByWeightLimit(200).get());
        drone0.setSerialNumber("23457898765TG");
        droneRepository.save(drone0);
    }
}
