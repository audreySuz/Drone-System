/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dronesystem.dronex.dtos;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Sabi Employee
 */
@Data
public class RegisterDroneRequest {

    String serialNumber;
    String model;
}
