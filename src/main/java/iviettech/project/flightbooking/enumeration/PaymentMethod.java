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
public enum PaymentMethod {
    PAY_LATER("PAY_LATER"),
    PAY_ONLINE("PAY_ONLINE");
    
    private String paymentMethod;
     
    private PaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
     
    public String getPaymentMethod(){
        return this.paymentMethod;
    }
 
    @Override
    public String toString(){
        return this.paymentMethod;
    }
 
 
    public String getName(){
        return this.name();
    }
}
