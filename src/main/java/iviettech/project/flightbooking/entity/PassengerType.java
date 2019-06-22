/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import iviettech.project.flightbooking.enumeration.PassengerTypeEnum;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "PASSENGER_TYPE")
public class PassengerType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PASSENGER_TYPE_ID")
    private int id;
    @Column(name = "PASSENGER_TYPE")
    @Enumerated(EnumType.STRING)
    private PassengerTypeEnum type;
    @Column(name = "RATIO")
    private double ratio;
    
    @Column(name = "INFANT_RATIO")
    private double infantRatio;
    
    @Column(name = "DESCRIPTION")
    private String description;

    public PassengerType() {
    }

    public PassengerType(int id, PassengerTypeEnum type, double ratio, String description) {
        this.id = id;
        this.type = type;
        this.ratio = ratio;
        this.description = description;
    }

    public PassengerType(int id, PassengerTypeEnum type, double ratio, double infantRatio, String description) {
        this.id = id;
        this.type = type;
        this.ratio = ratio;
        this.infantRatio = infantRatio;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PassengerTypeEnum getType() {
        return type;
    }

    public void setType(PassengerTypeEnum type) {
        this.type = type;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getInfantRatio() {
        return infantRatio;
    }

    public void setInfantRatio(double infantRatio) {
        this.infantRatio = infantRatio;
    }
}
