/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import iviettech.project.flightbooking.enumeration.SeatTypeEnum;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "SEAT_TYPE")
public class SeatType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEAT_TYPE_ID")
    private int id;
    @Column(name = "SEAT_TYPE")
    @Enumerated(EnumType.STRING)
    private SeatTypeEnum type;
    @Column(name = "RATIO")
    private double ratio;
    @Column(name = "DESCRIPTION")
    private String description;

    public SeatType() {
    }

    public SeatType(int id, SeatTypeEnum type, double ratio, String description) {
        this.id = id;
        this.type = type;
        this.ratio = ratio;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SeatTypeEnum getType() {
        return type;
    }

    public void setType(SeatTypeEnum type) {
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

}
