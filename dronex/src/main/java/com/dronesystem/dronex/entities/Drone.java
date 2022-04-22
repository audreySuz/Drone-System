/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dronesystem.dronex.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Sabi Employee
 */
@Entity
@Table(name="Drone")
public class Drone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String serialNumber;
    
    @Column
    private DroneState droneState;
    
    @ManyToOne
    private Model model;
    
    @Column
    private int batteryLevel;
    
    @JsonIgnore
    @OneToMany(mappedBy = "drone", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medication>medications = new ArrayList();
    
    public enum DroneState{IDLE, LOADING, LOADED, DELIVERING, DELIVERED , RETURNING};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the state
     */
   

    /**
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * @return the batteryLevel
     */
    public int getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * @param batteryLevel the batteryLevel to set
     */
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    /**
     * @return the battery
     */
    
    /**
     * @return the droneState
     */
    public DroneState getDroneState() {
        return droneState;
    }

    /**
     * @param droneState the droneState to set
     */
    public void setDroneState(DroneState droneState) {
        this.droneState = droneState;
    }

    /**
     * @return the medications
     */
    public List<Medication> getMedications() {
        return medications;
    }

    /**
     * @param medications the medications to set
     */
    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drone)) {
            return false;
        }
        Drone other = (Drone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dronesystem.dronex.entities.Drone[ id=" + id + " ]";
    }
    
}
