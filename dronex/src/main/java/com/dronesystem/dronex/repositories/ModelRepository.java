/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.dronesystem.dronex.repositories;

import com.dronesystem.dronex.entities.Model;
import java.util.Optional;
import javax.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sabi Employee
 */
public interface ModelRepository extends JpaRepository<Model, Long> {
    public Optional<Model> findByName(String Name);
    public Optional<Model>findByWeightLimit(int WeightLimit);
}
