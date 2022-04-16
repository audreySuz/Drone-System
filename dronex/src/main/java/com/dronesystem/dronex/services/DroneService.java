package com.dronesystem.dronex.services;

import com.dronesystem.dronex.entities.Model;
import com.dronesystem.dronex.repositories.DroneRepository;
import com.dronesystem.dronex.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
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
    
    public Model createModel (Model model){
        Model exModel = modelRepository.findByName(model.getName());
        if (exModel!=null) {
            throw new RuntimeException("Model already exists");
        }
    Model newModel= modelRepository.save(model);
    return newModel;
    }

    
}
