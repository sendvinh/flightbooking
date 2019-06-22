/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Administrator
 */
@Entity
public class Aircraft implements Serializable {
    @Id
    @Column(name = "AIRCRAFT_CODE")
    private String aircraftCode;
    @Column(name = "BRAND_NAME")
    private String brandName;
    @Column(name = "NUM_OF_SEAT")
    private int numOfSeat;
    @Column(name = "YEAR_OF_MANUFATURE")
    private String yearOfManufacture;
    @Column(name = "PICTURE")
    private String picture;

    public Aircraft() {
    }

    public Aircraft(String aircraftCode, String brandName, int numOfSeat, String yearOfManufacture, String picture) {
        this.aircraftCode = aircraftCode;
        this.brandName = brandName;
        this.numOfSeat = numOfSeat;
        this.yearOfManufacture = yearOfManufacture;
        this.picture = picture;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getNumOfSeat() {
        return numOfSeat;
    }

    public void setNumOfSeat(int numOfSeat) {
        this.numOfSeat = numOfSeat;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
