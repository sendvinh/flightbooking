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
public enum PaymentState {
    PAID("PAID"),
    WAIT_FOR_PAY("WAIT_FOR_PAY");
    
    private String paymentState;
     
    private PaymentState(String paymentState){
        this.paymentState = paymentState;
    }
     
    public String getPaymentState(){
        return this.paymentState;
    }
 
    @Override
    public String toString(){
        return this.paymentState;
    }
 
 
    public String getName(){
        return this.name();
    }
}
