package com.dronesystem.dronex.services;

import com.dronesystem.dronex.entities.Audit;
import com.dronesystem.dronex.entities.Drone;
import com.dronesystem.dronex.repositories.AuditRepository;
import com.dronesystem.dronex.repositories.DroneRepository;
import com.dronesystem.dronex.repositories.MedicationRepository;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sabi Employee
 */
@Service
@AllArgsConstructor
public class BatteryCheckScheduler {
    private final AuditRepository auditRepository;
    private final DroneRepository droneRepository;
    
    
    @Scheduled(cron = "0 0 */3 ? * *")
    public void checkBattery(){
        List<Drone>drone= droneRepository.findAll();
        for (Drone drone1 : drone) {
           Audit audit =  new Audit();
           audit.setDroneBatLevel(drone1.getBatteryLevel());
           audit.setDroneSerialNumber(drone1.getSerialNumber());
           audit.setTime(new Date());
           auditRepository.save(audit);
        }
        
    }
    
}
