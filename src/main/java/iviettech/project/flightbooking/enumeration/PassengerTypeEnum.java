/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.enumeration;

/**
 *
 * @author Administrator
 */
public enum PassengerTypeEnum {
    ADULT("ADULT"),
    CHILDREN("CHILDREN");
    
    private String passengerTypeEnum;
    
    private PassengerTypeEnum(String passengerTypeEnum){
        this.passengerTypeEnum = passengerTypeEnum;
    }
     
    public String PassengerTypeEnum(){
        return this.passengerTypeEnum;
    }
 
    @Override
    public String toString(){
        return this.passengerTypeEnum;
    }
 
 
    public String getName(){
        return this.name();
    }
}
