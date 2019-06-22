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
@Table(name = "FLIGHT_ROUTE")
public class FlightRoute implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLIGHT_ROUTE_ID")
    private long flightRouteId;

    @ManyToOne
    @JoinColumn(name = "DEPARTURE", foreignKey = @ForeignKey(name = "FK_DEPARTURE"))
    private Station departure;

    @ManyToOne
    @JoinColumn(name = "ARRIVAL")
    private Station arrival;

    @Column(name = "STANDARD_PRICE")
    private double standardPrice;

    @Column(name = "DISTANCE")
    private int distance;

    @Column(name = "DURATION")
    private int duration;

    public FlightRoute() {
    }

    public FlightRoute(long flightRouteId, Station departure, Station arrival, double standardPrice, int distance, int duration) {
        this.flightRouteId = flightRouteId;
        this.departure = departure;
        this.arrival = arrival;
        this.standardPrice = standardPrice;
        this.distance = distance;
        this.duration = duration;
    }

    public long getFlightRouteId() {
        return flightRouteId;
    }

    public void setFlightRouteId(long flightRouteId) {
        this.flightRouteId = flightRouteId;
    }

    public Station getDeparture() {
        return departure;
    }

    public void setDeparture(Station departure) {
        this.departure = departure;
    }

    public Station getArrival() {
        return arrival;
    }

    public void setArrival(Station arrival) {
        this.arrival = arrival;
    }

    public double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(double standardPrice) {
        this.standardPrice = standardPrice;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
