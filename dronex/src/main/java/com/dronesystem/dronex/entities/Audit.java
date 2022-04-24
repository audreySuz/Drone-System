/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dronesystem.dronex.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Sabi Employee
 */
@Entity
@Table(name="Battery_Check_Audit")
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String droneSerialNumber;
    
    @Column
    private int droneBatLevel;
    
    @Column
    private Date time;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the droneSerialNumber
     */
    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    /**
     * @param droneSerialNumber the droneSerialNumber to set
     */
    public void setDroneSerialNumber(String droneSerialNumber) {
        this.droneSerialNumber = droneSerialNumber;
    }

    /**
     * @return the droneBatLevel
     */
    public int getDroneBatLevel() {
        return droneBatLevel;
    }

    /**
     * @param droneBatLevel the droneBatLevel to set
     */
    public void setDroneBatLevel(int droneBatLevel) {
        this.droneBatLevel = droneBatLevel;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
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
        if (!(object instanceof Audit)) {
            return false;
        }
        Audit other = (Audit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dronesystem.dronex.entities.Audit[ id=" + id + " ]";
    }
    
}
