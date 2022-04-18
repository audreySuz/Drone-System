package com.dronesystem.dronex.controllers;

import com.dronesystem.dronex.dtos.RegisterDroneRequest;
import com.dronesystem.dronex.entities.Drone;
import com.dronesystem.dronex.entities.Model;
import com.dronesystem.dronex.services.DroneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
//import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author Sabi Employee
 */
@Slf4j
@RestController
@RequestMapping("/drone")
@Tag(name = "Drone",description = "Drone Controller")
@RequiredArgsConstructor
public class DroneController {
    
private final DroneService droneService;
    
    
    @PostMapping("/model")
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        Model newModel = droneService.createModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(newModel);
    }
    
    @PostMapping("/drone")
    public ResponseEntity<Drone> registerDrone(@RequestBody RegisterDroneRequest request){
    Drone newDrone = droneService.registerDrone(request.getSerialNumber(), request.getModel());
    return ResponseEntity.status(HttpStatus.CREATED).body(newDrone);
    }
}
