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
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");
    
    private String gender;
    
    private Gender(String gender){
        this.gender = gender;
    }
     
    public String getGender(){
        return this.gender;
    }
 
    @Override
    public String toString(){
        return this.gender;
    }
 
 
    public String getName(){
        return this.name();
    }
}
