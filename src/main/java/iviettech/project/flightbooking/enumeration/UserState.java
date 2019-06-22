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
public enum UserState {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DELETED("DELETED"),
    LOCKED("LOCKED");
    
    private String userState;
     
    private UserState(String userState){
        this.userState = userState;
    }
     
    public String getUserState(){
        return this.userState;
    }
 
    @Override
    public String toString(){
        return this.userState;
    }
 
 
    public String getName(){
        return this.name();
    }
}
