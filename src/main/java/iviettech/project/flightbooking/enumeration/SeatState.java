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
public enum SeatState {
    BOOKED("BOOKED"),
    AVAILABLE("AVAILABLE"),
    TEMPORARY("TEMPORARY");
    
    private String seatState;
     
    private SeatState(String seatState){
        this.seatState = seatState;
    }
     
    public String getSeatState(){
        return this.seatState;
    }
 
    @Override
    public String toString(){
        return this.seatState;
    }
 
 
    public String getName(){
        return this.name();
    }
}
