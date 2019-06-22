/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import iviettech.project.flightbooking.enumeration.PromotionType;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrator
 */
@Entity
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLIGHT_ID")
    private long flightId;

    @Column(name = "FLIGHT_CODE")
    private String flightCode;

    @ManyToOne
    @JoinColumn(name = "AIRCRAFT_CODE")
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(name = "FLIGHT_ROUTE_ID")
    private FlightRoute flightRoute;

    @Column(name = "DEPART_TIME")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departTime;

    @Column(name = "ARRIVE_TIME")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arriveTime;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> listTicket;

    @ManyToMany
    @JoinTable(name = "FLIGHT_PROMOTION",
            joinColumns = {
                @JoinColumn(name = "FLIGHT_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "PROMOTION_ID")})
    private List<Promotion> listPromotion;

    public Flight() {
    }

    public Flight(long flightId, String flightCode, Aircraft aircraft, FlightRoute flightRoute, Date departTime, Date arriveTime, List<Ticket> listTicket, List<Promotion> listPromotion) {
        this.flightId = flightId;
        this.flightCode = flightCode;
        this.aircraft = aircraft;
        this.flightRoute = flightRoute;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.listTicket = listTicket;
        this.listPromotion = listPromotion;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public List<Ticket> getListTicket() {
        return listTicket;
    }

    public void setListTicket(List<Ticket> listTicket) {
        this.listTicket = listTicket;
    }

    public List<Promotion> getListPromotion() {
        return listPromotion;
    }

    public void setListPromotion(List<Promotion> listPromotion) {
        this.listPromotion = listPromotion;
    }

    public String getDepartDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(getDepartTime());
    }
    
    public String getDepartDay() {
        DateFormat dateFormat = new SimpleDateFormat("EEE");
        return dateFormat.format(getDepartTime());
    }
    
    public String getDepartureTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(getDepartTime());
    }
    
    public String getArrivalTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(getArriveTime());
    }
    
    public double getPromotionValue(Promotion promotion) {
        double promotionValue = 0;
        if(promotion.getType()==PromotionType.PERCENT){
            promotionValue = getFlightRoute().getStandardPrice()*promotion.getValueOfPromotion();
        }
        if(promotion.getType()==PromotionType.AMOUNT){
            promotionValue = promotion.getValueOfPromotion();
        }
        return promotionValue;
    }
}
