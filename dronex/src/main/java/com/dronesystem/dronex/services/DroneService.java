package com.dronesystem.dronex.services;

import com.dronesystem.dronex.entities.Drone;
import com.dronesystem.dronex.entities.Medication;
import com.dronesystem.dronex.entities.Model;
import java.util.List;

/**
 *
 * @author Sabi Employee
 */
public interface DroneService {

    public Model createModel(Model model);

    public Drone registerDrone(String serialNumber, String model);

    public Drone loadDrone(Medication medication, String serialNumber);

    public List<Medication> getDroneMedications(String serialNumber);

    public List<Drone> getAvailableDrones();

    public Integer getBatteryLevel(String serialNumber);

}
