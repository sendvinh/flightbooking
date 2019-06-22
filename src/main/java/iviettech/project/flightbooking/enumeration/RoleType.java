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
public enum RoleType {
    CUSTOMER("CUSTOMER"),
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");
    
    private String roleType;
    
    private RoleType(String roleType){
        this.roleType = roleType;
    }
     
    public String getRoleType(){
        return this.roleType;
    }
 
    @Override
    public String toString(){
        return this.roleType;
    }
 
 
    public String getName(){
        return this.name();
    }
}
