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
public enum PromotionType {
    AMOUNT("AMOUNT"),
    PERCENT("PERCENT");
    
    private String promotionType;
    
    private PromotionType(String promotionType){
        this.promotionType = promotionType;
    }
     
    public String PromotionType(){
        return this.promotionType;
    }
 
    @Override
    public String toString(){
        return this.promotionType;
    }
 
 
    public String getName(){
        return this.name();
    }
}
