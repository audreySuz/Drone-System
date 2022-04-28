package com.dronesystem.dronex.controllers;

import com.dronesystem.dronex.dtos.GetMedRequest;
import com.dronesystem.dronex.dtos.LoadDroneRequest;
import com.dronesystem.dronex.dtos.RegisterDroneRequest;
import com.dronesystem.dronex.entities.Drone;
import com.dronesystem.dronex.entities.Medication;
import com.dronesystem.dronex.entities.Model;
import com.dronesystem.dronex.responses.CustomResponse;
import com.dronesystem.dronex.services.impl.DroneServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Tag(name = "Drone", description = "Drone Controller")
@RequiredArgsConstructor
public class DroneController {

    private final DroneServiceImpl droneService;
    

    @PostMapping("/model")
    public ResponseEntity<CustomResponse<Model>> createModel(@RequestBody Model model) throws Exception {
        // Model newModel = droneService.createModel(model);
        CustomResponse<Model> response = droneService.createModel(model);
        return new ResponseEntity<CustomResponse<Model>>(response, response.getStatus());
        //return ResponseEntity.status(HttpStatus.CREATED).body(newModel);
    }

    @PostMapping("/drone")
    public ResponseEntity<CustomResponse<Drone>> registerDrone(@RequestBody RegisterDroneRequest request) {
        CustomResponse<Drone> response = droneService.registerDrone(request.getSerialNumber(), request.getModel());
        return new ResponseEntity<CustomResponse<Drone>>(response, response.getStatus());
    }

    @PutMapping("/load")
    public ResponseEntity<CustomResponse<Drone>> loadDrone(@RequestBody LoadDroneRequest request) throws Exception {
        CustomResponse<Drone> response = droneService.loadDrone(request.getMedication(), request.getSerialNumber());
        return new ResponseEntity<CustomResponse<Drone>>(response, response.getStatus());
    }

    @GetMapping("/loadedmedications")
    public ResponseEntity<CustomResponse<List<Medication>>> getDroneMedications(@RequestBody GetMedRequest request) {
        CustomResponse<List<Medication>> response = droneService.getDroneMedications(request.getSerialNumber());
        return new ResponseEntity<CustomResponse<List<Medication>>>(response, response.getStatus());
    }

    @GetMapping("/avalaibledrones")
    public ResponseEntity<CustomResponse<List<Drone>>> getAvailableDrones() {
        CustomResponse<List<Drone>> response = droneService.getAvailableDrones();
        return new ResponseEntity<CustomResponse<List<Drone>>>(response, response.getStatus());
    }

    @GetMapping("/battery")
    public ResponseEntity<CustomResponse<Integer>> getBatteryLevel(@RequestBody GetMedRequest request) {
        CustomResponse<Integer> response = droneService.getBatteryLevel(request.getSerialNumber());
        return new ResponseEntity<CustomResponse<Integer>>(response, response.getStatus());
    }

    @PutMapping("/state")
    public ResponseEntity<CustomResponse<String>> updateDroneState(@RequestBody GetMedRequest request) {
        CustomResponse<String> response = droneService.updateDroneState(request.getSerialNumber(), request.getState());
        return new ResponseEntity<CustomResponse<String>>(response, response.getStatus());
    }
}
