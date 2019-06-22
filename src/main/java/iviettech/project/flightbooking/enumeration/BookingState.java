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
public enum BookingState {
    IN_PROGRESS("IN_PROGRESS"),
    SUCCESS("SUCCESS"),
    CANCEL("CANCEL");
    
    private String boookingState;
     
    private BookingState(String boookingState){
        this.boookingState = boookingState;
    }
     
    public String getBookingState(){
        return this.boookingState;
    }
 
    @Override
    public String toString(){
        return this.boookingState;
    }
 
 
    public String getName(){
        return this.name();
    }
}
