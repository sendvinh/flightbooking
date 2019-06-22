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
public enum SeatTypeEnum {
    BUSINESS("BUSINESS"),
    ECONOMY("ECONOMY");
    
    private String seatTypeEnum;
    
    private SeatTypeEnum(String seatTypeEnum){
        this.seatTypeEnum = seatTypeEnum;
    }
     
    public String SeatTypeEnum(){
        return this.seatTypeEnum;
    }
 
    @Override
    public String toString(){
        return this.seatTypeEnum;
    }
 
 
    public String getName(){
        return this.name();
    }
}
