/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import iviettech.project.flightbooking.enumeration.PromotionType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
public class Promotion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROMOTION_ID")
    private long promotionId;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private PromotionType type;

    @Column(name = "VALUE_OF_PROMOTION")
    private double valueOfPromotion;
    
    @ManyToMany(mappedBy = "listPromotion")
    private List<Flight> listFlight;

    public Promotion() {
    }

    public Promotion(String description, PromotionType type, double valueOfPromotion) {
        this.description = description;
        this.type = type;
        this.valueOfPromotion = valueOfPromotion;
    }    

    public Promotion(long promotionId, String description, PromotionType type, double valueOfPromotion, List<Flight> listFlight) {
        this.promotionId = promotionId;
        this.description = description;
        this.type = type;
        this.valueOfPromotion = valueOfPromotion;
        this.listFlight = listFlight;
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValueOfPromotion() {
        return valueOfPromotion;
    }

    public void setValueOfPromotion(double valueOfPromotion) {
        this.valueOfPromotion = valueOfPromotion;
    }

    public List<Flight> getListFlight() {
        return listFlight;
    }

    public void setListFlight(List<Flight> listFlight) {
        this.listFlight = listFlight;
    }

    public PromotionType getType() {
        return type;
    }

    public void setType(PromotionType type) {
        this.type = type;
    }

}
