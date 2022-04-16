package com.dronesystem.dronex.controllers;

import com.dronesystem.dronex.entities.Model;
import com.dronesystem.dronex.services.DroneService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
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
@Api(value = "drone", tags = "Drones")
@RequiredArgsConstructor
public class DroneController {
    
private final DroneService droneService;
    
    
    @PostMapping("/model")
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        Model newModel = droneService.createModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }
    
}
