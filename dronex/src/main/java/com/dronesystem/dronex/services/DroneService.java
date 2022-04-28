package com.dronesystem.dronex.services;

import com.dronesystem.dronex.entities.Drone;
import com.dronesystem.dronex.entities.Medication;
import com.dronesystem.dronex.entities.Model;
import com.dronesystem.dronex.responses.CustomResponse;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Sabi Employee
 */
public interface DroneService {

   CustomResponse<Model> createModel(Model model)throws Exception;

   CustomResponse<Drone>  registerDrone(String serialNumber, String model);

     CustomResponse<Drone> loadDrone (Medication medication, String serialNumber)throws Exception;

     CustomResponse<List<Medication>> getDroneMedications(String serialNumber);

      CustomResponse<List<Drone>> getAvailableDrones();

    CustomResponse< Integer> getBatteryLevel(String serialNumber);
    
     CustomResponse<String>  updateDroneState(String serialNumber,String State);
}
