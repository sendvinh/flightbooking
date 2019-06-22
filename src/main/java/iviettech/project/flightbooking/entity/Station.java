/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
public class Station implements Serializable{
    @Id
    @Column(name = "STATION_CODE")
    private String stationCode;
    @Column(name = "STATION_NAME", columnDefinition = "nvarchar(100)")
    private String stationName;
    @Column(name = "CITY", columnDefinition = "nvarchar(100)")
    private String city;
    @Column(name = "COUNTRY", columnDefinition = "nvarchar(100)")
    private String country;

    public Station() {
    }

    public Station(String stationCode, String stationName, String city, String country) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.city = city;
        this.country = country;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
