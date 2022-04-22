/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dronesystem.dronex.dtos;

import com.dronesystem.dronex.entities.Medication;
import lombok.Data;

/**
 *
 * @author Sabi Employee
 */
@Data
public class LoadDroneRequest {
    String serialNumber;
    Medication medication;
}
