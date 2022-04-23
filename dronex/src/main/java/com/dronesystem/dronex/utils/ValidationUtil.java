
package com.dronesystem.dronex.utils;

/**
 *
 * @author Sabi Employee
 */
public class ValidationUtil {
    public  boolean validateMedicationName( String name){
    return name.matches("[a-zA-Z_0-9_'-]*");
    }
    
    public  boolean validateCode( String name){
    return name.matches("[A-Z_0-9_']*");
    }
}
