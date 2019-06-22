/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import iviettech.project.flightbooking.enumeration.Gender;
import iviettech.project.flightbooking.enumeration.PromotionType;
import iviettech.project.flightbooking.enumeration.SeatState;
import iviettech.project.flightbooking.service.UtilitiesService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrator
 */
@Entity
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID")
    private long ticketId;
     
    //seat
    @Column(name = "SEAT_CODE")
    private String seatCode;
    @ManyToOne
    @JoinColumn(name = "SEAT_TYPE_ID")
    private SeatType seatType;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private SeatState status;
    @Column(name = "EXPIRE_TIME")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime;
    
    //passenger
    @ManyToOne
    @JoinColumn(name = "PASSENGER_TYPE_ID")
    private PassengerType passengerType;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "DATE_OF_BIRTH")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "ATTACHED_INFANT")
    private boolean attachedInfant;    
    @Column(name = "INFANT_NAME")
    private String infantName;
    
    @Column(name = "PRICE")
    private Double price;
    
    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID")
    private Flight flight;
    
    @ManyToOne
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;
    
    @Transient
    private long timeOutSecond;
    

    public Ticket() {
    }
    
    public Ticket(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public SeatState getStatus() {
        return status;
    }

    public void setStatus(SeatState status) {
        this.status = status;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public String getStringOfDateOfBirth() {
        return UtilitiesService.convertDateToString(dateOfBirth);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isAttachedInfant() {
        return attachedInfant;
    }

    public void setAttachedInfant(boolean attachedInfant) {
        this.attachedInfant = attachedInfant;
    }

    public String getInfantName() {
        return infantName;
    }

    public void setInfantName(String infantName) {
        this.infantName = infantName;
    }

    public long getTimeOutSecond() {
        return timeOutSecond;
    }

    public void setTimeOutSecond(long timeOutSecond) {
        this.timeOutSecond = timeOutSecond;
    }
    
    public boolean isInListTicket(List<Ticket> listTicket) {
        boolean t = false;
        if(listTicket.isEmpty()) {
            return false;
        }
        for(int i = 0; i < listTicket.size(); i++) {
            if(this.getTicketId() == listTicket.get(i).getTicketId()) {
                t = true;
                break;
            }
        }
        return t;
    }
    
    public double standardCost() {
        double standardPrice = getFlight().getFlightRoute().getStandardPrice();
        double seatRatio = getSeatType().getRatio();
        return Math.round(standardPrice * seatRatio);
    }
    
    public double passengerTypeCost() {
        double typeRatio = getPassengerType().getRatio();
        return standardCost()*(typeRatio-1);
    }

    public double promotionCost() {
        double promotionCost = 0;
        List<Promotion> listPromotion = getFlight().getListPromotion();
        for (Promotion promotion : listPromotion) {
            if(promotion.getType() == PromotionType.AMOUNT){
                promotionCost += promotion.getValueOfPromotion();
            } 
            if(promotion.getType() == PromotionType.PERCENT){
                promotionCost += standardCost()*promotion.getValueOfPromotion();
            } 
        }
        return promotionCost;
    }
    
    public double infantCost() {
        double infantCost;
        if(attachedInfant == true) {
            infantCost = standardCost()*passengerType.getInfantRatio();   
        } else {
            infantCost = 0;
        }
        return Math.round(infantCost);
    }
    
    public double calculateTotalCostBeforeTax() {
        double additionCost = passengerTypeCost() + infantCost() + promotionCost();
        return Math.round(standardCost() + additionCost);
    }
    
    public double calculateTotalCostAfterTax() {
        double costUnderTax = UtilitiesService.afterTax(standardCost() + passengerTypeCost() + infantCost());
        double costWithoutTax = Math.round(promotionCost());
        return Math.round(costUnderTax + costWithoutTax);
    }
    
    public double calculateTicketPrice() {
        return price = calculateTotalCostAfterTax();
    }
    
    public double calculateTax() {
        return calculateTotalCostAfterTax() - calculateTotalCostBeforeTax();
    }
}
